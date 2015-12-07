package AST;
import AST.Visitor.Visitor;

public class IdentifierExp extends Exp {
	public Identifier i;
	public IdentifierExp(Identifier ai) { 
		i=ai;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
