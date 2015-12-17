package AST;

public class IntegerValue extends Value {

	public Integer i;

	public IntegerValue(int ai) {
		this.i=ai;
	}

    public Boolean toBoolean() {
        return Boolean(this.i != 0);
    }

    public String toString() {
        return this.i.toString();
    }

}
