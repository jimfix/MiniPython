import Scanner.*;
import Parser.sym;
import java_cup.runtime.Symbol;

public class TestScanner {
    public static void main(String [] args) {
        try {
            // create a scanner on the input file
            scanner s = new scanner(System.in);
            Symbol t = s.next_token();
            while (t.sym != sym.EOF){ 
                // print each token that we scan
                System.out.print(t.toString() + " ");
                t = s.next_token(); 
            }
            System.out.print("\nLexical analysis completed"); 
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + 
                        e.toString());
            // print out a stack dump
            e.printStackTrace();
        }
   }
}


