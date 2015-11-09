package syntaxtree;

import visitor.TypeVisitor;
import visitor.Visitor;

public class While extends Statement{

	public Expn   exp;
	public Statement  state;

	public While(Expn e, Statement s) {
		this.exp = e;
		this.state = s;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}