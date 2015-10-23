public class ExpnList {

    ExpnList   explist;
    Expn       exp;
    
    public ExpnList(Expn e, ExpnList el) {
    	this.exp = e;
    	this.explist = el;
    }

    public String toString() {
        String s = "ExpnList(";
        s += this.exp;
        s += ",[";
        s += this.explist;
        s += "])";
        return s;
    }
}