package AST;
import AST.Visitor.Visitor;

public class FunCall extends Exp {
	public Identifier i;
	public ExpList el;

	public FunCall(Identifier ai, ExpList ael) {
		i=ai; el=ael;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
