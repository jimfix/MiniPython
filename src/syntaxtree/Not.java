package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Not extends Expn {
	public Expn exp;

	public Not(Expn not) {
		this.exp = not; 
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}