package AST;
import AST.Visitor.Visitor;

public class Return extends Statement {
	public Exp r;

	public Return(Exp ar) {
		r=ar; 
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
