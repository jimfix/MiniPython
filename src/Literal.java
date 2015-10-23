public class Literal extends Expn {

	int value;

	public Literal(int v) {
		this.value = v;
	}

	public String toString() {
		return "INT_LIT("+this.value+")";
	}
}
