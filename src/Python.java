
import java.io.FileReader;

import java_cup.parser;
import java_cup.runtime.*;
import syntaxtree.Program;

public class Python {
    public static void main(String[] args) {
        Program program;
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
