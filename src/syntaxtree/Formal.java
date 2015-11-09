package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Formal {
	public Type type;
	public Identifier ident;

	public Formal(Type t, Identifier i) {
		this.type = t;
		this.ident = i;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}