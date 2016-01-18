package Interpreter;
import java.util.ArrayList;
import Errors.ParseError;

// The Parser is a big chunk of our Interpreter.  The Parser does 
// not actually run any of the code in our program, but rather helps
// us prepare to run the program by turning a set of tokens into 
// a much more helpful structure: an Abstract Syntax Tree (AST). 

// The Parser takes a set of tokens, and attempts to derive them
// using the rules of the grammar for our mini-Python language.
// This produces a nice tree structure, which will tell us exactly
// what's going on in our program.  For example, we might get the
// following set of tokens from the Tokenizer:
// ["if", "x", "<", "5", ":", "INDENT", "print", "5", "DEDENT", "else", ":", "INDENT", "print", "6", "DEDENT"]

// The Parser will take this list and produce the following list:
// ["if", ["<", "x", "5"], [["print", "5"]], [["print", "6"]]]]
// --------------------------------------------------

public class Parser {
	public static ArrayList<Object> parse(String s) {
		ArrayList<String> tokens = Tokenizer.tokenize(s);
		TokenStream lines = Tokenizer.preparse(tokens);
		return parseProgram(lines);	
	}

	// Program := Statement | Statement Program
	// --------------------------------------------------
	// A Python program is just a set of statements.  We'll read in
	// each of these statements, then return an ArrayList, where
	// each element of the list is one of these statements.
	// --------------------------------------------------
	public static ArrayList<Object> parseProgram(TokenStream tokens) {
		ArrayList<Object> block = new ArrayList<Object>();
		block.add(parseStatement(tokens));
		if (tokens.size() > 0) {
			while (tokens.size() > 0) {
				block.add(parseStatement(tokens));	
			}
		}
		return block;
	}

	// Statement := IfStatement | PrintStatment | ReturnStatment
	// 				WhileStatement | AssignStatement
	// --------------------------------------------------
	public static ArrayList<Object> parseStatement(TokenStream tokens) {

		// Check that we're not out of tokens
		if (tokens.size() < 1) {
			throw new ParseError("Expected a statement, but there was none");
		}


		if (tokens.get(0).equals("if")) {
			return parseIf(tokens);
		}
		else if (tokens.get(0).equals("while")) {
			return parseWhile(tokens);
		}
		else if (tokens.get(0).equals("print")) {
			return parsePrint(tokens);
		}
		else if (tokens.get(0).equals("return")) {
			return parseReturn(tokens);
		}

		// We cannot tell the difference between a procedure
		// call and a variable assignment just by looking
		// at the first token, since "x = 5" and "x(5)" both
		// start with "x".  Instead, we'll peak at the next token. 
		// If it's a "=", we know we're doing an assignment, if
		// it's a "(", we know it's a function call.

		String lookahead = "";
		if (tokens.size() > 1) {
			lookahead = tokens.get(1);
		}
		if (lookahead.equals("=")) {
			return parseAssign(tokens);
		}

		// We don't know what kind of statement this is
		throw new ParseError("Unexpected symbol: " + tokens.get(0));
	}

	// IfStatement := if Expression : Sequence else : Sequence
	// Output: ["if", expression, sequence, sequence]
	// --------------------------------------------------
	public static ArrayList<Object> parseIf(TokenStream tokens) {
		// Make a new ArrayList to store our parsed If statement
		ArrayList<Object> retval = new ArrayList<Object>();

		// Make "if" the first element of our output list
		retval.add("if");
		tokens.munch();

		// Parse the condition
		retval.add(parseExpression(tokens));

		// Parse the true clause
		tokens.munchAssert(":");
		retval.add(parseSequence(tokens));

		// Parse the false clause
		tokens.munchAssert("else");
		tokens.munchAssert(":");
		retval.add(parseSequence(tokens));
		return retval;
	}

	// WhileStatement := while Expression : Sequence
	// Output: ["while", expression, sequence]
	// --------------------------------------------------
	private static ArrayList<Object> parseWhile(TokenStream tokens) {
		// Make a new ArrayList to store our parsed While statement
		ArrayList<Object> retval = new ArrayList<Object>();

		// Make "while" the first element of our output list
		retval.add("while");
		tokens.munch();

		// Parse the condition
		retval.add(parseExpression(tokens));

		// Parse the clause
		tokens.munchAssert(":");
		retval.add(parseSequence(tokens));
		return retval;
	}

	// ReturnStatement := return Expression NEWLINE
	// Output: ["return", expression]
	// --------------------------------------------------
	public static ArrayList<Object> parseReturn(TokenStream tokens) {
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add("return");
		tokens.munch();
		retval.add(parseExpression(tokens));
		tokens.munchAssert("NEWLINE");
		return retval;
	}

	// PrintStatement := print Expression NEWLINE
	// Output: ["print", expression]
	// --------------------------------------------------
	public static ArrayList<Object> parsePrint(TokenStream tokens) {
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add("print");
		tokens.munch();
		retval.add(parseExpression(tokens));
		tokens.munchAssert("NEWLINE");
		return retval;
	}

	// AssignStatement := NAME = Expression NEWLINE
	// Output: ["assign", name, expression]
	// --------------------------------------------------
	public static ArrayList<Object> parseAssign(TokenStream tokens) {
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add("assign");
		// Get the name of the variable
		retval.add(tokens.munch());
		tokens.munchAssert("=");
		// Parse the new value for the variable
		retval.add(parseExpression(tokens));
		tokens.munchAssert("NEWLINE");
		return retval;
	}

	// Sequence := NEWLINE INDENT StatementList DEDENT
	// StatementList := Statement StatementList | Statement
	// --------------------------------------------------
	// A Sequence is just a set of Statements.  Since we use Sequences in
	// things like procedure bodies and loop bodies, we expect that a Sequence
	// should be indented (hence the added INDENT and DEDENT).  The output 
	// of parseSequence is an ArrayList of Statements.
	// --------------------------------------------------
	public static ArrayList<Object> parseSequence(TokenStream tokens) {
		ArrayList<Object> block = new ArrayList<Object>();
		tokens.munchAssert("NEWLINE");
		tokens.munchAssert("INDENT");
		// Parse the first statement (there must be at least one)
		block.add(parseStatement(tokens));		

		// If there more statements, keep parsing.  We know we're
		// done if we hit either the end of the file or a DEDENT
		while (tokens.size() > 0 && !tokens.get(0).equals("DEDENT")) {
			block.add(parseStatement(tokens));	
		}
		tokens.munchAssert("DEDENT",true);
		return block;
	}

	// Expressions are how we perform calculations in miniPython.
	// Unlike Statements, Expressions are guaranteed to always 
	// evaluate to some value. Expressions are broken into the 
	// following hierarchy:
	//	 - Expression (Comparisons involving ||)
	//   - AndExpression (Comparisons involving &&)
	//   - EqualityExpression (Comparisons involving <,>,<=,>=)
	//   - AdditionExpression (Calculations involving + and -)
	//   - MultiplicationExpression (Calculations involving * and /)
	//   - ElementExpression (Getting a certain element from a list using list[index])
	//   - FunctionCallExpression (Call a function that returns a value)
	//   - PrimativeExpression (Numbers, Variable Names, Parentheses, and Lists)
	//
	// The output of parsing Expressions will be a set of nested 
	// lists where each list starts an operation.  For example, 
	// parsing (1+2)*3 will return ["*", ["+", "1", "2"], "3"]
	//
	// The reason for specific expressions in a "hierarchical" structure
	// is that it enforces order of operations. Operations higher in the
	// hierarchy are performed last in the order of operations.
	// For example, 4+4<7 will parse as ["<", ["+", "4", "4"], "7"] rather than
	// ["+", "4", ["<", "4", "7"]], meaning we will do the addition before the
	// less than comparison.
	// --------------------------------------------------

	// Expression := AndExpression Or
	// Or := || AndExpression Or | epsilon
	// --------------------------------------------------
	public static Object parseExpression(TokenStream tokens) {
		// Parse the current value as an AndExpression
		Object val1 = parseAndExpression(tokens);

		// First check that there are still tokens left
		if (tokens.size() > 0) {				

			// Now, is the next token a comparison operator like < or > ? 
			if (tokens.get(0).equals("||")) {

				// If so, we will create a new ArrayList to represent
				// this comparison
				ArrayList<Object> nexp = new ArrayList<Object>();

				// Get the operation
				String operation = tokens.munch();

				// Parse the second argument
				Object val2 = parseAndExpression(tokens);

				// Make the list
				nexp.add(operation);
				nexp.add(val1);
				nexp.add(val2);
				return nexp;
			}				
		}
		return val1;
	}

	// AndExpression := EqualityExpression And
	// And := && EqualityExpression And | epsilon
	// --------------------------------------------------
	public static Object parseAndExpression(TokenStream tokens) {
		Object val1 = parseEqualityExpression(tokens);
		if (tokens.size() > 0) {				
			if (tokens.get(0).equals("&&")) {
				ArrayList<Object> nexp = new ArrayList<Object>();
				String operation = tokens.munch();
				Object val2 = parseEqualityExpression(tokens);
				nexp.add(operation);
				nexp.add(val1);
				nexp.add(val2);
				return nexp;
			}				
		}
		return val1;
	}

	// EqualityExpression := AdditionExpression Comparison
	// Comparison := < AdditionExpression Comparison |
	//				 > AdditionExpression Comparison | 
	//				 <= AdditionExpression Comparison |
	//				 >= AdditionExpression Comparison | 
	//				 == AdditionExpression Comparison |
	//				 epsilon
	// --------------------------------------------------
	public static Object parseEqualityExpression(TokenStream tokens) {
		Object val1 = parseAddition(tokens);
		if (tokens.size() > 0) {				
			if (tokens.get(0).equals("<=") || tokens.get(0).equals(">=") || tokens.get(0).equals("<") || tokens.get(0).equals(">") || tokens.get(0).equals("==")) {
				ArrayList<Object> nexp = new ArrayList<Object>();
				String operation = tokens.munch();
				Object val2 = parseAddition(tokens);
				nexp.add(operation);
				nexp.add(val1);
				nexp.add(val2);
				return nexp;
			}				
		}
		return val1;		
	}

	// AdditionExpression := MultiplicationExpression AdditionFactor
	// AdditionFactor := + MultiplicationExpression AdditionFactor |
	// 					 - MultiplicationExpression AdditionFactor |
	// 					 epsilon
	// --------------------------------------------------
	public static Object parseAddition(TokenStream tokens) {
		Object val1 = parseMultiplyDivide(tokens);		
		while (tokens.size() > 0 && (tokens.get(0).equals("+") || tokens.get(0).equals("-"))) {
			ArrayList<Object> nexp = new ArrayList<Object>();
			String operation = tokens.munch();
			Object val2 = parseMultiplyDivide(tokens);
			nexp.add(operation);
			nexp.add(val1);
			nexp.add(val2);
			val1 = nexp;
		}
		return val1;		
	}

	// MultiplicationExpression := PrimativeExpression MultiplicationFactor
	// MultiplicationFactor := * PrimativeExpression MultiplicationFactor |
	//                         / PrimativeExpression MultiplicationFactor |
	// 						   epsilon
	// --------------------------------------------------
	public static Object parseMultiplyDivide(TokenStream tokens) {
		Object val1 = parsePrimative(tokens);				
		while (tokens.size() > 0 && (tokens.get(0).equals("*") || tokens.get(0).equals("/"))) {
			ArrayList<Object> nexp = new ArrayList<Object>();
			String operation = tokens.munch();
			Object val2 = parsePrimative(tokens);
			nexp.add(operation);
			nexp.add(val1);
			nexp.add(val2);
			val1 = nexp;
		}				
		return val1;		
	}

	// PrimitiveExpression := Integer | NAME | ( Expression ) | 
	// 						  ListExpression
	// --------------------------------------------------
	// Lots of different things count as a primitive expression 
	// including numbers, variable and function names, lists,
	// expressions in parentheses and the |x| operation for 
	// list sizes. Depending on the case, the output of parsePrimitive
	// is slightly different:
	//   - Numbers and names: returns the number or name (i.e. "6" of "x")
	//   - Parentheses: returns the expression inside the parentheses
	//   - List: Returns a list of expressions, one for each of the elements
	// --------------------------------------------------
	public static Object parsePrimative(TokenStream tokens) {

		// Is the primitive an expression in parentheses?
		if (tokens.get(0).equals("(")) {
			tokens.munchAssert("(");
			Object val1 = parseExpression(tokens);	
			tokens.munchAssert(")");
			return val1;
		}

		// In all other cases, the primitive is a number
		// or name. We'll keep these as strings for now
		else {
			return tokens.munch();
		}
	}
}
