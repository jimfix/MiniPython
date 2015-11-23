/* The following code was generated by JFlex 1.6.1 */

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


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>./minipython.flex</tt>
 */
public final class scanner implements sym, java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int DSTRING = 2;
  public static final int SSTRING = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\2\1\54\1\55\1\1\16\7\4\0\1\3\1\45"+
    "\1\4\1\5\1\6\1\52\1\0\1\13\1\33\1\34\1\50\1\46"+
    "\1\37\1\47\1\0\1\51\1\10\11\11\1\44\1\0\1\42\1\40"+
    "\1\41\2\0\5\6\1\31\15\6\1\30\6\6\1\0\1\12\2\0"+
    "\1\6\1\0\1\32\1\53\1\6\1\14\1\15\1\16\1\6\1\27"+
    "\1\23\2\6\1\24\1\6\1\22\1\43\2\6\1\17\1\25\1\20"+
    "\1\21\1\6\1\26\3\6\1\35\1\0\1\36\1\0\6\7\1\56"+
    "\32\7\2\0\4\6\4\0\1\6\2\0\1\7\7\0\1\6\4\0"+
    "\1\6\5\0\27\6\1\0\37\6\1\0\u01ca\6\4\0\14\6\16\0"+
    "\5\6\7\0\1\6\1\0\1\6\21\0\160\7\5\6\1\0\2\6"+
    "\2\0\4\6\10\0\1\6\1\0\3\6\1\0\1\6\1\0\24\6"+
    "\1\0\123\6\1\0\213\6\1\0\5\7\2\0\236\6\11\0\46\6"+
    "\2\0\1\6\7\0\47\6\7\0\1\6\1\0\55\7\1\0\1\7"+
    "\1\0\2\7\1\0\2\7\1\0\1\7\10\0\33\6\5\0\3\6"+
    "\15\0\5\7\6\0\1\6\4\0\13\7\5\0\53\6\37\7\4\0"+
    "\2\6\1\7\143\6\1\0\1\6\10\7\1\0\6\7\2\6\2\7"+
    "\1\0\4\7\2\6\12\7\3\6\2\0\1\6\17\0\1\7\1\6"+
    "\1\7\36\6\33\7\2\0\131\6\13\7\1\6\16\0\12\7\41\6"+
    "\11\7\2\6\4\0\1\6\5\0\26\6\4\7\1\6\11\7\1\6"+
    "\3\7\1\6\5\7\22\0\31\6\3\7\104\0\1\6\1\0\13\6"+
    "\67\0\33\7\1\0\4\7\66\6\3\7\1\6\22\7\1\6\7\7"+
    "\12\6\2\7\2\0\12\7\1\0\7\6\1\0\7\6\1\0\3\7"+
    "\1\0\10\6\2\0\2\6\2\0\26\6\1\0\7\6\1\0\1\6"+
    "\3\0\4\6\2\0\1\7\1\6\7\7\2\0\2\7\2\0\3\7"+
    "\1\6\10\0\1\7\4\0\2\6\1\0\3\6\2\7\2\0\12\7"+
    "\4\6\7\0\1\6\5\0\3\7\1\0\6\6\4\0\2\6\2\0"+
    "\26\6\1\0\7\6\1\0\2\6\1\0\2\6\1\0\2\6\2\0"+
    "\1\7\1\0\5\7\4\0\2\7\2\0\3\7\3\0\1\7\7\0"+
    "\4\6\1\0\1\6\7\0\14\7\3\6\1\7\13\0\3\7\1\0"+
    "\11\6\1\0\3\6\1\0\26\6\1\0\7\6\1\0\2\6\1\0"+
    "\5\6\2\0\1\7\1\6\10\7\1\0\3\7\1\0\3\7\2\0"+
    "\1\6\17\0\2\6\2\7\2\0\12\7\1\0\1\6\17\0\3\7"+
    "\1\0\10\6\2\0\2\6\2\0\26\6\1\0\7\6\1\0\2\6"+
    "\1\0\5\6\2\0\1\7\1\6\7\7\2\0\2\7\2\0\3\7"+
    "\10\0\2\7\4\0\2\6\1\0\3\6\2\7\2\0\12\7\1\0"+
    "\1\6\20\0\1\7\1\6\1\0\6\6\3\0\3\6\1\0\4\6"+
    "\3\0\2\6\1\0\1\6\1\0\2\6\3\0\2\6\3\0\3\6"+
    "\3\0\14\6\4\0\5\7\3\0\3\7\1\0\4\7\2\0\1\6"+
    "\6\0\1\7\16\0\12\7\11\0\1\6\7\0\3\7\1\0\10\6"+
    "\1\0\3\6\1\0\27\6\1\0\12\6\1\0\5\6\3\0\1\6"+
    "\7\7\1\0\3\7\1\0\4\7\7\0\2\7\1\0\2\6\6\0"+
    "\2\6\2\7\2\0\12\7\22\0\2\7\1\0\10\6\1\0\3\6"+
    "\1\0\27\6\1\0\12\6\1\0\5\6\2\0\1\7\1\6\7\7"+
    "\1\0\3\7\1\0\4\7\7\0\2\7\7\0\1\6\1\0\2\6"+
    "\2\7\2\0\12\7\1\0\2\6\17\0\2\7\1\0\10\6\1\0"+
    "\3\6\1\0\51\6\2\0\1\6\7\7\1\0\3\7\1\0\4\7"+
    "\1\6\10\0\1\7\10\0\2\6\2\7\2\0\12\7\12\0\6\6"+
    "\2\0\2\7\1\0\22\6\3\0\30\6\1\0\11\6\1\0\1\6"+
    "\2\0\7\6\3\0\1\7\4\0\6\7\1\0\1\7\1\0\10\7"+
    "\22\0\2\7\15\0\60\6\1\7\2\6\7\7\4\0\10\6\10\7"+
    "\1\0\12\7\47\0\2\6\1\0\1\6\2\0\2\6\1\0\1\6"+
    "\2\0\1\6\6\0\4\6\1\0\7\6\1\0\3\6\1\0\1\6"+
    "\1\0\1\6\2\0\2\6\1\0\4\6\1\7\2\6\6\7\1\0"+
    "\2\7\1\6\2\0\5\6\1\0\1\6\1\0\6\7\2\0\12\7"+
    "\2\0\4\6\40\0\1\6\27\0\2\7\6\0\12\7\13\0\1\7"+
    "\1\0\1\7\1\0\1\7\4\0\2\7\10\6\1\0\44\6\4\0"+
    "\24\7\1\0\2\7\5\6\13\7\1\0\44\7\11\0\1\7\71\0"+
    "\53\6\24\7\1\6\12\7\6\0\6\6\4\7\4\6\3\7\1\6"+
    "\3\7\2\6\7\7\3\6\4\7\15\6\14\7\1\6\17\7\2\0"+
    "\46\6\1\0\1\6\5\0\1\6\2\0\53\6\1\0\u014d\6\1\0"+
    "\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0\51\6\1\0"+
    "\4\6\2\0\41\6\1\0\4\6\2\0\7\6\1\0\1\6\1\0"+
    "\4\6\2\0\17\6\1\0\71\6\1\0\4\6\2\0\103\6\2\0"+
    "\3\7\40\0\20\6\20\0\125\6\14\0\u026c\6\2\0\21\6\1\0"+
    "\32\6\5\0\113\6\3\0\3\6\17\0\15\6\1\0\4\6\3\7"+
    "\13\0\22\6\3\7\13\0\22\6\2\7\14\0\15\6\1\0\3\6"+
    "\1\0\2\7\14\0\64\6\40\7\3\0\1\6\3\0\2\6\1\7"+
    "\2\0\12\7\41\0\3\7\2\0\12\7\6\0\130\6\10\0\51\6"+
    "\1\7\1\6\5\0\106\6\12\0\35\6\3\0\14\7\4\0\14\7"+
    "\12\0\12\7\36\6\2\0\5\6\13\0\54\6\4\0\21\7\7\6"+
    "\2\7\6\0\12\7\46\0\27\6\5\7\4\0\65\6\12\7\1\0"+
    "\35\7\2\0\13\7\6\0\12\7\15\0\1\6\130\0\5\7\57\6"+
    "\21\7\7\6\4\0\12\7\21\0\11\7\14\0\3\7\36\6\15\7"+
    "\2\6\12\7\54\6\16\7\14\0\44\6\24\7\10\0\12\7\3\0"+
    "\3\6\12\7\44\6\122\0\3\7\1\0\25\7\4\6\1\7\4\6"+
    "\3\7\2\6\11\0\300\6\47\7\25\0\4\7\u0116\6\2\0\6\6"+
    "\2\0\46\6\2\0\6\6\2\0\10\6\1\0\1\6\1\0\1\6"+
    "\1\0\1\6\1\0\37\6\2\0\65\6\1\0\7\6\1\0\1\6"+
    "\3\0\3\6\1\0\7\6\3\0\4\6\2\0\6\6\4\0\15\6"+
    "\5\0\3\6\1\0\7\6\16\0\5\7\30\0\1\54\1\54\5\7"+
    "\20\0\2\6\23\0\1\6\13\0\5\7\5\0\6\7\1\0\1\6"+
    "\15\0\1\6\20\0\15\6\3\0\33\6\25\0\15\7\4\0\1\7"+
    "\3\0\14\7\21\0\1\6\4\0\1\6\2\0\12\6\1\0\1\6"+
    "\3\0\5\6\6\0\1\6\1\0\1\6\1\0\1\6\1\0\4\6"+
    "\1\0\13\6\2\0\4\6\5\0\5\6\4\0\1\6\21\0\51\6"+
    "\u0a77\0\57\6\1\0\57\6\1\0\205\6\6\0\4\6\3\7\2\6"+
    "\14\0\46\6\1\0\1\6\5\0\1\6\2\0\70\6\7\0\1\6"+
    "\17\0\1\7\27\6\11\0\7\6\1\0\7\6\1\0\7\6\1\0"+
    "\7\6\1\0\7\6\1\0\7\6\1\0\7\6\1\0\7\6\1\0"+
    "\40\7\57\0\1\6\u01d5\0\3\6\31\0\11\6\6\7\1\0\5\6"+
    "\2\0\5\6\4\0\126\6\2\0\2\7\2\0\3\6\1\0\132\6"+
    "\1\0\4\6\5\0\51\6\3\0\136\6\21\0\33\6\65\0\20\6"+
    "\u0200\0\u19b6\6\112\0\u51cd\6\63\0\u048d\6\103\0\56\6\2\0\u010d\6"+
    "\3\0\20\6\12\7\2\6\24\0\57\6\1\7\4\0\12\7\1\0"+
    "\31\6\7\0\1\7\120\6\2\7\45\0\11\6\2\0\147\6\2\0"+
    "\4\6\1\0\4\6\14\0\13\6\115\0\12\6\1\7\3\6\1\7"+
    "\4\6\1\7\27\6\5\7\20\0\1\6\7\0\64\6\14\0\2\7"+
    "\62\6\21\7\13\0\12\7\6\0\22\7\6\6\3\0\1\6\4\0"+
    "\12\7\34\6\10\7\2\0\27\6\15\7\14\0\35\6\3\0\4\7"+
    "\57\6\16\7\16\0\1\6\12\7\46\0\51\6\16\7\11\0\3\6"+
    "\1\7\10\6\2\7\2\0\12\7\6\0\27\6\3\0\1\6\1\7"+
    "\4\0\60\6\1\7\1\6\3\7\2\6\2\7\5\6\2\7\1\6"+
    "\1\7\1\6\30\0\3\6\2\0\13\6\5\7\2\0\3\6\2\7"+
    "\12\0\6\6\2\0\6\6\2\0\6\6\11\0\7\6\1\0\7\6"+
    "\221\0\43\6\10\7\1\0\2\7\2\0\12\7\6\0\u2ba4\6\14\0"+
    "\27\6\4\0\61\6\u2104\0\u016e\6\2\0\152\6\46\0\7\6\14\0"+
    "\5\6\5\0\1\6\1\7\12\6\1\0\15\6\1\0\5\6\1\0"+
    "\1\6\1\0\2\6\1\0\2\6\1\0\154\6\41\0\u016b\6\22\0"+
    "\100\6\2\0\66\6\50\0\15\6\3\0\20\7\20\0\7\7\14\0"+
    "\2\6\30\0\3\6\31\0\1\6\6\0\5\6\1\0\207\6\2\0"+
    "\1\7\4\0\1\6\13\0\12\7\7\0\32\6\4\0\1\6\1\0"+
    "\32\6\13\0\131\6\3\0\6\6\2\0\6\6\2\0\6\6\2\0"+
    "\3\6\3\0\2\6\3\0\2\6\22\0\3\7\4\0\14\6\1\0"+
    "\32\6\1\0\23\6\1\0\2\6\1\0\17\6\2\0\16\6\42\0"+
    "\173\6\105\0\65\6\210\0\1\7\202\0\35\6\3\0\61\6\57\0"+
    "\37\6\21\0\33\6\65\0\36\6\2\0\44\6\4\0\10\6\1\0"+
    "\5\6\52\0\236\6\2\0\12\7\u0356\0\6\6\2\0\1\6\1\0"+
    "\54\6\1\0\2\6\3\0\1\6\2\0\27\6\252\0\26\6\12\0"+
    "\32\6\106\0\70\6\6\0\2\6\100\0\1\6\3\7\1\0\2\7"+
    "\5\0\4\7\4\6\1\0\3\6\1\0\33\6\4\0\3\7\4\0"+
    "\1\7\40\0\35\6\203\0\66\6\12\0\26\6\12\0\23\6\215\0"+
    "\111\6\u03b7\0\3\7\65\6\17\7\37\0\12\7\20\0\3\7\55\6"+
    "\13\7\2\0\1\7\22\0\31\6\7\0\12\7\6\0\3\7\44\6"+
    "\16\7\1\0\12\7\100\0\3\7\60\6\16\7\4\6\13\0\12\7"+
    "\u04a6\0\53\6\15\7\10\0\12\7\u0936\0\u036f\6\221\0\143\6\u0b9d\0"+
    "\u042f\6\u33d1\0\u0239\6\u04c7\0\105\6\13\0\1\6\56\7\20\0\4\7"+
    "\15\6\u4060\0\2\6\u2163\0\5\7\3\0\26\7\2\0\7\7\36\0"+
    "\4\7\224\0\3\7\u01bb\0\125\6\1\0\107\6\1\0\2\6\2\0"+
    "\1\6\2\0\2\6\2\0\4\6\1\0\14\6\1\0\1\6\1\0"+
    "\7\6\1\0\101\6\1\0\4\6\2\0\10\6\1\0\7\6\1\0"+
    "\34\6\1\0\4\6\1\0\5\6\1\0\1\6\3\0\7\6\1\0"+
    "\u0154\6\2\0\31\6\1\0\31\6\1\0\37\6\1\0\31\6\1\0"+
    "\37\6\1\0\31\6\1\0\37\6\1\0\31\6\1\0\37\6\1\0"+
    "\31\6\1\0\10\6\2\0\62\7\u1600\0\4\6\1\0\33\6\1\0"+
    "\2\6\1\0\1\6\2\0\1\6\1\0\12\6\1\0\4\6\1\0"+
    "\1\6\1\0\1\6\6\0\1\6\4\0\1\6\1\0\1\6\1\0"+
    "\1\6\1\0\3\6\1\0\2\6\1\0\1\6\2\0\1\6\1\0"+
    "\1\6\1\0\1\6\1\0\1\6\1\0\1\6\1\0\2\6\1\0"+
    "\1\6\2\0\4\6\1\0\7\6\1\0\4\6\1\0\4\6\1\0"+
    "\1\6\1\0\12\6\1\0\21\6\5\0\3\6\1\0\5\6\1\0"+
    "\21\6\u1144\0\ua6d7\6\51\0\u1035\6\13\0\336\6\u3fe2\0\u021e\6\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05ee\0"+
    "\1\7\36\0\140\7\200\0\360\7\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\2\2\1\3\1\2\1\4\2\5\1\6"+
    "\11\4\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\4\1\17\1\1\1\20\1\21\1\22\1\1"+
    "\1\23\1\24\2\25\1\26\1\1\1\24\1\0\4\4"+
    "\1\27\4\4\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
    "\1\46\1\0\1\47\2\4\1\50\3\4\1\51\1\0"+
    "\1\52\2\4\1\53\1\4\1\0\1\4\1\54\1\55"+
    "\1\0\1\56\2\2";

  private static int [] zzUnpackAction() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\215\0\353\0\u011a"+
    "\0\u0149\0\215\0\u0178\0\215\0\u01a7\0\u01d6\0\u0205\0\u0234"+
    "\0\u0263\0\u0292\0\u02c1\0\u02f0\0\u031f\0\215\0\215\0\215"+
    "\0\215\0\215\0\u034e\0\u037d\0\u03ac\0\u03db\0\215\0\u040a"+
    "\0\215\0\215\0\215\0\u0439\0\215\0\u0468\0\u0497\0\215"+
    "\0\215\0\u04c6\0\u04f5\0\u0524\0\u0553\0\u0582\0\u05b1\0\u05e0"+
    "\0\u0149\0\u060f\0\u063e\0\u066d\0\u069c\0\215\0\215\0\215"+
    "\0\u0149\0\215\0\215\0\215\0\215\0\215\0\215\0\215"+
    "\0\215\0\215\0\215\0\215\0\u06cb\0\u0149\0\u06fa\0\u0729"+
    "\0\u0149\0\u0758\0\u0787\0\u07b6\0\u0149\0\u07e5\0\u0149\0\u0814"+
    "\0\u0843\0\u0149\0\u0872\0\u08a1\0\u08d0\0\u0149\0\u0149\0\u08ff"+
    "\0\u0149\0\u092e\0\u095d";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\2\6\1\7\1\10\1\11\1\4\1\12"+
    "\1\13\1\4\1\14\1\15\1\16\1\11\1\17\2\11"+
    "\1\20\1\21\2\11\1\22\1\11\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45"+
    "\1\11\1\4\1\6\1\4\1\46\1\47\1\50\1\46"+
    "\1\51\5\46\1\52\44\46\1\53\1\47\1\50\7\53"+
    "\1\52\1\51\43\53\61\0\1\6\60\0\1\54\52\0"+
    "\1\10\1\5\1\6\54\10\6\0\4\11\2\0\17\11"+
    "\10\0\1\11\7\0\1\11\2\0\1\11\10\0\2\13"+
    "\53\0\4\11\2\0\1\11\1\55\15\11\10\0\1\11"+
    "\7\0\1\11\2\0\1\11\6\0\4\11\2\0\10\11"+
    "\1\56\6\11\10\0\1\11\7\0\1\11\2\0\1\11"+
    "\6\0\4\11\2\0\1\11\1\57\15\11\10\0\1\11"+
    "\7\0\1\11\2\0\1\11\6\0\4\11\2\0\17\11"+
    "\10\0\1\60\7\0\1\11\2\0\1\11\6\0\4\11"+
    "\2\0\2\11\1\61\14\11\10\0\1\11\7\0\1\11"+
    "\2\0\1\11\6\0\4\11\2\0\13\11\1\62\3\11"+
    "\10\0\1\11\7\0\1\11\2\0\1\11\6\0\4\11"+
    "\2\0\3\11\1\63\13\11\10\0\1\11\7\0\1\11"+
    "\2\0\1\11\6\0\4\11\2\0\16\11\1\64\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\6\0\4\11\2\0"+
    "\6\11\1\65\10\11\10\0\1\11\7\0\1\11\2\0"+
    "\1\11\40\0\1\66\56\0\1\67\56\0\1\70\24\0"+
    "\4\11\2\0\3\11\1\71\13\11\10\0\1\11\7\0"+
    "\1\11\2\0\1\11\40\0\1\72\67\0\1\73\5\0"+
    "\1\46\2\0\1\46\1\0\5\46\1\0\44\46\2\0"+
    "\1\50\54\0\1\74\2\0\1\74\1\75\5\74\1\76"+
    "\1\77\2\74\1\100\1\101\1\102\1\74\1\103\30\74"+
    "\1\104\3\0\1\53\2\0\7\53\2\0\43\53\4\0"+
    "\1\105\60\0\4\11\2\0\2\11\1\106\14\11\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\6\0\4\11\2\0"+
    "\11\11\1\107\5\11\10\0\1\11\7\0\1\11\2\0"+
    "\1\11\6\0\4\11\2\0\4\11\1\110\12\11\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\6\0\4\11\2\0"+
    "\4\11\1\111\12\11\10\0\1\11\7\0\1\11\2\0"+
    "\1\11\6\0\4\11\2\0\7\11\1\112\7\11\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\6\0\4\11\2\0"+
    "\5\11\1\113\11\11\10\0\1\11\7\0\1\11\2\0"+
    "\1\11\6\0\4\11\2\0\10\11\1\114\6\11\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\6\0\4\11\2\0"+
    "\1\115\16\11\10\0\1\11\7\0\1\11\2\0\1\11"+
    "\4\116\1\0\52\116\6\0\4\11\2\0\1\11\1\117"+
    "\15\11\10\0\1\11\7\0\1\11\2\0\1\11\6\0"+
    "\4\11\2\0\5\11\1\120\11\11\10\0\1\11\7\0"+
    "\1\11\2\0\1\11\6\0\4\11\2\0\10\11\1\121"+
    "\6\11\10\0\1\11\7\0\1\11\2\0\1\11\6\0"+
    "\4\11\2\0\1\11\1\122\15\11\10\0\1\11\7\0"+
    "\1\11\2\0\1\11\6\0\4\11\2\0\11\11\1\123"+
    "\5\11\10\0\1\11\7\0\1\11\2\0\1\11\4\116"+
    "\1\124\52\116\6\0\4\11\2\0\3\11\1\125\13\11"+
    "\10\0\1\11\7\0\1\11\2\0\1\11\6\0\4\11"+
    "\2\0\1\11\1\126\15\11\10\0\1\11\7\0\1\11"+
    "\2\0\1\11\6\0\4\11\2\0\1\11\1\127\15\11"+
    "\10\0\1\11\7\0\1\11\2\0\1\11\4\116\1\130"+
    "\52\116\6\0\4\11\2\0\6\11\1\131\10\11\10\0"+
    "\1\11\7\0\1\11\2\0\1\11\4\116\1\132\52\116"+
    "\4\0\1\133\56\0\1\6\52\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2444];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\1\1\1\11\3\1\1\11\1\1\1\11"+
    "\11\1\5\11\4\1\1\11\1\1\3\11\1\1\1\11"+
    "\2\1\2\11\2\1\1\0\11\1\3\11\1\1\13\11"+
    "\1\0\10\1\1\0\5\1\1\0\3\1\1\0\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[91];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
  StringBuilder string = new StringBuilder();
  
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2858) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(EOF);
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn);
            }
          case 47: break;
          case 2: 
            { /* ignore */
            }
          case 48: break;
          case 3: 
            { yybegin(DSTRING); string.setLength(0);
            }
          case 49: break;
          case 4: 
            { return symbol(IDENTIFIER, yytext());
            }
          case 50: break;
          case 5: 
            { return symbol(INT_LITERAL, new Integer(yytext()));
            }
          case 51: break;
          case 6: 
            { yybegin(SSTRING); string.setLength(0);
            }
          case 52: break;
          case 7: 
            { return symbol(LPAREN);
            }
          case 53: break;
          case 8: 
            { return symbol(RPAREN);
            }
          case 54: break;
          case 9: 
            { return symbol(LBRACE);
            }
          case 55: break;
          case 10: 
            { return symbol(RBRACE);
            }
          case 56: break;
          case 11: 
            { return symbol(COMMA);
            }
          case 57: break;
          case 12: 
            { return symbol(ASSIGN);
            }
          case 58: break;
          case 13: 
            { return symbol(GT);
            }
          case 59: break;
          case 14: 
            { return symbol(LT);
            }
          case 60: break;
          case 15: 
            { return symbol(COLON);
            }
          case 61: break;
          case 16: 
            { return symbol(PLUS);
            }
          case 62: break;
          case 17: 
            { return symbol(MINUS);
            }
          case 63: break;
          case 18: 
            { return symbol(TIMES);
            }
          case 64: break;
          case 19: 
            { return symbol(MOD);
            }
          case 65: break;
          case 20: 
            { string.append( yytext() );
            }
          case 66: break;
          case 21: 
            { throw new RuntimeException("Unterminated string at end of line");
            }
          case 67: break;
          case 22: 
            { yybegin(YYINITIAL); return symbol(STRING_LITERAL, string.toString());
            }
          case 68: break;
          case 23: 
            { return symbol(IF);
            }
          case 69: break;
          case 24: 
            { return symbol(EQ);
            }
          case 70: break;
          case 25: 
            { return symbol(GEQ);
            }
          case 71: break;
          case 26: 
            { return symbol(LEQ);
            }
          case 72: break;
          case 27: 
            { return symbol(OR);
            }
          case 73: break;
          case 28: 
            { return symbol(NEQ);
            }
          case 74: break;
          case 29: 
            { return symbol(DIV);
            }
          case 75: break;
          case 30: 
            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");
            }
          case 76: break;
          case 31: 
            { string.append( '\"' );
            }
          case 77: break;
          case 32: 
            { string.append( '\\' );
            }
          case 78: break;
          case 33: 
            { string.append( '\'' );
            }
          case 79: break;
          case 34: 
            { string.append( '\f' );
            }
          case 80: break;
          case 35: 
            { string.append( '\r' );
            }
          case 81: break;
          case 36: 
            { string.append( '\t' );
            }
          case 82: break;
          case 37: 
            { string.append( '\n' );
            }
          case 83: break;
          case 38: 
            { string.append( '\b' );
            }
          case 84: break;
          case 39: 
            { return symbol(DEF);
            }
          case 85: break;
          case 40: 
            { return symbol(NOT);
            }
          case 86: break;
          case 41: 
            { return symbol(AND);
            }
          case 87: break;
          case 42: 
            { return symbol(ELSE);
            }
          case 88: break;
          case 43: 
            { return symbol(BOOL_LITERAL, true);
            }
          case 89: break;
          case 44: 
            { return symbol(WHILE);
            }
          case 90: break;
          case 45: 
            { return symbol(BOOL_LITERAL, false);
            }
          case 91: break;
          case 46: 
            { return symbol(RETURN);
            }
          case 92: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }

  /**
   * Converts an int token code into the name of the
   * token by reflection on the cup symbol class/interface sym
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  private String getTokenName(int token) {
    try {
      java.lang.reflect.Field [] classFields = sym.class.getFields();
      for (int i = 0; i < classFields.length; i++) {
        if (classFields[i].getInt(null) == token) {
          return classFields[i].getName();
        }
      }
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return "UNKNOWN TOKEN";
  }

  /**
   * Same as next_token but also prints the token to standard out
   * for debugging.
   *
   * This code was contributed by Karl Meissner <meissnersd@yahoo.com>
   */
  public java_cup.runtime.Symbol debug_next_token() throws java.io.IOException {
    java_cup.runtime.Symbol s = next_token();
    System.out.println( "line:" + (yyline+1) + " col:" + (yycolumn+1) + " --"+ yytext() + "--" + getTokenName(s.sym) + "--");
    return s;
  }

  /**
   * Runs the scanner on input files.
   *
   * This main method is the debugging routine for the scanner.
   * It prints debugging information about each returned token to
   * System.out until the end of file is reached, or an error occured.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    if (argv.length == 0) {
      System.out.println("Usage : java scanner [ --encoding <name> ] <inputfile(s)>");
    }
    else {
      int firstFilePos = 0;
      String encodingName = "UTF-8";
      if (argv[0].equals("--encoding")) {
        firstFilePos = 2;
        encodingName = argv[1];
        try {
          java.nio.charset.Charset.forName(encodingName); // Side-effect: is encodingName valid? 
        } catch (Exception e) {
          System.out.println("Invalid encoding '" + encodingName + "'");
          return;
        }
      }
      for (int i = firstFilePos; i < argv.length; i++) {
        scanner scanner = null;
        try {
          java.io.FileInputStream stream = new java.io.FileInputStream(argv[i]);
          java.io.Reader reader = new java.io.InputStreamReader(stream, encodingName);
          scanner = new scanner(reader);
          while ( !scanner.zzAtEOF ) scanner.debug_next_token();
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


}
