package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Defn {

	public Type   type;
	public Identifier ident;
	public FormalList formals;
	public NameList vars;
	public StateList statelist;
	public Expn exp;

	public Defn(Type ty, Identifier i, FormalList fs, NameList v, 
			StateList sl, Expn e) {
		this.type = ty;
		this.ident = i;
		this.formals = fs;
		this.vars = v;
		this.statelist = sl;
		this.exp = e;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}