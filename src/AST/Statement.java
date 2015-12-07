package AST;
import AST.Visitor.Visitor;

public abstract class Statement {
	public abstract <T,E>T accept(Visitor<T,E> v, E env);
}
