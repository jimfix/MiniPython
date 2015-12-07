package AST;
import AST.Visitor.Visitor;

public class StringLiteral extends Exp {
	public String s;

	public StringLiteral(String as) {
		s=as;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
