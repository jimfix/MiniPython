package AST;
import AST.Visitor.Visitor;

public class Name extends ASTNode {
  public Type t;
  public Identifier i;
  
  public Name(Type at, Identifier ai, int ln) {
    super(ln);
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }
}
