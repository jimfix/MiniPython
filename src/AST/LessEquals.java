package AST;
import AST.Visitor.Visitor;

public class LessEquals extends Exp {
	public Exp e1,e2;

	public LessEquals(Exp ae1, Exp ae2) {
		e1=ae1; e2=ae2;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
