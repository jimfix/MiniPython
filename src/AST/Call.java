package AST;
import AST.Visitor.Visitor;

public class Call extends Exp {
	public Exp e;
	public Identifier i;
	public ExpList el;

	public Call(Identifier ai, ExpList ael) {
		i=ai; el=ael;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
