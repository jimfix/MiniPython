package syntaxtree;
public class Defn {

    String   name;
    Formals  formals;
    Block    body;
    
    public Defn(String f, Formals xs, Block b) {
        this.name = f;
        this.formals = xs;
        this.body = b;
    }

    public Defn(String f, Block b) {
        this.name = f;
        this.formals = null;
        this.body = b;
    }
    
    public String toString() {
        String s = "Defn(";
        s += this.name;
        s += ",[";
        if(this.formals != null) {
            s += this.formals;
            s += "],[";
        }
        s += this.body;
        s += "])";
        return s;
    }
}