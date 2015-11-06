package syntaxtree;
public class Call extends Statement {
    
    String name;
    ExpnList explist;

    public Call(String f, ExpnList exps) {
        this.name = f;
        this.explist = exps;
    }

    public Call(String f) {
        this.name = f;
        this.explist = null;
    }
    
    public String toString() {
        String s = "Call(";
        s += this.name;
        s += ",[";
        if(this.explist != null) {
            s += this.explist;
        }
        s += "])";
        return s;
    }
    
}