package AST;
import AST.Visitor.Visitor;

public class Program {

	public DefnList defnlist;
	public Block block;

	public Program(DefnList dl, Block b) {
		this.defnlist = dl;
		this.block = b;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
