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

package Scanner;

import java_cup.runtime.*;
import Parser.sym;

%%

%public
%final
%class scanner
%implements sym
%unicode
%cup
%cupdebug
%line
%column


%{
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
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

  /* assignment keywords */
  "def"                          { return symbol(DEF); }
  "print"                        { return symbol(PRINT); }
  "return"                       { return symbol(RETURN); }
  
  /* conditional keywords */
  "if"                           { return symbol(IF); }
  "else"                         { return symbol(ELSE); }
  "while"                        { return symbol(WHILE); }
  
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
  ","                            { return symbol(COMMA); }
  "."                            { return symbol(DOT); }
  
  /* operators */
  "="                            { return symbol(ASSIGN); }
  ">"                            { return symbol(GT); }
  "<"                            { return symbol(LT); }
  "!"                          { return symbol(NOT); }
  ":"                            { return symbol(COLON); }
  "=="                           { return symbol(EQ); }
  "<="                           { return symbol(LEQ); }
  ">="                           { return symbol(GEQ); }
  "!="                           { return symbol(NEQ); }
  "and"                          { return symbol(AND); }
  "or"                           { return symbol(OR); }
  "+"                            { return symbol(PLUS); }
  "-"                            { return symbol(MINUS); }
  "*"                            { return symbol(TIMES); }
  "//"                           { return symbol(DIV); }
  "%"                            { return symbol(MOD); }
  
  /* string literal */
  \"                             { yybegin(DSTRING); string.setLength(0); }
  \'                             { yybegin(SSTRING); string.setLength(0); }


  /* numeric literals */
  {DecIntegerLiteral}            { return symbol(INT_LITERAL, new Integer(yytext())); }
  
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
