package syntaxtree;
public class If extends Statement {

	Expn   exp;
	Block  first;
	Block  second;

	public If(Expn e, Block m) {
		this.exp = e;
		this.first = m;
		this.second = null;
	}

	public If(Expn e, Block m, Block n) {
		this.exp = e;
		this.first = m;
		this.second = n;
	}

	public String toString() {
		String s = "If(";
		s += this.exp;
		s += ",[";
		s += this.first;
		s += "]";
		if(this.second != null) {
			s += ",Else[";
			s += this.second;
			s += "]";
		}
		s += ")";
		return s;
	}
}