package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Minus extends Expn {
	public Expn exp1;
	public Expn exp2;

	public Minus(Expn e1, Expn e2) {
		this.exp1= e1;
		this.exp2= e2;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}