package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class If extends Statement {

	public Expn   exp;
	public Statement  s1;
	public Statement s2;

	public If(Expn e, Statement m, Statement n) {
		this.exp = e;
		this.s1 = m;
		this.s2 = n;
	}
	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}