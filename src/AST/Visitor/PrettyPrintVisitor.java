package AST.Visitor;

import AST.And;
import AST.Assign;
import AST.Block;
import AST.Call;
import AST.Defn;
import AST.Div;
import AST.Equals;
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

// Sample print visitor from MiniJava web site with small modifications for UW CSE.
// HP 10/11

public class PrettyPrintVisitor implements Visitor<Object,Object> {

	private String indent = "";

	// Program ->
	// DefnList defnlist;
	// Block block;
	public Object visit(Program n, Object e) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			System.out.println();
			n.defnlist.get(i).accept(this, e);
		}
		System.out.println();
		System.out.println();
		System.out.print("  main(): ");
		n.block.accept(this, e);
		return null;
	}  

	// Defn ->
	// Identifier i;
	// FormalList fl;
	// Block b;
	public Object visit(Defn n, Object e) {
		System.out.print(" ");
		n.i.accept(this, e);
		System.out.print("(");
		for ( int i = 0; i < n.fl.size(); i++ ) {
			n.fl.get(i).accept(this, e);
			if (i+1 < n.fl.size()) { System.out.print(", "); }
		}
		System.out.print("): ");
		n.b.accept(this, e);
		return null;
	}

	// Formal ->
	// Identifier i;
	public Object visit(Formal n, Object e) {
		System.out.print(" ");
		n.i.accept(this, e);
		return null;
	}

	// Block ->
	// StatementList sl;
	public Object visit(Block n, Object e) {
		System.out.println("{ ");
		for ( int i = 0; i < n.sl.size(); i++ ) {
			System.out.print("      ");
			n.sl.get(i).accept(this, e);
			System.out.println();
		}
		System.out.print("    } ");
		return null;
	}

	// If ->
	// Exp e;
	// Statement s1,s2;
	public Object visit(If n, Object e) {
		System.out.print("if ");
		n.e.accept(this, e);
		System.out.print("  ");
		n.s1.accept(this, e);
		System.out.println();
		if (n.s2 != null) {
			System.out.print("    else ");
			n.s2.accept(this, e);
		}
		return null;
	}

	// While ->
	// Exp e;
	// Statement s;
	public Object visit(While n, Object e) {
		System.out.print("while (");
		n.e.accept(this, e);
		System.out.print(") ");
		n.s.accept(this, e);
		return null;
	}

	// Print ->
	// Exp e;
	public Object visit(Print n, Object e) {
		System.out.print("System.out.print(");
		n.e.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Return ->
	// Exp r;
	public Object visit(Return n, Object e) {
		System.out.print("return ");
		n.r.accept(this, e);
		return null;
	}

	// Assign ->
	// Identifier i;
	// Exp e;
	public Object visit(Assign n, Object e) {
		n.i.accept(this, e);
		System.out.print(" = ");
		n.e.accept(this, e);
		return null;
	}

	// And ->
	// Exp e1,e2;
	public Object visit(And n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" && ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Or ->
	// Exp e1,e2;
	public Object visit(Or n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" || ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// GreaterThan ->
	// Exp e1,e2;
	public Object visit(GreaterThan n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" > ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// LessThan ->
	// Exp e1,e2;
	public Object visit(LessThan n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" < ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Equals ->
	// Exp e1,e2;
	public Object visit(Equals n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" == ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// NotEquals ->
	// Exp e1,e2;
	public Object visit(NotEquals n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" != ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// GreaterEquals ->
	// Exp e1,e2;
	public Object visit(GreaterEquals n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" >= ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// LessEquals ->
	// Exp e1,e2;
	public Object visit(LessEquals n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" <= ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Plus ->
	// Exp e1,e2;
	public Object visit(Plus n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" + ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Minus ->
	// Exp e1,e2;
	public Object visit(Minus n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" - ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Times ->
	// Exp e1,e2;
	public Object visit(Times n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" * ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Div ->
	// Exp e1,e2;
	public Object visit(Div n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" // ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Mod ->
	// Exp e1,e2;
	public Object visit(Mod n, Object e) {
		System.out.print("(");
		n.e1.accept(this, e);
		System.out.print(" % ");
		n.e2.accept(this, e);
		System.out.print(")");
		return null;
	}

	// Call ->
	// Identifier i;
	// ExpList el;
	public Object visit(Call n, Object e) {
		n.i.accept(this, e);
		System.out.print("(");
		for ( int i = 0; i < n.el.size(); i++ ) {
			n.el.get(i).accept(this, e);
			if ( i+1 < n.el.size() ) { System.out.print(", "); }
		}
		System.out.print(")");
		return null;
	}

	// IntegerLiteral ->
	// int i;
	public Object visit(IntegerLiteral n, Object e) {
		System.out.print(n.i);
		return null;
	}

	// IdentifierType ->
	// String s;
	public Object visit(IdentifierType n, Object e) {
		System.out.print(n.s);
		return null;}

	// StringLiteral ->
	// String s;
	public Object visit(StringLiteral n, Object e) {
		System.out.print(n.s);
		return null;
	}

	// IdentifierExp ->
	// Identifier i;
	public Object visit(IdentifierExp n, Object e) {
		System.out.print(n.i);
		return null;
	}

	// Not ->
	// Exp e;
	public Object visit(Not n, Object e) {
		System.out.print("!");
		n.e.accept(this, e);
		return null;
	}

	// Identifier ->
	// String s;
	public Object visit(Identifier n, Object e) {
		System.out.print(n.s);
		return null;
	}

	public Object visit(True n, Object e) {
		System.out.print("true");
		return null;
	}

	public Object visit(False n, Object e) {
		System.out.print("false");
		return null;
	}
}
