import java.io.*;
import java_cup.runtime.*;
import visitor.PrettyPrintVisitor;

public class Python {
    public static void main(String[] args) {
        Program program;
        try {
            parser parser = new parser(new Scanner(new FileReader(args[0])));
            program = (Program) parser.parse().value;
            program.accept(new PrettyPrintVisitor());
        }
        catch (Exception e) {
            System.out.println("Exception ");
			e.printStackTrace();
        }
    }
}
