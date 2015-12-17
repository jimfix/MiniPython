package AST;
import AST.Visitor.Visitor;

public abstract class Statement {
	public abstract <T,C>T accept(Visitor<T,C> v, C ctxt);
}
