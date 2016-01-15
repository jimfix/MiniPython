package Interpreter;
import java.util.ArrayList;
import Errors.ParseError;

// The Parser is a big chunk of our Interpreter.  The Parser does 
// not actually run any of the code in our program, but rather helps
// us prepare to run the program by turning a set of tokens into 
// a much more helpful structure: an Abstract Syntax Tree (AST). 

// The Parser takes a set of tokens, and attempts to derive them
// using the rules of the grammar for our mini-Python language 
// (just like you did in Problem Set 3).  This produces a nice
// tree structure, which will tell us exactly what's going on in
// our program.  For example, we might get the following set of
// tokens from the Tokenizer:
// ["if", "x", "<", "5", ":", "INDENT", "print", "5", "DEDENT", "else", ":", "INDENT", "print", "6", "DEDENT"]

// The Parser will take this list and produce the following list:
// ["if", ["<", "x", "5"], [["print", "5"]], [["print", "6"]]]]

// Don't worry if you don't understand every detail of the Parser,
// but you should make sure you understand the general idea of
// what's going on.

public class Parser {
	public static ArrayList<Object> parse(String s) {
		ArrayList<String> tokens = Tokenizer.tokenize(s);
		TokenStream lines = Tokenizer.preparse(tokens);
		return parseProgram(lines);	
	}

	// Now we get to the actual Parser. Note that each of the following
	// "parse" functions correspond to a rule in the grammar for
	// our mini Python language.  They're even numbered if you'd like
	// to see which rule goes with which function!
	
	
	// (Rule 1) Program := Statement | Statement Program
	// --------------------------------------------------
	// A Python program is just a set of statements.  We'll read in
	// each of these statements, then return an ArrayList, where
	// each element of the list is one of these statements.

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

	// (Rule 2) Statement := DefStatement | IfStatement | ReturnStatement |
	//						 PrintStatment | WhileStatment | AssignStatement |
	//						 FunctionCallStatement
	// --------------------------------------------------
	// A Statement can be all sorts of things in our mini Python language.
	// It can be a function definition, an if statement, an assignment, etc
	// We'll have to try to figure out which one we're dealing with, then
	// call the appropriate method to parse that kind of statement

	public static ArrayList<Object> parseStatement(TokenStream tokens) {
		
		// Check that we're not out of tokens
		if (tokens.size() < 1) {
			throw new ParseError("Expected a statement, but there was none");
		}
		
		// Most types of statements can be identified just by 
		// looking at their first token. If statements start with
		// "if", print statements start with "print", etc.  Based
		// on this starting token, we choose what procedure to
		// call next
		
		if (tokens.get(0).equals("def")) {
			return parseDef(tokens);
		}
		else if (tokens.get(0).equals("if")) {
			return parseIf(tokens);
		}
		else if (tokens.get(0).equals("return")) {
			return parseReturn(tokens);
		}
		else if (tokens.get(0).equals("print")) {
			return parsePrint(tokens);
		}
		else if (tokens.get(0).equals("while")) {
			return YourCode.parseWhile(tokens);
		}
		
		// We cannot tell the difference between a procedure
		// call and a variable assignment just by looking
		// at the first token, since "x = 5" and "x(5)" both
		// start with "x".  Instead, we'll peak at the next token. 
		// If it's a "=", we know we're doing an assignment, if
		// it's a "(", we know it's a function call.
		
		String lookahead="";
		if (tokens.size() > 1) {
			lookahead = tokens.get(1);
		}
		if (lookahead.equals("=")) {
			return parseAssign(tokens);
		}
		if (lookahead.equals("(")) {
			return parseFunctionCallStatement(tokens.munch(),tokens);
		}
		
		// We don't know what kind of statement this is
		throw new ParseError("Unexpected symbol: " + tokens.get(0));
	}

	// (Rules 3-5) DefStatement := def NAME ( ArgList ) : Sequence
	// 			   ArgList := NAME MoreArgs | epsilon
	//			   MoreArgs := , NAME MoreArgs | epsilon
	// --------------------------------------------------
	// Procedure definitions are pretty straightforward.  They should
	// start with a name, then have a list of arguments, and then have
	// a set of statements (a sequence) corresponding to the body of
	// our procedure.  The output of parseDef is an ArrayList with the
	// following format:
	//  ["def", NAME, ArgList, Body]

	public static ArrayList<Object> parseDef(TokenStream tokens) {
		
		// This ArrayList will hold our new "def" statement
		ArrayList<Object> retval = new ArrayList<Object>();
		
		// Add "def" to the beginning of our ArrayList version of
		// the statement so we can identify it as a "def" statement
		// later
		tokens.munch();
		retval.add("def");
		
		// Get the name of the new procedure
		String name = tokens.munch();
		retval.add(name);

		// Read in the names of the procedure arguments, we
		// store them in an ArrayList called "args"
		tokens.munchAssert("(");
		ArrayList<String> args = new ArrayList<String>();
		if (!tokens.get(0).equals(")")) {
			while (true) {
				// Read in a name for the argument
				args.add(tokens.munch());
				
				// If the next token is not a comma, 
				// there are no more arguments
				if (!tokens.get(0).equals(",")) {
					break;
				}
				tokens.munchAssert(",");
			}
		}
		retval.add(args);
		tokens.munchAssert(")");
		
		tokens.munchAssert(":");
		
		// Parse the body of the procedure
		retval.add(parseSequence(tokens));
		return retval;
	}

	// (Rule 6) IfStatement := if Expression : Sequence else : Sequence
	// --------------------------------------------------
	//  To parse an IfStatement, we need to parse three separate parts:
	//  the condition, the true clause, and the false ("else") clause.
	//  The true and false clauses can contain any number of statements,
	//  so we represent them with a Sequence. The condition needs to be
	//  some true or false value, so we use an Expression.  The output
	//  of IfStatement is an ArrayList with the following format
	//  ["if", condition, true-clause, false-clause]

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
	
	
	// (Rule 8) ReturnStatement := return Expression NEWLINE
	// Output: ["return", expression]
	
	public static ArrayList<Object> parseReturn(TokenStream tokens) {
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add("return");
		tokens.munch();
		retval.add(parseExpression(tokens));
		tokens.munchAssert("NEWLINE");
		return retval;
	}
	
	// (Rule 9) PrintStatement := print Expression NEWLINE
	// Output: ["print", expression]
	
	public static ArrayList<Object> parsePrint(TokenStream tokens) {
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add("print");
		tokens.munch();
		retval.add(parseExpression(tokens));
		tokens.munchAssert("NEWLINE");
		return retval;
	}
	
	// (Rule 10) AssignStatement := NAME = Expression NEWLINE
	// Output: ["assign", name, expression]
	
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

	// (Rules 11,12) Sequence := NEWLINE INDENT MoreStatements DEDENT
	//               MoreStatements := Statement MoreStatements | Statement
	// --------------------------------------------------
	// A Sequence is just a set of Statements.  Since we use Sequences in
	// things like procedure bodies and loop bodies, we expect that a Sequence
	// should be indented (hence the added INDENT and DEDENT).  The output 
	// of parseSequence is an ArrayList of Statements.
	
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

	
	// (Rules 13,14) Expression := AdditionExpression Comparison
	//			     Comparison := < AdditionExpression Comparison |
	//							   > AdditionExpression Comparison | 
	//							   <= AdditionExpression Comparison |
	//							   >= AdditionExpression Comparison | 
	//							   == AdditionExpression Comparison |
	//							   epsilon
	// --------------------------------------------------
	// Expressions are how we perform calculations in mini Python.
	// Unlike Statements, Expressions are guaranteed to always 
	// evaluate to some value. Expressions are broken into the 
	// following hierarchy:
	//   - Expression (Comparisons involving <,>,<=,>=)
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
	// Why do we need so many different expressions?  You might imagine
	// that we could, for example, combine AdditionExpression and 
	// Multiplication Expression, making it work with +,-,* and / 
	// all at once.  
	// 
	// The reason for this hierarchical structure is that
	// it enforces order of operations. Operations higher in the
	// hierarchy are performed last in the order of operations.
	// For example, 4+4<7 will parse as ["<", ["+", "4", "4"], "7"] rather than
	// ["+", "4", ["<", "4", "7"]], meaning we will do the addition before the
	// less than comparison.
	// 
	
	public static Object parseExpression(TokenStream tokens) {
		// Parse the current value as an AdditionExpression
		Object val1 = parseAddition(tokens);
		
		// First check that there are still tokens left
		if (tokens.size() > 0) {				
			
			// Now, is the next token a comparison operator like < or > ? 
			if (tokens.get(0).equals("<=") || tokens.get(0).equals(">=") || tokens.get(0).equals("<") || tokens.get(0).equals(">") || tokens.get(0).equals("==")) {

				// If so, we will create a new ArrayList to represent
				// this comparison
				ArrayList<Object> nexp = new ArrayList<Object>();
				
				// Get the operation
				String operation = tokens.munch();
				
				// Parse the second argument
				Object val2 = parseAddition(tokens);
				
				// Make the list
				nexp.add(operation);
				nexp.add(val1);
				nexp.add(val2);
				return nexp;
			}				
		}
		return val1;		
	}

	// (Rules 15,16) AdditionExpression := MultiplicationExpression AdditionFactor
	//			     AdditionFactor := + MultiplicationExpression AdditionFactor |
	// 								   - MultiplicationExpression AdditionFactor | epsilon
	
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

	// (Rules 17,18) MultiplicationExpression := ElementExpression MultiplicationFactor
	//			     MultiplicationFactor := * ElementExpression MultiplicationFactor |
	//                                       / ElementExpression MultiplicationFactor | epsilon
	
	public static Object parseMultiplyDivide(TokenStream tokens) {
		Object val1 = parseElement(tokens);				
		while (tokens.size() > 0 && (tokens.get(0).equals("*") || tokens.get(0).equals("/"))) {
			ArrayList<Object> nexp = new ArrayList<Object>();
			String operation = tokens.munch();
			Object val2 = parseElement(tokens);
			nexp.add(operation);
			nexp.add(val1);
			nexp.add(val2);
			val1 = nexp;
		}				
		return val1;		
	}
	
	// (Rules 19,20) ElementExpression := PrimitiveExpression ElementAccess
	//			     ElementAccess :=  [ Expression ] ElementAcess | epsilon
	// --------------------------------------------------
	// Element accesses have their usual form in mini Python.  You
	// can do x[1], [1,2,3][0], lst[i+1], [[1,2], 3, 4][0][1], etc.
	// Element accesses will be represented as a list starting with
	// "get". Multiple element accesses in a row are represented as
	// nested lists. For example, x[1][3] will be represented as:
	//	 ["get", ["get", "x", "1"], "3"]
	
	public static Object parseElement(TokenStream tokens) {
		Object val1 = parseCallExpression(tokens);
		while (tokens.size() > 0 && tokens.get(0).equals("[")) {
			tokens.munchAssert("[");
			ArrayList<Object> nexp = new ArrayList<Object>();
			nexp.add("get");
			nexp.add(val1);
			nexp.add(parseExpression(tokens));
			tokens.munchAssert("]");
			val1 = nexp;
		}
		return val1;
	}
		
	// (Rules 21-23) FunctionCallExpression := PrimitiveExpression ( ExpressionList ) 
	//               ExpressionList := Expression MoreExpressions | epsilon
	//               MoreExpressions := , Expression MoreExpressions | epsilon
	// --------------------------------------------------
	// Procedure calls are represented using ArrayLists with the
	// following format: ["callE", [arg1, arg2, etc.]]
	
	public static Object parseCallExpression(TokenStream tokens) {
		Object val = parsePrimative(tokens);
		
		// Since function calls can be mad both in Expressions
		// and as a Statement, we use one helper function to do
		// both.  We call that here.
		if (tokens.size() > 0 && tokens.get(0).equals("(")) {
			return parseCall((String)val,tokens,false,"callE");
		}
		else {
			return val;
		}
	}
	
	// (Rule 24) PrimitiveExpression := Integer | NAME | ( Expression ) | 
	//  								ListExpression  | BAR Expression BAR
	// --------------------------------------------------
	// Lots of different things count as a primitive expression 
	// including numbers, variable and function names, lists,
	// expressions in parentheses and the |x| operation for 
	// list sizes. Depending on the case, the output of parsePrimitive
	// is slightly different:
	//   - Numbers and names: returns the number or name (i.e. "6" of "x")
	//   - Parentheses: returns the expression inside the parentheses
	//   - List: Returns a list of expressions, one for each of the elements
	//   - List Size: A list of the form ["sizeof", list]
	
	public static Object parsePrimative(TokenStream tokens) {
		
		// Is the primitive an expression in parentheses?
		if (tokens.get(0).equals("(")) {
			tokens.munchAssert("(");
			Object val1 = parseExpression(tokens);	
			tokens.munchAssert(")");
			return val1;
		}
		
		// Is the primitive calculating a list size?
		// (THIS CODE NOT RELEVANT TO QUESTION 6)
		if (tokens.get(0).equals("|")) {
			tokens.munchAssert("|");
			Object val1 = parseExpression(tokens);	
			ArrayList<Object> res = new ArrayList<Object>();
			res.add("sizeof");
			res.add(val1);
			tokens.munchAssert("|");
			return res;
		}
		
		// Is the primitive a list?
		else if (tokens.get(0).equals("[")) {
			return parseList(tokens);
		}
		
		// In all other cases, the primitive is a number
		// or name. We'll keep these as strings for now
		else {
			return tokens.munch();
		}
	}
	
	// How to parse a list (might be helpful for Question 6)
	public static ArrayList<Object> parseList(TokenStream tokens) {
		// Make a new list
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("list");
		tokens.munchAssert("[");
		
		// Keep looping until we hit a "]", indicating
		// we've hit the end of our list
		if (!tokens.get(0).equals("]")) {
			
			// Parse the first element of the list
			Object val = parseExpression(tokens);
			list.add(val);
			while (tokens.get(0).equals(",")) {
				
				// Check for a comma, then parse
				// the expression for the next element
				tokens.munchAssert(",");
				val = parseExpression(tokens);
				list.add(val);
			}
		}
		tokens.munchAssert("]");
		return list;
	}
	
	// (Rule 25) FunctionCallStatement := PrimitiveExpression ( ExpressionList ) NEWLINE
	// --------------------------------------------------
	// FunctionCallStatements are essentially just like FunctionCallExpressions
	// but they can appear in Sequences.
	
	public static ArrayList<Object> parseFunctionCallStatement(String name, TokenStream tokens) {
		return parseCall(name,tokens,true,"callS");
	}
	
	// Here is the helper function for function calls that is used in
	// both FunctionCallStatement and FunctionCallExpression
	public static ArrayList<Object> parseCall(String name, TokenStream tokens, boolean expectNewline, String calltype) {
		
		// We will return a list where the first element is "call"
		// to indicate that this is a function call
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add(calltype);
		
		// Get the name of the function in question (this can also be a variable)
		retval.add(name);
		
		// Read in the expressions for each of the function
		// arguments
		tokens.munchAssert("(");
		ArrayList<Object> args = new ArrayList<Object>();
		if (!tokens.get(0).equals(")")) {
			while (true) {
				args.add(parseExpression(tokens));
				if (!tokens.get(0).equals(",")) {
					break;
				}
				tokens.munchAssert(",");
			}
		}
		retval.add(args);
		tokens.munchAssert(")");
		
		// We expect a NEWLINE if this is a Statement,
		// but not if it's an Expression
		if (expectNewline) 
			tokens.munchAssert("NEWLINE");
		return retval;
	}
}
