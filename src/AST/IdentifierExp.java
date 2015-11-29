package AST;
import AST.Visitor.Visitor;

public class IdentifierExp extends Exp {
  public Identifier i;
  public IdentifierExp(Identifier ai, int ln) { 
    super(ln);
    i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
