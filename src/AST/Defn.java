package AST;
import AST.Visitor.Visitor;

public class Defn extends ASTNode {
  public Type t;
  public Identifier i;
  public FormalList fl;
  public NameList vl;
  public Block b;
  public Exp e;

  public Defn(Type at, Identifier ai, FormalList afl, NameList avl, 
                    Block ab, Exp ae, int ln) {
    super(ln);
    t=at; i=ai; fl=afl; vl=avl; b=ab; e=ae;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }
}
