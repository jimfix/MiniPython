public class Program {

    DefnList  defnlist;
    Block     block;

    public Program(DefnList dl, Block b) {
        this.defnlist = dl;
        this.block = b;
    }

    public Program(Block b) {
        this.defnlist = null;
        this.block = b;
    }

    public String toString() {
        String s = "Program(";
        if (this.defnlist != null) {
            s += "[";
            s += this.defnlist;
            s += "],";
        }
        s += this.block;
        s += ")";
        return s;
    }
}
