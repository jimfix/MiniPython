public class While {

	Expn   exp;
	Block  body;

	public While(Expn e, Block m) {
		this.exp = e;
		this.body = m;
	}

	public String toString() {
		String s = "While(";
		s += this.exp;
		s += ",[";
		s += this.body;
		s += "])";
		return s;
	}
}