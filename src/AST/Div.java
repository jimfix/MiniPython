package AST;
import AST.Visitor.Visitor;

public class Div extends Exp {
  public Exp e1,e2;
  
  public Div(Exp ae1, Exp ae2, int ln) {
    super(ln);
    e1=ae1; e2=ae2;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
