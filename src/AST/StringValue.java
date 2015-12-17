package AST;

public class StringValue extends Value {
	
	public String s;

	public StringLiteral(String as) {
		this.s=as;
	}

	public Boolean toBoolean() {
		return this.s.equals("");
	}

	public String toString() {
		return this.s;
	}
	
}
