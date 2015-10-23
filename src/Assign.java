public class Assign extends Statement {
	String lhs;
	Expn   rhs;

	public Assign(String l, Expn r) {
		this.lhs = l;
		this.rhs = r;
	}

	public String toString() {
		return "Assign("+this.lhs+","+this.rhs+")";
	}
}