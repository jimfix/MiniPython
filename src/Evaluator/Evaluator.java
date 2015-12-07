package Evaluator;

import java.util.HashMap;

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

public class Evaluator implements Visitor < Boolean,java.util.HashMap < String, Boolean > >{

	public Boolean visit(And x, java.util.HashMap < String,Boolean > env){
		return x.e1.accept(this,env) && x.e2.accept(this,env);
	}

	public Boolean visit(Assign x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Block x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Call x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Defn x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Div x, HashMap<String, Boolean> env) {
		int i = x.e1.accept(this,env);
		int j = x.e2.accept(this,env);
		return (i / j);

	}

	public Boolean visit(Equals x, HashMap<String, Boolean> env) {
		if (x.e1.accept(this,env) == x.e2.accept(this,env)) { return true; }
		else { return false; }

	}

	public Boolean visit(Exp x, java.util.HashMap < String, Boolean > env){
		return x.accept(this,env);
	}

	public Boolean visit(False x, HashMap<String, Boolean> env) {
		return false;
	}

	public Boolean visit(Formal x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(GreaterEquals x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(GreaterThan x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Identifier x, java.util.HashMap < String,Boolean > env){
		return env.get(x.s);
	}

	public Boolean visit(IdentifierExp x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(IdentifierType x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(If x, HashMap<String, Boolean> env) {
		if (x.e.accept(this,env) == true) {
			return x.s1.accept(this,env);
		}
		else {
			return x.s2.accept(this,env);
		}
	}

	public Boolean visit(IntegerLiteral x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(LessEquals x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(LessThan x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Minus x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Mod x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Not x, java.util.HashMap < String,Boolean > env){
		return !(x.e.accept(this,env));
	}

	public Boolean visit(NotEquals x, HashMap<String, Boolean> env) {
		return x.e1.accept(this,env) != x.e2.accept(this,env);
	}

	public Boolean visit(Or x, java.util.HashMap < String,Boolean > env){
		return x.e1.accept(this,env) || x.e2.accept(this,env);
	}

	public Boolean visit(Plus x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public void visit(Print x, HashMap<String, Boolean> env) {
		System.out.println(x.e.accept(this,env));
	}

	public Boolean visit(Program x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(Return x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public String visit(StringLiteral x, HashMap<String, Boolean> env) {
		return x.s.toString();
	}

	public Boolean visit(Times x, HashMap<String, Boolean> env) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean visit(True x, HashMap<String, Boolean> env) {
		return true;
	}

	public Boolean visit(While x, HashMap<String, Boolean> env) {
		while (x.e.accept(this,env)) { x.s.accept(this,env); }
		return null;
	}

}
