package syntaxtree;
public class Formals {

    Formals   names;
    String    head;
    
    public Formals(String x, Formals xs) {
    	this.head = x;
    	this.names = xs;
    }

    public Formals(String x) {
    	this.head = x;
    	this.names = null;
    }

    public String toString() {
        String s = "Formals(";
        s += this.head;
        if(this.names != null) {
        	s += ",";
        	s += this.names;
        }
        s += ")";
        return s;
    }
}