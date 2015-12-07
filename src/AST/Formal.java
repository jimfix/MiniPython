package AST;
import AST.Visitor.Visitor;

public class Formal {
	public Identifier i;

	public Formal(Identifier ai) {
		i=ai;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
