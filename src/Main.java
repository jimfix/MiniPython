import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Interpreter.Environment;
import Interpreter.Evaluator;
import Interpreter.Parser;

public class Main {
	public static void main(String args[]) throws Exception {
		// System.out.println(Tokenizer.tokenize(myProgram));
		runFile("Examples/test.py");
	}

	// loadFile reads in a specific file and returns
	// its contents as a nice string for us to use
	public static String loadFile(String name) throws Exception {
		Scanner s = new Scanner(new File(name));
		String file = "";
		while (s.hasNext()) {
			file += s.nextLine() + "\n";			
		}
		s.close();
		return file;
	}

	// runFile reads in the given file, then runs it
	// through the parser to produce the Abstract Syntax
	// Tree, then evaluates the tree to run the program
	public static void runFile(String name) throws Exception {
		ArrayList<Object> ast = Parser.parse(loadFile(name));
		Evaluator.evalSequence(ast, Environment.createGlobalEnvironment());
	}	
}