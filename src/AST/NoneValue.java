package AST;

public class NoneValue extends PrimValue {
	
	public NoneValue() {
	}

	public Boolean toBoolean() {
		return Boolean(false)
	}

	public String toString() {
		return "None";
	}
	
}
