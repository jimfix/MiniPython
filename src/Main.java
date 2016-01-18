import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Interpreter.Environment;
import Interpreter.Evaluator;
import Interpreter.Parser;
import Interpreter.Tokenizer;

public class Main {
	public static void main(String args[]) throws Exception {
		String myProgram = loadFile("Examples/test.py");
		System.out.println(Tokenizer.tokenize(myProgram));
		ArrayList<Object> ast = Parser.parse(myProgram);
		Evaluator.evalSequence(ast, Environment.createGlobalEnvironment());
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
}