package AST;
import AST.Visitor.Visitor;

public class IntegerLiteral extends Exp {
	public int i;

	public IntegerLiteral(int ai) {
		i=ai;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
