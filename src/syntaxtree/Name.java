package syntaxtree;
public class Name {

    Name   varname;
    String    direction;
    
    public Name(Name x, String dir) {
    	this.varname = x;
    	this.direction = dir;
    }

    public Name(Name x) {
    	this.varname = x;
    	this.direction = null;
    }

    public String toString() {
        String s = "Name(";
        s += this.varname;
        if(this.direction != null) {
        	s += ".";
        	s += this.direction;
        }
        s += ")";
        return s;
    }
}