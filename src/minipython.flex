/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2015  Gerwin Klein <lsf@jflex.de>                    *
 * Modified by Jeremy Cosel & James Fix for Python                         *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Python3 language lexer specification written in Java */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and minipython.cup for a Python parser                      */

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
    return new PythonSymbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new PythonSymbol(type, yyline+1, yycolumn+1, value);
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

/* integer literal */
DecIntegerLiteral = 0 | [1-9][0-9]*

/* string and character literals */
DStringCharacter = [^\r\n\"\\]
SStringCharacter = [^\r\n\'\\]

%state DSTRING, SSTRING

%%

<YYINITIAL> {

  /* keywords */
  "bool"                         { return symbol(BOOL); }
  "break"                        { return symbol(BREAK); }
  "continue"                     { return symbol(CONTINUE); }
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
  ">"                            { return symbol(GT); }
  "<"                            { return symbol(LT); }
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

  {DStringCharacter}+            { string.append( yytext() ); }

  /* escape sequences */
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

<SSTRING> {
  \'                             { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString()); }
 
  {SStringCharacter}+            { string.append( yytext() ); }
 
  /* escape sequences */
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