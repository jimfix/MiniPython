package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IntLiteral extends Expn {
	public int i;

	public IntLiteral(int ai) {
		this.i = ai;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}