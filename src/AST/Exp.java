package AST;
import AST.Visitor.Visitor;

public abstract class Exp {
	public abstract <T,E>T accept(Visitor<T,E> v, E env);
}