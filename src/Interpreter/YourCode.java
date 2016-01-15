package Interpreter;

import java.util.ArrayList;
import Errors.*;

public class YourCode {
	public static String [] authors = {"mst3k"};
	
	// Question 3:
	// WhileStatement := while Expression : Sequence
	// --------------------------------------------------
	//  To parse an WhileStatement, we'll need to parse both the 
	//  loop condition and the loop body.  The condition to keep
	//  looping should be an Expression, while the body should be
	//  a Sequence.  Your implementation of parseWhile should produce
	//  an ArrayList with the following format:
	//  ["while", condition, body]
	
	public static ArrayList<Object> parseWhile(TokenStream tokens) {
		return null; // Replace this line with your code
	}
	
	// Question 4:
	public static Boolean evalLessThanEqual(ArrayList<Object> exp, Environment env) {
		return false; // Replace this line with your code
	}
	
	// Question 5:
	// evalWhile should take as input an ArrayList formatted in the
	// same way as the output of parseWhile: ["while", condition, body].
	// Using this information, you should write code that runs the body
	// of the while loop for as long as condition evaluates to true. Note
	// that when testing this, we will assume that no "return" statements
	// will be in any loop bodies.
	
	public static Object evalWhile(ArrayList<Object> exp, Environment env) {
		// Your code here...
		
		// Your while loop should return null
		return null;
	}

	// Question 7:
	// Your function "evalList" should take in an ArrayList formatted
	// in the following way: ["list", expression1, expression2, expression3, ...]
	// It should evaluate each of the expressions and build a new ArrayList
	// with the result.
	
	public static ArrayList<Object> evalList(ArrayList<Object> exp, Environment env) {
		ArrayList<Object> list = new ArrayList<Object>();
		// Your code here...
		return list;
	}
	
	// Question 8:
	// Your "evalGet" function should take in an ArrayList formatted
	// in the following way: ["get", list_expression, index_expression].
	// You should evaluate both the list_expression and index_expression,
	// which should return an ArrayList and Integer, respectively. You should
	// produce an EvalError for the following three cases:
	//   - list_expression does not evaluate to a list
	//   - index_expression does not evaluate to an Integer
	//   - The index is either less than 0 or greater than the size of the list-1
	
	public static Object evalGetListElement(ArrayList<Object> exp, Environment env) {
		return 0; // Replace this line with your code
	}
	
	// Question 9:
	// evalEquals provided here already works for Integers and Booleans.
	// Now we need to make it work for lists.  Note that this function 
	// should not throw any errors; if there is a case that seems like
	// it should be an error, you should probably return false.
	
	public static Boolean evalEquals(ArrayList<Object> exp, Environment env) {
		Object v1 = Evaluator.meval(exp.get(1),env);
		Object v2 = Evaluator.meval(exp.get(2),env);
		return checkEquals(v1,v2);
	}
	public static Boolean checkEquals(Object v1, Object v2) { 
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return ((Integer) v1) == ((Integer) v2);
		}
		else if (v1 instanceof Boolean && v2 instanceof Boolean) {
			return ((Boolean) v1) == ((Boolean) v2);
		}
		// Add your code here
		return false;
	}
	
	// Question 10:
	public static String pleased = "...";
	public static String displeased = "...";
	public static String hopetolearn = "...";
	public static String tacomments = "...";
}
