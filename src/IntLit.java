
public class IntLit extends Expn {

	int value;

	public IntLit(int v) {
		this.value = v;
	}

	public String toString() {
		return "INT_LIT("+this.value+")";
	}
}
