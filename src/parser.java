
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20150930 (SVN rev 66)
//----------------------------------------------------

import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20150930 (SVN rev 66) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\062\000\002\002\004\000\002\002\003\000\002\002" +
    "\003\000\002\003\004\000\002\003\005\000\002\005\004" +
    "\000\002\005\003\000\002\007\004\000\002\007\003\000" +
    "\002\004\010\000\002\004\011\000\002\006\003\000\002" +
    "\006\003\000\002\006\003\000\002\006\003\000\002\006" +
    "\003\000\002\016\005\000\002\010\006\000\002\010\011" +
    "\000\002\011\006\000\002\012\004\000\002\012\003\000" +
    "\002\015\005\000\002\015\004\000\002\015\003\000\002" +
    "\015\003\000\002\015\005\000\002\017\003\000\002\017" +
    "\003\000\002\017\003\000\002\017\003\000\002\017\003" +
    "\000\002\017\003\000\002\017\003\000\002\017\003\000" +
    "\002\017\003\000\002\017\003\000\002\017\003\000\002" +
    "\017\003\000\002\017\003\000\002\020\003\000\002\020" +
    "\003\000\002\021\004\000\002\021\004\000\002\013\003" +
    "\000\002\013\004\000\002\022\003\000\002\022\003\000" +
    "\002\022\003\000\002\022\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\115\000\006\011\004\026\007\001\002\000\032\006" +
    "\041\007\043\012\034\023\042\025\036\030\052\044\040" +
    "\045\045\046\033\047\044\050\014\051\054\001\002\000" +
    "\004\002\031\001\002\000\004\002\000\001\002\000\004" +
    "\050\014\001\002\000\006\002\ufffb\026\007\001\002\000" +
    "\004\002\uffff\001\002\000\004\002\ufffc\001\002\000\006" +
    "\007\015\015\017\001\002\000\072\004\uffd5\006\uffd5\007" +
    "\uffd5\010\uffd5\012\uffd5\015\uffd5\022\uffd5\023\uffd5\025\uffd5" +
    "\027\uffd5\030\uffd5\031\uffd5\032\uffd5\033\uffd5\034\uffd5\035" +
    "\uffd5\036\uffd5\037\uffd5\040\uffd5\041\uffd5\042\uffd5\043\uffd5" +
    "\044\uffd5\045\uffd5\046\uffd5\047\uffd5\050\uffd5\051\uffd5\001" +
    "\002\000\004\010\022\001\002\000\072\004\uffd4\006\uffd4" +
    "\007\uffd4\010\uffd4\012\uffd4\015\uffd4\022\uffd4\023\uffd4\025" +
    "\uffd4\027\uffd4\030\uffd4\031\uffd4\032\uffd4\033\uffd4\034\uffd4" +
    "\035\uffd4\036\uffd4\037\uffd4\040\uffd4\041\uffd4\042\uffd4\043" +
    "\uffd4\044\uffd4\045\uffd4\046\uffd4\047\uffd4\050\uffd4\051\uffd4" +
    "\001\002\000\006\016\020\017\021\001\002\000\072\004" +
    "\uffd7\006\uffd7\007\uffd7\010\uffd7\012\uffd7\015\uffd7\022\uffd7" +
    "\023\uffd7\025\uffd7\027\uffd7\030\uffd7\031\uffd7\032\uffd7\033" +
    "\uffd7\034\uffd7\035\uffd7\036\uffd7\037\uffd7\040\uffd7\041\uffd7" +
    "\042\uffd7\043\uffd7\044\uffd7\045\uffd7\046\uffd7\047\uffd7\050" +
    "\uffd7\051\uffd7\001\002\000\072\004\uffd6\006\uffd6\007\uffd6" +
    "\010\uffd6\012\uffd6\015\uffd6\022\uffd6\023\uffd6\025\uffd6\027" +
    "\uffd6\030\uffd6\031\uffd6\032\uffd6\033\uffd6\034\uffd6\035\uffd6" +
    "\036\uffd6\037\uffd6\040\uffd6\041\uffd6\042\uffd6\043\uffd6\044" +
    "\uffd6\045\uffd6\046\uffd6\047\uffd6\050\uffd6\051\uffd6\001\002" +
    "\000\004\022\027\001\002\000\004\010\024\001\002\000" +
    "\004\022\025\001\002\000\004\011\004\001\002\000\006" +
    "\002\ufff7\026\ufff7\001\002\000\004\011\004\001\002\000" +
    "\006\002\ufff8\026\ufff8\001\002\000\004\002\001\001\002" +
    "\000\032\006\041\007\043\012\ufff9\023\042\025\036\030" +
    "\052\044\040\045\045\046\033\047\044\050\014\051\054" +
    "\001\002\000\066\006\uffd2\007\uffd2\010\uffd2\012\uffd2\022" +
    "\uffd2\023\uffd2\025\uffd2\027\uffd2\030\uffd2\031\uffd2\032\uffd2" +
    "\033\uffd2\034\uffd2\035\uffd2\036\uffd2\037\uffd2\040\uffd2\041" +
    "\uffd2\042\uffd2\043\uffd2\044\uffd2\045\uffd2\046\uffd2\047\uffd2" +
    "\050\uffd2\051\uffd2\001\002\000\040\002\ufffe\006\ufffe\007" +
    "\ufffe\012\ufffe\023\ufffe\024\ufffe\025\ufffe\026\ufffe\030\ufffe" +
    "\044\ufffe\045\ufffe\046\ufffe\047\ufffe\050\ufffe\051\ufffe\001" +
    "\002\000\032\006\ufff5\007\ufff5\012\ufff5\023\ufff5\025\ufff5" +
    "\030\ufff5\044\ufff5\045\ufff5\046\ufff5\047\ufff5\050\ufff5\051" +
    "\ufff5\001\002\000\022\007\043\030\052\044\040\045\045" +
    "\046\033\047\044\050\014\051\054\001\002\000\032\006" +
    "\ufff4\007\ufff4\012\ufff4\023\ufff4\025\ufff4\030\ufff4\044\ufff4" +
    "\045\ufff4\046\ufff4\047\ufff4\050\ufff4\051\ufff4\001\002\000" +
    "\022\007\uffd8\030\uffd8\044\uffd8\045\uffd8\046\uffd8\047\uffd8" +
    "\050\uffd8\051\uffd8\001\002\000\032\006\uffec\007\043\012" +
    "\uffec\023\uffec\025\uffec\030\052\044\040\045\045\046\033" +
    "\047\044\050\014\051\054\001\002\000\022\007\043\030" +
    "\052\044\040\045\045\046\033\047\044\050\014\051\054" +
    "\001\002\000\022\007\043\030\052\044\040\045\045\046" +
    "\033\047\044\050\014\051\054\001\002\000\066\006\uffd1" +
    "\007\uffd1\010\uffd1\012\uffd1\022\uffd1\023\uffd1\025\uffd1\027" +
    "\uffd1\030\uffd1\031\uffd1\032\uffd1\033\uffd1\034\uffd1\035\uffd1" +
    "\036\uffd1\037\uffd1\040\uffd1\041\uffd1\042\uffd1\043\uffd1\044" +
    "\uffd1\045\uffd1\046\uffd1\047\uffd1\050\uffd1\051\uffd1\001\002" +
    "\000\066\006\uffd3\007\uffd3\010\uffd3\012\uffd3\022\uffd3\023" +
    "\uffd3\025\uffd3\027\uffd3\030\uffd3\031\uffd3\032\uffd3\033\uffd3" +
    "\034\uffd3\035\uffd3\036\uffd3\037\uffd3\040\uffd3\041\uffd3\042" +
    "\uffd3\043\uffd3\044\uffd3\045\uffd3\046\uffd3\047\uffd3\050\uffd3" +
    "\051\uffd3\001\002\000\004\012\102\001\002\000\062\006" +
    "\ufff2\007\ufff2\012\ufff2\023\ufff2\025\ufff2\027\067\030\063" +
    "\031\064\032\072\033\065\034\075\035\073\036\077\037" +
    "\074\040\070\041\066\042\062\043\076\044\ufff2\045\ufff2" +
    "\046\ufff2\047\ufff2\050\ufff2\051\ufff2\001\002\000\022\007" +
    "\043\030\052\044\040\045\045\046\033\047\044\050\014" +
    "\051\054\001\002\000\032\006\ufff6\007\ufff6\012\ufff6\023" +
    "\ufff6\025\ufff6\030\ufff6\044\ufff6\045\ufff6\046\ufff6\047\ufff6" +
    "\050\ufff6\051\ufff6\001\002\000\022\007\uffd9\030\uffd9\044" +
    "\uffd9\045\uffd9\046\uffd9\047\uffd9\050\uffd9\051\uffd9\001\002" +
    "\000\066\006\uffe8\007\uffe8\010\uffe8\012\uffe8\022\uffe8\023" +
    "\uffe8\025\uffe8\027\uffe8\030\uffe8\031\uffe8\032\uffe8\033\uffe8" +
    "\034\uffe8\035\uffe8\036\uffe8\037\uffe8\040\uffe8\041\uffe8\042" +
    "\uffe8\043\uffe8\044\uffe8\045\uffe8\046\uffe8\047\uffe8\050\uffe8" +
    "\051\uffe8\001\002\000\066\006\uffd0\007\uffd0\010\uffd0\012" +
    "\uffd0\022\uffd0\023\uffd0\025\uffd0\027\uffd0\030\uffd0\031\uffd0" +
    "\032\uffd0\033\uffd0\034\uffd0\035\uffd0\036\uffd0\037\uffd0\040" +
    "\uffd0\041\uffd0\042\uffd0\043\uffd0\044\uffd0\045\uffd0\046\uffd0" +
    "\047\uffd0\050\uffd0\051\uffd0\001\002\000\032\006\ufff3\007" +
    "\ufff3\012\ufff3\023\ufff3\025\ufff3\030\ufff3\044\ufff3\045\ufff3" +
    "\046\ufff3\047\ufff3\050\ufff3\051\ufff3\001\002\000\066\004" +
    "\057\006\uffe9\007\uffe9\012\uffe9\015\017\023\uffe9\025\uffe9" +
    "\027\uffe9\030\uffe9\031\uffe9\032\uffe9\033\uffe9\034\uffe9\035" +
    "\uffe9\036\uffe9\037\uffe9\040\uffe9\041\uffe9\042\uffe9\043\uffe9" +
    "\044\uffe9\045\uffe9\046\uffe9\047\uffe9\050\uffe9\051\uffe9\001" +
    "\002\000\022\007\043\030\052\044\040\045\045\046\033" +
    "\047\044\050\014\051\054\001\002\000\070\006\uffe9\007" +
    "\uffe9\010\uffe9\012\uffe9\015\017\022\uffe9\023\uffe9\025\uffe9" +
    "\027\uffe9\030\uffe9\031\uffe9\032\uffe9\033\uffe9\034\uffe9\035" +
    "\uffe9\036\uffe9\037\uffe9\040\uffe9\041\uffe9\042\uffe9\043\uffe9" +
    "\044\uffe9\045\uffe9\046\uffe9\047\uffe9\050\uffe9\051\uffe9\001" +
    "\002\000\062\006\ufff1\007\ufff1\012\ufff1\023\ufff1\025\ufff1" +
    "\027\067\030\063\031\064\032\072\033\065\034\075\035" +
    "\073\036\077\037\074\040\070\041\066\042\062\043\076" +
    "\044\ufff1\045\ufff1\046\ufff1\047\ufff1\050\ufff1\051\ufff1\001" +
    "\002\000\022\007\uffe6\030\uffe6\044\uffe6\045\uffe6\046\uffe6" +
    "\047\uffe6\050\uffe6\051\uffe6\001\002\000\022\007\uffe3\030" +
    "\uffe3\044\uffe3\045\uffe3\046\uffe3\047\uffe3\050\uffe3\051\uffe3" +
    "\001\002\000\022\007\uffe2\030\uffe2\044\uffe2\045\uffe2\046" +
    "\uffe2\047\uffe2\050\uffe2\051\uffe2\001\002\000\022\007\uffe0" +
    "\030\uffe0\044\uffe0\045\uffe0\046\uffe0\047\uffe0\050\uffe0\051" +
    "\uffe0\001\002\000\022\007\uffdc\030\uffdc\044\uffdc\045\uffdc" +
    "\046\uffdc\047\uffdc\050\uffdc\051\uffdc\001\002\000\022\007" +
    "\uffe4\030\uffe4\044\uffe4\045\uffe4\046\uffe4\047\uffe4\050\uffe4" +
    "\051\uffe4\001\002\000\022\007\uffdd\030\uffdd\044\uffdd\045" +
    "\uffdd\046\uffdd\047\uffdd\050\uffdd\051\uffdd\001\002\000\022" +
    "\007\043\030\052\044\040\045\045\046\033\047\044\050" +
    "\014\051\054\001\002\000\022\007\uffe1\030\uffe1\044\uffe1" +
    "\045\uffe1\046\uffe1\047\uffe1\050\uffe1\051\uffe1\001\002\000" +
    "\022\007\uffde\030\uffde\044\uffde\045\uffde\046\uffde\047\uffde" +
    "\050\uffde\051\uffde\001\002\000\022\007\uffdb\030\uffdb\044" +
    "\uffdb\045\uffdb\046\uffdb\047\uffdb\050\uffdb\051\uffdb\001\002" +
    "\000\022\007\uffdf\030\uffdf\044\uffdf\045\uffdf\046\uffdf\047" +
    "\uffdf\050\uffdf\051\uffdf\001\002\000\022\007\uffe5\030\uffe5" +
    "\044\uffe5\045\uffe5\046\uffe5\047\uffe5\050\uffe5\051\uffe5\001" +
    "\002\000\022\007\uffda\030\uffda\044\uffda\045\uffda\046\uffda" +
    "\047\uffda\050\uffda\051\uffda\001\002\000\066\006\uffeb\007" +
    "\uffeb\010\uffeb\012\uffeb\022\uffeb\023\uffeb\025\uffeb\027\067" +
    "\030\063\031\064\032\072\033\065\034\075\035\073\036" +
    "\077\037\074\040\070\041\066\042\062\043\076\044\uffeb" +
    "\045\uffeb\046\uffeb\047\uffeb\050\uffeb\051\uffeb\001\002\000" +
    "\066\006\uffea\007\uffea\010\uffea\012\uffea\022\uffea\023\uffea" +
    "\025\uffea\027\067\030\063\031\064\032\072\033\065\034" +
    "\075\035\073\036\077\037\074\040\070\041\066\042\062" +
    "\043\076\044\uffea\045\uffea\046\uffea\047\uffea\050\uffea\051" +
    "\uffea\001\002\000\040\002\ufffd\006\ufffd\007\ufffd\012\ufffd" +
    "\023\ufffd\024\ufffd\025\ufffd\026\ufffd\030\ufffd\044\ufffd\045" +
    "\ufffd\046\ufffd\047\ufffd\050\ufffd\051\ufffd\001\002\000\036" +
    "\010\104\027\067\030\063\031\064\032\072\033\065\034" +
    "\075\035\073\036\077\037\074\040\070\041\066\042\062" +
    "\043\076\001\002\000\066\006\uffe7\007\uffe7\010\uffe7\012" +
    "\uffe7\022\uffe7\023\uffe7\025\uffe7\027\uffe7\030\uffe7\031\uffe7" +
    "\032\uffe7\033\uffe7\034\uffe7\035\uffe7\036\uffe7\037\uffe7\040" +
    "\uffe7\041\uffe7\042\uffe7\043\uffe7\044\uffe7\045\uffe7\046\uffe7" +
    "\047\uffe7\050\uffe7\051\uffe7\001\002\000\036\022\106\027" +
    "\067\030\063\031\064\032\072\033\065\034\075\035\073" +
    "\036\077\037\074\040\070\041\066\042\062\043\076\001" +
    "\002\000\004\011\004\001\002\000\034\006\ufff0\007\ufff0" +
    "\012\ufff0\023\ufff0\024\110\025\ufff0\030\ufff0\044\ufff0\045" +
    "\ufff0\046\ufff0\047\ufff0\050\ufff0\051\ufff0\001\002\000\004" +
    "\022\111\001\002\000\004\011\004\001\002\000\032\006" +
    "\uffef\007\uffef\012\uffef\023\uffef\025\uffef\030\uffef\044\uffef" +
    "\045\uffef\046\uffef\047\uffef\050\uffef\051\uffef\001\002\000" +
    "\062\006\uffed\007\uffed\012\uffed\023\uffed\025\uffed\027\067" +
    "\030\063\031\064\032\072\033\065\034\075\035\073\036" +
    "\077\037\074\040\070\041\066\042\062\043\076\044\uffed" +
    "\045\uffed\046\uffed\047\uffed\050\uffed\051\uffed\001\002\000" +
    "\036\022\115\027\067\030\063\031\064\032\072\033\065" +
    "\034\075\035\073\036\077\037\074\040\070\041\066\042" +
    "\062\043\076\001\002\000\004\011\004\001\002\000\032" +
    "\006\uffee\007\uffee\012\uffee\023\uffee\025\uffee\030\uffee\044" +
    "\uffee\045\uffee\046\uffee\047\uffee\050\uffee\051\uffee\001\002" +
    "\000\004\012\ufffa\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\115\000\012\002\004\003\010\004\007\005\005\001" +
    "\001\000\026\006\031\007\045\010\034\011\036\012\054" +
    "\013\055\015\046\016\050\020\047\022\052\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\013\012\001\001" +
    "\000\006\004\007\005\011\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\021\015\001\001\000\002\001\001" +
    "\000\004\014\022\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\003\025\001" +
    "\001\000\002\001\001\000\004\003\027\001\001\000\002" +
    "\001\001\000\002\001\001\000\026\006\031\007\116\010" +
    "\034\011\036\012\054\013\055\015\046\016\050\020\047" +
    "\022\052\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\012\013\057\015\113\020\047\022\052" +
    "\001\001\000\002\001\001\000\002\001\001\000\012\013" +
    "\057\015\112\020\047\022\052\001\001\000\012\013\057" +
    "\015\104\020\047\022\052\001\001\000\012\013\057\015" +
    "\102\020\047\022\052\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\004\017\070\001\001\000" +
    "\012\013\057\015\100\020\047\022\052\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\021\015\001\001\000\012" +
    "\013\057\015\060\020\047\022\052\001\001\000\004\021" +
    "\015\001\001\000\004\017\070\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\012" +
    "\013\057\015\077\020\047\022\052\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\017\070\001" +
    "\001\000\004\017\070\001\001\000\002\001\001\000\004" +
    "\017\070\001\001\000\002\001\001\000\004\017\070\001" +
    "\001\000\004\003\106\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\003\111\001\001\000\002\001\001\000" +
    "\004\017\070\001\001\000\004\017\070\001\001\000\004" +
    "\003\115\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


 
  public void report_error(String message, Object info) {
    StringBuilder m = new StringBuilder("Error ");

    if (info instanceof java_cup.runtime.Symbol) 
      m.append( "("+info.toString()+")" );
     
    m.append(" : "+message);
   
    System.out.println(m);
  }
   
  public void report_fatal_error(String message, Object info) {
    report_error(message, info);
    throw new RuntimeException("Fatal Syntax Error");
  }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // program ::= definition_list 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // program ::= block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // block ::= LBRACE RBRACE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // block ::= LBRACE statement_list RBRACE 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // definition_list ::= definition definition_list 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("definition_list",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // definition_list ::= definition 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("definition_list",3, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // statement_list ::= statement statement_list 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement_list",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // statement_list ::= statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement_list",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // definition ::= DEF name LPAREN RPAREN COLON block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("definition",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-5)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // definition ::= DEF name LPAREN name_list RPAREN COLON block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("definition",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // statement ::= assignment 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // statement ::= if_statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // statement ::= while_statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // statement ::= return_statement 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // statement ::= expression 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("statement",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // assignment ::= name ASSIGN expression 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("assignment",12, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // if_statement ::= IF expression COLON block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("if_statement",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // if_statement ::= IF expression COLON block ELSE COLON block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("if_statement",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // while_statement ::= WHILE expression COLON block 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("while_statement",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // return_statement ::= RETURN expression 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("return_statement",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // return_statement ::= RETURN 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("return_statement",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expression ::= expression binop expression 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expression ::= unop expression 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // expression ::= name 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // expression ::= literal 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // expression ::= LPAREN expression RPAREN 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("expression",11, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // binop ::= AND 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // binop ::= OR 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // binop ::= PLUS 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // binop ::= MINUS 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // binop ::= MULT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 32: // binop ::= DIV 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 33: // binop ::= MOD 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 34: // binop ::= LT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 35: // binop ::= GT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 36: // binop ::= EQ 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 37: // binop ::= NEQ 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 38: // binop ::= GEQ 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 39: // binop ::= LEQ 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("binop",13, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 40: // unop ::= MINUS 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("unop",14, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 41: // unop ::= NOT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("unop",14, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 42: // postfix ::= DOT LEFT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("postfix",15, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 43: // postfix ::= DOT RIGHT 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("postfix",15, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 44: // name ::= IDENTIFIER 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("name",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 45: // name ::= name postfix 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("name",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 46: // literal ::= INT_LITERAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 47: // literal ::= BOOL_LITERAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 48: // literal ::= STRING_LITERAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 49: // literal ::= NONE_LITERAL 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("literal",16, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
