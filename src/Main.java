
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Environment.Frame;
import Interpreter.Evaluator;
import Interpreter.Parser;
import Interpreter.Tokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
		String mySource = "hello.py"; 
		if (args.length == 0) {
			System.out.print("Input the name of the file you wish to load: ");
			Scanner scan = new Scanner(System.in);
			mySource = scan.next();
			scan.close();
		} else {
			mySource = args[0];
		}
		String myProgram = loadFile(mySource);
		System.out.println();
		System.out.println("So here's the program I read:");
		System.out.println("---------------------");
		System.out.println(myProgram);
		System.out.println("---------------------");
		ArrayList<String> tokenized = Tokenizer.tokenize(myProgram);
		System.out.println("After tokenizing:");
		System.out.println(tokenized);
		System.out.println("---------------------");
		ArrayList<Object> ast = Parser.parse(myProgram);
		System.out.println("After parsing:");
		System.out.println(ast);
		System.out.println("---------------------");
		System.out.println("Now evaluating...");
		System.out.println();
		Evaluator.evalSequence(ast, Frame.createGlobalEnvironment());
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