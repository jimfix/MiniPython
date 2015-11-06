package syntaxtree;

public class Lookup extends Expn {

	String name;

	public Lookup(String x) {
		this.name = x;
	}

	public String toString() {
		return "Lookup(\""+this.name+"\")";
	}
}
