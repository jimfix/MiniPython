package AST;
import AST.Visitor.Visitor;

public class Formal extends ASTNode{
  public Identifier i;
 
  public Formal(Identifier ai, int ln) {
    super(ln);
    i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
