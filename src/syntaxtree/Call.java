package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class Call extends Expn {

	public Expn exp;
	public Identifier ident;
	public ExpnList explist;

	public Call(Expn exp, Identifier i, ExpnList exps) {
		this.exp = exp;
		this.ident = i;
		this.explist = exps;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}    
}