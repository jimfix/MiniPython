/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2015  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */


import java_cup.runtime.*;

%%

%public
%class Scanner
%implements sym

%unicode

%line
%column

%cup
%cupdebug

%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new JavaSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new JavaSymbol(type, yyline+1, yycolumn+1, value);
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "\"\"\"" [^\"] ~"\"\"\"" 
EndOfLineComment = "#" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

/* string and character literals */
DStringCharacter = [^\r\n\"\\]
SStringCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "bool"                         { return symbol(BOOLEAN); }
  "break"                        { return symbol(BREAK); }
  "continue"                     { return symbol(CONTINUE); }
  "elif"                         { return symbol(ELIF); }
  "else"                         { return symbol(ELSE); }
  "except"                       { return symbol(EXCEPT); }
  "finally"                      { return symbol(FINALLY); }
  "for"                          { return symbol(FOR); }
  "import"                       { return symbol(IMPORT); }
  "int"                          { return symbol(INT); }
  "new"                          { return symbol(NEW); }
  "if"                           { return symbol(IF); }
  "raise"                        { return symbol(RAISE); }
  "return"                       { return symbol(RETURN); }
  "while"                        { return symbol(WHILE); }
  "throws"                       { return symbol(THROWS); }
  "try"                          { return symbol(TRY); }
  "x"                            { return symbol(CROSS); }
  "list"                         { return symbol(LIST); }
  
  /* boolean literals */
  "True"                         { return symbol(BOOL_LITERAL, true); }
  "False"                        { return symbol(BOOL_LITERAL, false); }
  
  /* none literal */
  "None"                         { return symbol(NONE_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(LPAREN); }
  ")"                            { return symbol(RPAREN); }
  "{"                            { return symbol(LBRACE); }
  "}"                            { return symbol(RBRACE); }
  "["                            { return symbol(LBRACK); }
  "]"                            { return symbol(RBRACK); }
  ";"                            { return symbol(SEMICOLON); }
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  
  /* operators */
  "->"                           { return symbol(TO); }
  "="                            { return symbol(ASSIGN); }
  ">"                            { return symbol(GTR); }
  "<"                            { return symbol(LTR); }
  "not"                          { return symbol(NOT); }
  ":"                            { return symbol(COLON); }
  "=="                           { return symbol(EQ); }
  "<="                           { return symbol(LEQ); }
  ">="                           { return symbol(GEQ); }
  "!="                           { return symbol(NEQ); }
  "and"                          { return symbol(AND); }
  "or"                           { return symbol(OR); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(MULT); }
  "//"                           { return symbol(DIV); }
  "%"                            { return symbol(MOD); }
  
  /* string literal */
  \"                             { yybegin(DSTRING); string.setLength(0); }
  \'                             { yybegin(SSTRING); string.setLength(0); }


  /* numeric literals */

  {DecIntegerLiteral}            { return symbol(INTEGER_LITERAL, new Integer(yytext())); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(IDENTIFIER, yytext()); }  
}

<DSTRING> {
  \"                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }

<SSTRING> {
  \'                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
   
  {DStringCharacter}+            { string.append( yytext() ); }
  {SStringCharacter}+            { string.append( yytext() ); }
 
  /* escape sequences */
  "'"                            { string.append( '\'' ); }
  "\""                           { string.append( '"' ); }
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }

  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

  
/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(EOF); }