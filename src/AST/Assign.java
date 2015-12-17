package AST;
import AST.Visitor.Visitor;

public class Assign extends Statement {
	public Identifier i;
	public Exp e;

	public Assign(Identifier ai, Exp ae) {
		i=ai; e=ae; 
	}

}
