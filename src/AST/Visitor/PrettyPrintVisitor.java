package AST.Visitor;

import AST.And;
import AST.Assign;
import AST.Block;
import AST.BooleanLiteral;
import AST.Call;
import AST.Defn;
import AST.Display;
import AST.Div;
import AST.Equals;
import AST.Formal;
import AST.GreaterEquals;
import AST.GreaterThan;
import AST.Identifier;
import AST.IdentifierExp;
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
import AST.While;

// Sample print visitor from MiniJava web site with small modifications for UW CSE.
// HP 10/11

public class PrettyPrintVisitor implements Visitor {

	// Display added for toy example language.  Not used in regular MiniJava
	public void visit(Display n) {
		System.out.print("display ");
		n.e.accept(this);
		System.out.print(";");
	}

	// Program ->
	// DefnList defnlist;
	// Block block;
	public void visit(Program n) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			System.out.println();
			n.defnlist.get(i).accept(this);
		}
		System.out.println();
		System.out.println();
		System.out.print("  main ");
		n.block.accept(this);
	}  

	// Defn ->
	// Identifier i;
	// FormalList fl;
	// Block b;
	public void visit(Defn n) {
		System.out.print(" ");
		n.i.accept(this);
		System.out.print(" (");
		for ( int i = 0; i < n.fl.size(); i++ ) {
			n.fl.get(i).accept(this);
			if (i+1 < n.fl.size()) { System.out.print(", "); }
		}
		System.out.print(") ");
		n.b.accept(this);
	}

	// Formal ->
	// Identifier i;
	public void visit(Formal n) {
		System.out.print(" ");
		n.i.accept(this);
	}

	// Block ->
	// StatementList sl;
	public void visit(Block n) {
		System.out.println("{ ");
		for ( int i = 0; i < n.sl.size(); i++ ) {
			System.out.print("      ");
			n.sl.get(i).accept(this);
			System.out.println();
		}
		System.out.print("    } ");
	}

	// If ->
	// Exp e;
	// Statement s1,s2;
	public void visit(If n) {
		System.out.print("if ");
		n.e.accept(this);
		System.out.print("  ");
		n.s1.accept(this);
		System.out.println();
		if (n.s2 != null) {
			System.out.print("    else ");
			n.s2.accept(this);
		}
	}

	// While ->
	// Exp e;
	// Statement s;
	public void visit(While n) {
		System.out.print("while (");
		n.e.accept(this);
		System.out.print(") ");
		n.s.accept(this);
	}

	// Print ->
	// Exp e;
	public void visit(Print n) {
		System.out.print("System.out.print(");
		n.e.accept(this);
		System.out.print(");");
	}

	// Return ->
	// Exp r;
	public void visit(Return n) {
		System.out.print("return ");
		n.r.accept(this);
		System.out.print(";");
	}

	// Assign ->
	// Identifier i;
	// Exp e;
	public void visit(Assign n) {
		n.i.accept(this);
		System.out.print(" = ");
		n.e.accept(this);
		System.out.print(";");
	}

	// And ->
	// Exp e1,e2;
	public void visit(And n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" && ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Or ->
	// Exp e1,e2;
	public void visit(Or n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" || ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// GreaterThan ->
	// Exp e1,e2;
	public void visit(GreaterThan n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" > ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// LessThan ->
	// Exp e1,e2;
	public void visit(LessThan n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" < ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Equals ->
	// Exp e1,e2;
	public void visit(Equals n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" == ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// NotEquals ->
	// Exp e1,e2;
	public void visit(NotEquals n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" != ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// GreaterEquals ->
	// Exp e1,e2;
	public void visit(GreaterEquals n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" >= ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// LessEquals ->
	// Exp e1,e2;
	public void visit(LessEquals n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" <= ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Plus ->
	// Exp e1,e2;
	public void visit(Plus n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" + ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Minus ->
	// Exp e1,e2;
	public void visit(Minus n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" - ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Times ->
	// Exp e1,e2;
	public void visit(Times n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" * ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Div ->
	// Exp e1,e2;
	public void visit(Div n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" // ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Mod ->
	// Exp e1,e2;
	public void visit(Mod n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" % ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Call ->
	// Identifier i;
	// ExpList el;
	public void visit(Call n) {
		n.i.accept(this);
		System.out.print("(");
		for ( int i = 0; i < n.el.size(); i++ ) {
			n.el.get(i).accept(this);
			if ( i+1 < n.el.size() ) { System.out.print(", "); }
		}
		System.out.print(")");
	}

	// IntegerLiteral ->
	// int i;
	public void visit(IntegerLiteral n) {
		System.out.print(n.i);
	}

	// BooleanLiteral ->
	// boolean b;
	public void visit(BooleanLiteral n) {
		System.out.print(n.b);
	}

	// StringLiteral ->
	// String s;
	public void visit(StringLiteral n) {
		System.out.print(n.s);
	}

	// IdentifierExp ->
	// Identifier i;
	public void visit(IdentifierExp n) {
		System.out.print(n.i);
	}

	// Not ->
	// Exp e;
	public void visit(Not n) {
		System.out.print("!");
		n.e.accept(this);
	}

	// Identifier ->
	// String s;
	public void visit(Identifier n) {
		System.out.print(n.s);
	}
}
