package Evaluator;

import java.util.ArrayList;

import AST.And;
import AST.Assign;
import AST.Block;
import AST.Call;
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

public class Evaluator implements Visitor < Object, Environment >{

	public Boolean visit(And x, Environment env){
		Boolean exp1 = (Boolean) x.e1.accept(this,env);
		Boolean exp2 = (Boolean) x.e2.accept(this,env);
		return exp1 && exp2;
	}

	public Object visit(Assign x, Environment env) {
		String id = (String) x.i.toString();
		Object res = (Object) x.e.accept(this,env);
		env.addVariable(id, res);
		return null;
	}

	public Boolean visit(Block x, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object visit(Call x, Environment env) {
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

	public Object visit(Defn x, Environment env) {
		Procedure newProc = new Procedure(x.fl,x.b,env);
		env.addVariable(x.i.toString(),newProc);
		return null;
	}

	public Object visit(Div x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this,env);
		return exp1 / exp2;
	}

	public Boolean visit(Equals x, Environment env) {
		if (x.e1.accept(this,env) == x.e2.accept(this,env)) { return true; }
		else { return false; }

	}

	public Boolean visit(Exp x, java.util.HashMap < String, Boolean > env){
		return null;
	}

	public Boolean visit(False x, Environment env) {
		return false;
	}

	public Boolean visit(Formal x, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(GreaterEquals x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 >= exp2;
	}

	public Boolean visit(GreaterThan x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 > exp2;
	}

	public Object visit(Identifier x, Environment env){
		return env.lookupVariable(x);
	}

	public Object visit(IdentifierExp x, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(IdentifierType x, Environment env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(If x, Environment env) {
		if ((Boolean) x.e.accept(this,env) == true) {
			return (Boolean) x.s1.accept(this,env);
		}
		else {
			return (Boolean) x.s2.accept(this,env);
		}
	}

	public Object visit(IntegerLiteral x, Environment env) {
		return x.i;
	}

	public Boolean visit(LessEquals x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 <= exp2;
	}

	public Boolean visit(LessThan x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 < exp2;
	}

	public Object visit(Minus x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 - exp2;
	}

	public Object visit(Mod x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
		return exp1 % exp2;
	}

	public Boolean visit(Not x, Environment env){
		Boolean exp = (Boolean) x.e.accept(this,env);
		return !exp;
	}

	public Boolean visit(NotEquals x, Environment env) {
		return x.e1.accept(this,env) != x.e2.accept(this,env);
	}

	public Boolean visit(Or x, Environment env){
		Boolean exp1 = (Boolean) x.e1.accept(this,env);
		Boolean exp2 = (Boolean) x.e2.accept(this,env);
		return exp1 || exp2;
	}

	public Object visit(Plus x, Environment env) {
		int exp1 = (int) x.e1.accept(this,env);
		int exp2 = (int) x.e2.accept(this, env);
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
