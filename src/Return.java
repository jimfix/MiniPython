public class Return extends Statement {

	Expn ret;

	public Return() {
		this.ret = null;
	}
	
	public Return(Expn r) {
		this.ret = r;
	}

	public String toString() {
		return "Return("+this.ret+")";
	}
}
