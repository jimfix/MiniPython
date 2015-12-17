package AST;
import AST.Visitor.Visitor;

public class IntegerValue extends Value {
	public Integer i;

	public IntegerValue(int ai) {
		self.i=ai;
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
