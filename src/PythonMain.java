import java.io.*;
import java_cup.runtime.*;

public class PythonMain {
    public static void main(String[] args) {
        ProgramOutput program;
        try {
            parser parser = new parser(new Scanner(new FileReader(args[0])));
            program = (Program) parser.parse().value;
            System.out.println(program);
        }
        catch (Exception e) {
            System.out.println("Exception ");
			e.printStackTrace();
        }
    }
}