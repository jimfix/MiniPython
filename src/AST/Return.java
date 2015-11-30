package AST;
import AST.Visitor.Visitor;

public class Return extends Statement {
	public Exp r;

	public Return(Exp ar, int ln) {
		super(ln);
		r=ar; 
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
