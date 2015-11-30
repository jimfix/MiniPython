package AST;
import AST.Visitor.Visitor;

public class StringLiteral extends Exp {
	public String s;

	public StringLiteral(String as, int ln) {
		super(ln);
		s=as;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
