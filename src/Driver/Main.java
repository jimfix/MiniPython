package Driver;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Interpreter.Environment;
import Interpreter.Evaluator;
import Interpreter.Parser;

public class Main {
	public static void main(String args[]) throws Exception {
		// Example Stuff:
		// String myProgram = loadFile("code/Example1");
		// System.out.println(Tokenizer.tokenize(myProgram));
		
		// Environment env = Environment.createGlobalEnvironment();
		// ArrayList<Object> ast = Parser.parse(myProgram);
		// Evaluator.meval(ast.get(0),env);
		// Evaluator.meval(ast.get(1),env);
		// Evaluator.evalSequence(ast, env);
		// runFile("code/Example1");
		
		// Question 1:
		 //runFile("code/ExampleSquare");
		
		// Test Question 2:
		// runFile("code/YourFactorial");
		
		// Test Question 3:
		// System.out.println(Parser.parse(loadFile("code/TestQuestion3")));
		// Should print "[[while, [<, i, [-, max, 1]], [[print, i], [assign, i, [+, i, 1]]]]]"
		
		// Test Question 4:
		// runFile("code/TestQuestion4");
		// Should print True, True, False, False
		
		// Test Question 5:
		// runFile("code/TestQuestion5");
		// Should print 0,0,1,0,1,2
		
		// Test Question 7:
		// runFile("code/TestQuestion7");
		// Should print [1,2,3], [2,4,3,-5], 6
		
		// Test Question 8:
		// runFile("code/TestQuestion8");
		// Should print 9, 1, 7, 3, 2, 5
		
		// Test Question 9:
		// runFile("code/TestQuestion9");
		// Should print true, false, true, false, true
		
		// Try running this when you're done:
		// runFile("code/ExampleOldFavorites");
		// Should print [-4, 4, 8]
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
