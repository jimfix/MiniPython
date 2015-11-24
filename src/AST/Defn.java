package AST;
import AST.Visitor.Visitor;

public class Defn extends ASTNode {
  public Type t;
  public Identifier i;
  public FormalList fl;
  public Block b;

  public Defn(Type at, Identifier ai, FormalList afl, Block ab, int ln) {
    super(ln);
    t=at; i=ai; fl=afl; b=ab;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }
}
