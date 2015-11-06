package syntaxtree;
public class Assign extends Statement {
	String lhs;
	Expn   rhs;

	public Assign(String l, Expn r) {
		this.lhs = l;
		this.rhs = r;
	}

	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public Type accept(TypeVisitor v) {
		return v.visit(this);
	}
	
	public String toString() {
		return "Assign("+this.lhs+","+this.rhs+")";
	}
}