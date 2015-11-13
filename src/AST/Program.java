package AST;
import AST.Visitor.Visitor;

public class Program extends ASTNode {

	public DefnList defnlist;
	public Block block;

	public Program(DefnList dl, Block b, int ln) {
		super(ln);
		this.defnlist = dl;
		this.block = b;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
