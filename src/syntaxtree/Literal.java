package syntaxtree;
public class Literal extends Expn {

	Integer intValue;
	Boolean boolValue;
	String stringValue;

	public Literal(int i) {
		this.intValue = i;
	}
	
	public Literal(boolean b) {
		this.boolValue = b;
	}
	
	public Literal(String v) {
		this.stringValue = v;
	}

	public String toString() {
		if (this.intValue != null) {
			return "INT_LIT("+this.intValue+")";
		}
		else if (this.boolValue != null) {
			return "BOOL_LIT("+this.boolValue+")";
		}
		else if (this.stringValue != null) {
			return "STRING_LIT("+this.stringValue+")";
		}
		return null;
	}
}
