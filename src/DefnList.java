public class DefnList {

    DefnList   defnlist;
    Defn       defn;
    
    public DefnList(Defn d, DefnList dl) {
    	this.defn = d;
    	this.defnlist = dl;
    }

    public String toString() {
        String s = "DefnList(";
        s += this.defn;
        s += ",[";
        s += this.defnlist;
        s += "])";
        return s;
    }
}