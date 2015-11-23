package AST;
import AST.Visitor.Visitor;

public class BooleanLiteral extends Exp {
  public boolean b;

  public BooleanLiteral(boolean ab, int ln) {
    super(ln);
    b=ab;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
