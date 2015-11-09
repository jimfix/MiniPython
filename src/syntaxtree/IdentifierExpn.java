package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierExpn extends Expn {
	public String s;
	public IdentifierExpn(String as) { 
		s=as;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}

	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
}