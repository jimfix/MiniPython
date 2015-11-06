package syntaxtree;
public class Binop extends Expn {

	Expn exp1;
	Expn exp2;
	String by;
	
	public Binop(Expn e1, String by, Expn e2) {
		this.exp1 = e1;
		this.exp2 = e2;
		this.by = by;
	}

	public Binop(Expn e1, sym by, Expn e2) {
		this.exp1 = e1;
		this.exp2 = e2;
		this.by = "+";
	}
	
	public String toString() {
        String s = "Binop(";
        s += this.exp1;
        s += ",";
        s += this.by;
        s += ",";
        s += this.exp2;
        s += ")";
        return s;
    }
}