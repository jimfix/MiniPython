package AST;
import AST.Visitor.Visitor;

public class False extends Exp {
    public <T,E>T accept(Visitor<T,E> v, E env) {
	return v.visit(this,env);
  }
}
