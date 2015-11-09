package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Name {

	public Identifier ident;
	public Type type;

	public Name(Type t, Identifier i) {
		this.ident = i;
		this.type = t;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}