package AST;
import AST.Visitor.Visitor;

public class Block extends Statement {
	public StatementList sl;

	public Block(StatementList asl) {
		sl=asl;
	}

}

