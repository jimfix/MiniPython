public class StateList {

	Statement  stmt;
	StateList  stmts;

	public StateList(Statement s, StateList ss) {
		this.stmt = s;
		this.stmts = ss;
	}

	public StateList(Statement s) {
		this.stmt = s;
		this.stmts = null;
	}

	public String toString() {
		String s = "" + this.stmt;
		if (this.stmts != null) {
			s += ",";
			s += this.stmts;
		}
		return s;
	}
}