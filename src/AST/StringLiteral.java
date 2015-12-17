package AST;
import AST.Visitor.Visitor;

public class StringValue extends PrimValue {
	
	public String s;

	public StringLiteral(String as) {
		s=as;
	}

	public Boolean toBoolean() {
		return this.s.equals("");
	}

	public String toString() {
		return this.s;
	}
	
}
