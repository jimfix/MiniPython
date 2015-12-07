package AST;
import AST.Visitor.Visitor;

public class Defn {
	public Identifier i;
	public FormalList fl;
	public Block b;

	public Defn(Identifier ai, FormalList afl, Block ab) {
		i=ai; fl=afl; b=ab;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
