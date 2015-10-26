public class Unop extends Expn {

	Expn exp;
	String on;
	
	public Unop(String on, Expn e) {
		this.on = on;
		this.exp = e;
	}
	
	
	public String toString() {
        String s = "Unop(";
        s += this.on;
        s += ",";
        s += this.exp;
        s += ")";
        return s;
    }
}