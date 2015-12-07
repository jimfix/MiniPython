import java.io.FileReader;

import Evaluator.Environment;
import EvaluatorExample.Evaluator;
import Parser.parser;
import Scanner.scanner;
import java_cup.runtime.Symbol;

public class TestEvaluator {
	public static void main(String [] args) {
		try {
			// create a scanner on the input file
			scanner s = new scanner(new FileReader(args[0]));
			parser p = new parser(s);
			Environment env = Environment.createGlobalEnvironment();
			Symbol root = p.parse();
			Evaluator.evalSequence(root, env);
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