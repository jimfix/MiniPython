package Evaluator;

import java.util.ArrayList;

import AST.And;
import AST.Assign;
import AST.Block;
import AST.FunCall;
import AST.Defn;
import AST.Div;
import AST.Equals;
import AST.Exp;
import AST.False;
import AST.Formal;
import AST.GreaterEquals;
import AST.GreaterThan;
import AST.Identifier;
import AST.IdentifierExp;
import AST.IdentifierType;
import AST.If;
import AST.IntegerLiteral;
import AST.LessEquals;
import AST.LessThan;
import AST.Minus;
import AST.Mod;
import AST.None;
import AST.Not;
import AST.NotEquals;
import AST.Or;
import AST.Plus;
import AST.Print;
import AST.Program;
import AST.Return;
import AST.StringLiteral;
import AST.Times;
import AST.True;
import AST.While;
import AST.Visitor.Visitor;

public class Evaluator implements Visitor < Value, Environment >{

	public Value visit(And x, Environment env){
		Value exp1 = x.e1.accept(this,env);
		if (exp1.toBoolean()) {
			return x.e2.accept(this,env);
		} else {
			return exp1;
		}
	}

	public Value visit(Or x, Environment env){
		Value val1 = x.e1.accept(this,env);
		if (val1.toBoolean()) {
			return val1;
		} else {
			return x.e2.accept(this,env);
		}
	}

	public Value visit(Assign x, Environment env) {
		Identifier id = x.i;
		Value res = x.e.accept(this,env);
		env.addVariable(id, res);
		return new NoneValue();
	}

	// JF: hi JC, you'll need a ReturnContext everywhere a return 
	//     might need to be handled. Let's not worry about this yet.
	//     Instead, for now, we'll return None.
	//
	public Value visit(Block x, Environment env, ReturnContext ) {
		for (Statement s: x.sl) {
			s.accept(this,env);
		}
		// for now
		return new NoneValue()
	}

	public Value visit(FunCall x, Environment env) {
		// First, we need to get the appropriate procedure out 
		// of the environment
		Procedure proc = ((Procedure)env.lookupVariable(x.i));

		// Get the arguments passed to the function
		ArrayList<Object> args = (ArrayList<Object>) x.el;

		// Get the arguments the function expects
		ArrayList<String> fargs = proc.args;

		// Make a new environment in which we'll run the procedure.  
		// It's parent should be the current environment.
		Environment new_env = new Environment(proc.env);

		// There should be just as many arguments in the function 
		// call as the function expects
		if (args.size() != fargs.size()) {
			throw new Error("Function expected " + fargs.size() + " arguments, got " + args.size());
		}

		// Loop through each of the arguments. We first evaluate
		// each argument, then we add that value to the Procedure's
		// environment under the appropriate name.  For example, if
		// we had a function f(x) and we called f(1+2), we would evaluate
		// 1+2, then set x to 3 in f's environment
		for (int i=0;i<args.size();i++) {
			new_env.addVariable(fargs.get(i), x.accept(args.get(i),env));					
		}

		// Now we're ready to run the procedure. Note that we run
		// the procedure in its own new environment
		return x.accept(proc.body,new_env);
	}

	public Value visit(Defn x, Environment env) {
		Procedure newProc = new Procedure(x.fl,x.b,env);
		env.addVariable(x.i,newProc);
		return new NoneValue();
	}

	public Value visit(IntegerOp x, Environment env) {
		Value val1 = x.e1.accept(this,env);
		Value val2 = x.e2.accept(this,env);
		if (val1 instanceof IntegerValue 
			&& val2 instanceof IntegerValue) {
			Integer i1 = ((IntegerValue)val1).value;
			Integer i2 = ((IntegerValue)val2).value;
			return x.compute(i1,i2);
		}
		// JF: JC, we need to raise an exception here
		return new NoneValue()
	}

	public Value visit(Equals x, Environment env) {
		// This is going to have to be special
		if (x.e1.accept(this,env) == x.e2.accept(this,env)) { return true; }
		else { return false; }
	}

	public Value visit(IdentifierExp x, Environment env) {
		return env.lookupVariable(x.i);
	}

	public Value visit(If x, Environment env) {
		Value c = x.e.accept(this,env)
		if (c.toBoolean()) {
			return x.s1.accept(this,env);
		}
		else {
			return x.s2.accept(this,env);
		}
	}

	public Boolean visit(Not x, Environment env){
		Value val = .e.accept(this,env);
		Boolean b = val.toBoolean();
		if (b) {
			return new BooleanValue(false);
		} else {
			return new BooleanValue(true);
		}
	}

	// Get rid of NotEquals and parse it as Not(Equals(...))
	/*
	public Boolean visit(NotEquals x, Environment env) {
		return x.e1.accept(this,env) != x.e2.accept(this,env);
	}
	*/



	public Object visit(Plus x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this,env);
		return exp1 + exp2;
	}

	public Object visit(Print x, Environment env) {
		Object val = x.e.accept(this,env);
		if (val instanceof Boolean) {
			if ((Boolean) val) {
				System.out.println("True");
			}
			else {
				System.out.println("False");
			}
		}
		else {
			System.out.println(val);
		}
		return null;
	}

	public Boolean visit(Program x, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Return x, Environment env) {
		return x.r.accept(this, env);
	}

	public String visit(StringLiteral x, Environment env) {
		return x.s.toString();
	}

	public Object visit(Times x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this,env);
		return exp1 * exp2;
	}

	public Boolean visit(True x, Environment env) {
		return true;
	}

	public Boolean visit(While x, Environment env) {
		Boolean cond = (Boolean) x.e.accept(this,env);
		while((Boolean) cond) {
			return (Boolean) x.s.accept(this,env);
		}
		return null;
	}
}
