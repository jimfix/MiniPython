package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Assign extends Statement {
	public Identifier lhs;
	public Expn   rhs;

	public Assign(Identifier l, Expn r) {
		this.lhs = l;
		this.rhs = r;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}