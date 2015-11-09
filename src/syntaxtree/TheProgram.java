package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class TheProgram {

	public DefnList  defnlist;
	public Block block;

	public TheProgram(DefnList dl, Block b) {
		this.defnlist = dl;
		this.block = b;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}
