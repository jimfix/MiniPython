package AST;
import AST.Visitor.Visitor;

public class Call extends Exp {
	public Exp e;
	public Identifier i;
	public ExpList el;

	public Call(Identifier ai, ExpList ael, int ln) {
		super(ln);
		i=ai; el=ael;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
}
