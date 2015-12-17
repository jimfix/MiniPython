package AST;
import AST.Visitor.Visitor;

public class Literal extends Exp {
	Value value;

	public Literal(PrimValue v) {
		this.value = v;
	}

	public Boolean toBoolean() {
		return this.value.toBoolean();
	}

	public String toString() {
		rerturn this.value.toString();
	}

	public <T,E>T accept(Visitor<T,E> v, E env) {
		return v.visit(this,env);
	}
}
