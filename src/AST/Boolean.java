package AST;

public class BoolValue extends Value {
    
    public Boolean b;
    
    public BoolValue(Boolean ab) {
        this.b = ab;
    }

    public Boolean toBoolean() {
        return this.b;
    }

    public String toString() {
        return this.b.toString();
    }
}


