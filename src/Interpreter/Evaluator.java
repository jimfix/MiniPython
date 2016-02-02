package Interpreter;
import java.util.ArrayList;

import Environment.Frame;
import Environment.Heap;
import Environment.Pair;
import Environment.Value;
import Errors.EvalError;

// The Evaluator is where we actually run the code.  The Evaluator
// takes in parsed code from the Parser as input and simulates the
// Python program according to the rules defined here.

@SuppressWarnings("unchecked")
public class Evaluator {

	static Heap heap = new Heap();

	// Given a Statement or Expression, which is expected to be an ArrayList,
	// determine what kind of Statement/Expression it is,
	// then call the appropriate "eval" function to evaluate that statement.

	public static Object meval(Object expr, Frame env) {
		if (expr instanceof ArrayList<?>) {

			ArrayList<Object> exp = (ArrayList<Object>) expr;	

			// In order to make our lives easier, the parser
			// stores what kind of Statement we're dealing with
			// as the first element of the list.
			Object operation = exp.get(0);

			// Call the appropriate "eval" method based on
			// the first element of the list

			//			// Function Definitions
			//			if (operation.equals("def")) {
			//				return evalDef(exp,env); 
			//			}

			// If Statements
			if (operation.equals("if")) {				
				return evalIf(exp,env);
			}

			// Return Statements
			else if (operation.equals("return")) {
				// Return is straightforward so it doesn't get its own method
				return meval(exp.get(1),env);
			}

			// Variable Assignment
			else if (operation.equals("assign")) {
				return evalAssign(exp,env);
			}

			// Print Statements
			else if (operation.equals("print")) {
				return evalPrint(exp,env);
			}

			// While Loops
			else if (operation.equals("while")) {				
				return evalWhile(exp,env);
			}

			//			// Function Calls (Statement version)
			//			else if (operation.equals("callS")) {
			//				evalCall(exp,env);
			//				// Function calls as statements cannot return a value or
			//				// this will be confused with the return value
			//				return null;
			//			}

			// Function calls (Expression version)
			else if (operation.equals("callE")) {
				// Special keyword 'pair' gets us to the
				// constructor eval function
				if (exp.get(1).equals("pair")) {
					return evalConscell(exp,env);
				}
				else {
					//					return evalCall(exp,env);
					return null;
				}
			}

			// Equality (for ints & booleans)
			else if (operation.equals("==")) {
				return evalEquals(exp,env);
			}

			// Non-Equality (for ints & booleans)
			else if (operation.equals("!=")) {
				return evalNotEquals(exp,env);
			}

			// Or Comparison
			else if (operation.equals("||")) {
				return evalOr(exp,env);
			}

			// And Comparison
			else if (operation.equals("&&")) {
				return evalAnd(exp,env);
			}

			// Addition
			else if (operation.equals("+")) {
				return evalAdd(exp,env);
			}

			// Subtraction
			else if (operation.equals("-")) {
				return evalSub(exp,env);
			}

			// Multiplication
			else if (operation.equals("*")) {
				return evalMult(exp,env);
			}

			// Division
			else if (operation.equals("/")) {
				return evalDiv(exp,env);
			}

			// Greater Than
			else if (operation.equals(">")) {
				return evalGreaterThan(exp, env);
			}

			// Less Than
			else if (operation.equals("<")) {
				return evalLessThan(exp, env);
			}

			// Less Than Equal
			else if (operation.equals("<=")) {
				return evalLessThanEqual(exp, env);
			}

			// Greater Than Equal
			else if (operation.equals(">=")) {
				return evalGreaterThanEqual(exp, env);
			}

			// Field selector (left/right)
			else if (operation.equals("field")) {
				return evalField(exp,env);
			}
		}

		else {
			return evalPrimitive(expr,env);	
		}

		return null;
	}

	//		// Evaluating Procedure Definitions: Note that this code
	//		// does not actually run the procedure, but rather saves
	//		// its code for later in the current Environment.  This makes
	//		// sense, as we don't run functions when we define them,
	//		// we run them by calling the functions elsewhere.
	//	
	//		public static Object evalDef(ArrayList<Object> exp, Frame env) {
	//	
	//			// Create a new Procedure Object to store
	//			// all of our procedure information. In particular:
	//			// 1) The procedure's arguments
	//			// 2) The procedure's code
	//			// 3) The environment to run the procedure in  
	//			//	  (This is the same as the environment the 
	//			//     function is defined in)	
	//	
	//			Procedure newProc = new Procedure((ArrayList<String>) exp.get(2), (ArrayList<Object>) exp.get(3),env);
	//	
	//			// Store the new Procedure object in the Environment
	//			// using the procedure's name as the key
	//	
	//			env.addVariable((String)exp.get(1),newProc);
	//			return null;
	//		}

	// Evaluating a Constructed Cell: Uses a custom Pair class that has
	// a left and right value stored.

	public static Value evalConscell(ArrayList<Object> exp, Frame env) {
		ArrayList<Object> cells = (ArrayList<Object>) exp.get(2);
		Value lv = (Value)(meval(cells.get(0),env));
		Value rv = (Value)(meval(cells.get(1),env));
		Pair newcells = new Pair(lv,rv);
		heap.addPair(newcells);
		int index = heap.indexOf(newcells);
		return new Value("pair",index);
	}

	// Evaluating a Field Selector: When given a Pair (parent) and
	// left/right (side), give back that stored value.

	public static Object evalField(ArrayList<Object> exp, Frame env) {

		Value val = (Value)env.lookupVariable((String)exp.get(1));
		if (!val.getTag().equals("pair")) {
			throw new EvalError("Expected a pair data value for this variable!");
		}
		Pair pair = (Pair)val.getData();
		Value fielder;
		if (exp.get(2).equals("left")) {
			fielder = pair.getLeft();
		}
		else if (exp.get(2).equals("right")) {
			fielder = pair.getRight();
		}
		else {
			throw new EvalError("Expected a 'left' or 'right' field indicator.");
		}
		return fielder;
	}

	// Evaluating If Statements: Evaluate the condition. Evaluate the first
	// clause if true, other/second clause if false.

	public static Object evalIf(ArrayList<Object> exp, Frame env) {

		Value cond = (Value)meval(exp.get(1),env);

		// Check that the condition is a Boolean. If not, throw
		// an error.
		if (!(cond.getData() instanceof Boolean)) {
			throw new EvalError("Expected a boolean for the condition of the if statement");
		}

		if ((Boolean) cond.getData()) {
			return evalSequence(exp.get(2),env);
		}

		else {
			return evalSequence(exp.get(3),env);
		}
	}

	// Evaluating While Statements: While the condition is true,
	// evaluate the clause. Each time the evaluation finishes,
	// update the condition.

	public static Object evalWhile(ArrayList<Object> exp, Frame env) {

		Object cond = meval(exp.get(1),env);

		if (!(cond instanceof Boolean)) {
			throw new EvalError("Expected a boolean for the condition of the while statement");
		}

		while ((Boolean) cond) {
			evalSequence(exp.get(2),env);
			cond = meval(exp.get(1),env);
		}

		// Return nothing when it stops being satisfied
		return null;
	}

	// Evaluate variable assignments (i.e. things like x = x + 1)
	public static Object evalAssign(ArrayList<Object> exp, Frame env) {

		// First let's get the name of the variable
		String id = (String)exp.get(1);

		// Now evaluate the expression for the new value of the variable 
		Value res = (Value)meval(exp.get(2),env);

		// Add the variable and its value to the current environment
		env.addVariable(id, res);

		// Assignments do not have a return value
		return null;
	}

	// Print statements are easy, we just use System.out.println
	public static Object evalPrint(ArrayList<Object> exp, Frame env) {
		if (exp.get(1).equals("SKIPLINE")) {
			System.out.println();
		}
		else {
			Value val = (Value)meval(exp.get(1),env);
			if (val.getData() instanceof Boolean) {
				if ((Boolean) val.getData()) {
					System.out.println("True");
				}
				else {
					System.out.println("False");
				}
			}
			else if (val.getData() instanceof Pair) {
				Value left = ((Pair) val.getData()).getLeft();
				Value right = ((Pair) val.getData()).getRight();
				System.out.println("(" + left + ", " + right + ")");
			}
			else {
				System.out.println(val.getData());
			}
		}
		return null;
	}

	// Evaluate a sequence (list) of statements
	public static Object evalSequence(Object exp, Frame env) {
		// We just loop through the statements one at a time
		// and evaluate each one
		ArrayList<Object> expr = (ArrayList<Object>) exp;

		for (int i=0; i<expr.size(); i++) {
			Object res = meval(expr.get(i),env);

			// Statements only return a value if a "return"
			// statement has been run, so if they return
			// something, we should bail out of the sequence
			// early.
			if (res != null) {
				return res;
			}
		}
		return null;
	}

	//	// Evaluating Function Calls
	//	public static Object evalCall(ArrayList<Object> exp, Frame env) {		
	//
	//		// First, we need to get the appropriate procedure out 
	//		// of the environment
	//		Procedure proc = ((Procedure)env.lookupVariable((String) exp.get(1)));
	//
	//		// Get the arguments passed to the function
	//		ArrayList<Object> args = (ArrayList<Object>) exp.get(2);
	//
	//		// Get the arguments the function expects
	//		ArrayList<String> fargs = proc.args;
	//
	//		// Make a new environment in which we'll run the procedure.  
	//		// Its parent should be the current environment.
	//		Frame new_env = new Frame(proc.env);
	//
	//		// There should be just as many arguments in the function 
	//		// call as the function expects
	//		if (args.size() != fargs.size()) {
	//			throw new EvalError("Function expected " + fargs.size() + " arguments, got " + args.size());
	//		}
	//
	//		// Loop through each of the arguments. We first evaluate
	//		// each argument, then we add that value to the Procedure's
	//		// environment under the appropriate name.  For example, if
	//		// we had a function f(x) and we called f(1+2), we would evaluate
	//		// 1+2, then set x to 3 in f's environment
	//		for (int i=0;i<args.size();i++) {
	//			new_env.addVariable(fargs.get(i), (Value)meval(args.get(i),env));					
	//		}
	//
	//		// Now we're ready to run the procedure. Note that we run
	//		// the procedure in its own new environment
	//		return evalSequence(proc.body,new_env);
	//	}

	// Evaluating primitives, which are either numbers or variable names

	public static Object evalPrimitive(Object exp, Frame env) {
		String var = (String) exp;

		// We'll try to convert the string to a number. If this
		// fails, we know it's not a number.
		try {
			return new Value("int",Integer.parseInt(var));
		}
		catch (Exception e) {
			if (var.equals("field")) {
				throw new EvalError("The name 'field' is a reserved keyword for constructors.");
			}
			else if (var.charAt(0) == '"') {
				return new Value("string",var.substring(1,var.length()-1));
			}
			else {
				// Look up the variable in the environment
				return env.lookupVariable(var);
			}
		}
	}

	// Our basic math functions:
	// --------------------------------

	public static Value evalAdd(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("int",(Integer)v1+(Integer)v2);
		}
		throw new EvalError("Cannot add " + v1 + " and " + v2);
	}

	public static Value evalSub(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("int",(Integer)v1-(Integer)v2);
		}	
		throw new EvalError("Cannot subtract " + v2 + " from " + v1);
	}

	public static Value evalMult(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("int",(Integer)v1*(Integer)v2);
		}
		throw new EvalError("Cannot multiply " + v1 + " and " + v2);
	}

	public static Value evalDiv(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("int",(Integer)v1/(Integer)v2);
		}
		throw new EvalError("Cannot divide " + v1 + " into " + v2);
	}

	public static Value evalLessThan(ArrayList<Object> exp, Frame env) {
		// Evaluate the two expressions we'll be comparing
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();

		// Check that both values are indeed integers
		if (v1 instanceof Integer && v2 instanceof Integer) {

			// Perform the actual calculation
			return new Value("boolean",(Integer)v1<(Integer)v2);
		}

		// Throw an error if we try to compare non-Integers
		throw new EvalError("Cannot compare with <: " + v1 + " and " + v2);
	}

	public static Value evalGreaterThan(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("boolean",(Integer)v1>(Integer)v2);
		}
		throw new EvalError("Cannot compare with >: " + v1 + " and " + v2);
	}

	public static Value evalLessThanEqual(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("boolean",(Integer)v1<=(Integer)v2);
		}
		throw new EvalError("Cannot compare with <=: " + v1 + " and " + v2);
	}

	public static Value evalGreaterThanEqual(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("boolean",(Integer)v1>=(Integer)v2);
		}
		throw new EvalError("Cannot compare with >=: " + v1 + " and " + v2);
	}

	public static Value evalEquals(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("boolean",(Integer)v1==(Integer)v2);
		}
		else if (v1 instanceof Boolean && v2 instanceof Boolean) {
			return new Value("boolean",(Boolean)v1==(Boolean)v2);
		}
		throw new EvalError("Cannot check equality of " + v1 + " to " + v2);
	}

	public static Value evalNotEquals(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Integer && v2 instanceof Integer) {
			return new Value("boolean",(Integer)v1!=(Integer)v2);
		}
		else if (v1 instanceof Boolean && v2 instanceof Boolean) {
			return new Value("boolean",(Boolean)v1!=(Boolean)v2);
		}
		throw new EvalError("Cannot check non-equality of " + v1 + " to " + v2);
	}

	public static Value evalAnd(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Boolean && v2 instanceof Boolean) {
			if ((Boolean)v1) {
				return new Value("boolean",(Boolean)v2);
			}
			else {
				return new Value("boolean",(Boolean)v1);
			}
		}
		throw new EvalError("Cannot compare with AND: " + v1 + " and " + v2);
	}

	public static Value evalOr(ArrayList<Object> exp, Frame env) {
		Object v1 = ((Value) meval(exp.get(1),env)).getData();
		Object v2 = ((Value) meval(exp.get(2),env)).getData();
		if (v1 instanceof Boolean && v2 instanceof Boolean) {
			if ((Boolean)v1) {
				return new Value("boolean",(Boolean)v1);
			}
			else {
				return new Value("boolean",(Boolean)v2);
			}
		}
		throw new EvalError("Cannot compare with OR: " + v1 + " and " + v2);
	}
}
