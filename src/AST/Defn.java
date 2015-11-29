package AST;
import AST.Visitor.Visitor;

public class Defn extends ASTNode {
  public Identifier i;
  public FormalList fl;
  public Block b;

  public Defn(Identifier ai, FormalList afl, Block ab, int ln) {
    super(ln);
    i=ai; fl=afl; b=ab;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }
}
