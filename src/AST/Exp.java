package AST;
import AST.Visitor.Visitor;

public abstract class Exp {
    public <T,E>T accept(Visitor<T,E> v, E env) {
        return v.visit(this,env);
    }
}