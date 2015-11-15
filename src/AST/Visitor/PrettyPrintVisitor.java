package AST.Visitor;

import AST.And;
import AST.Assign;
import AST.Block;
import AST.BooleanType;
import AST.Call;
import AST.Defn;
import AST.Display;
import AST.False;
import AST.Formal;
import AST.Identifier;
import AST.IdentifierExp;
import AST.IdentifierType;
import AST.If;
import AST.IntegerLiteral;
import AST.IntegerType;
import AST.LessThan;
import AST.Minus;
import AST.Not;
import AST.Plus;
import AST.Print;
import AST.Program;
import AST.Times;
import AST.True;
import AST.Name;
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

	public void visit(Program n) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			System.out.println();
			n.defnlist.get(i).accept(this);
		}
		n.block.accept(this);
	}  

	// Type t;
	// Identifier i;
	public void visit(Name n) {
		n.t.accept(this);
		System.out.print(" ");
		n.i.accept(this);
		System.out.print(";");
	}

	// Type t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// Block b;
	// Exp e;
	public void visit(Defn n) {
		System.out.print("  public ");
		n.t.accept(this);
		System.out.print(" ");
		n.i.accept(this);
		System.out.print(" (");
		for ( int i = 0; i < n.fl.size(); i++ ) {
			n.fl.get(i).accept(this);
			if (i+1 < n.fl.size()) { System.out.print(", "); }
		}
		System.out.println(") { ");
		for ( int i = 0; i < n.vl.size(); i++ ) {
			System.out.print("    ");
			n.vl.get(i).accept(this);
			System.out.println("");
		}
		System.out.print("    ");
		n.b.accept(this);
		System.out.print("    return ");
		n.e.accept(this);
		System.out.println(";");
		System.out.print("  }");
	}

	// Type t;
	// Identifier i;
	public void visit(Formal n) {
		n.t.accept(this);
		System.out.print(" ");
		n.i.accept(this);
	}

	public void visit(BooleanType n) {
		System.out.print("boolean");
	}

	public void visit(IntegerType n) {
		System.out.print("int");
	}

	// String s;
	public void visit(IdentifierType n) {
		System.out.print(n.s);
	}

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

	// Exp e;
	// Statement s1,s2;
	public void visit(If n) {
		System.out.print("if (");
		n.e.accept(this);
		System.out.println(") ");
		System.out.print("    ");
		n.s1.accept(this);
		System.out.println();
		System.out.print("    else ");
		n.s2.accept(this);
	}

	// Exp e;
	// Statement s;
	public void visit(While n) {
		System.out.print("while (");
		n.e.accept(this);
		System.out.print(") ");
		n.s.accept(this);
	}

	// Exp e;
	public void visit(Print n) {
		System.out.print("System.out.println(");
		n.e.accept(this);
		System.out.print(");");
	}

	// Identifier i;
	// Exp e;
	public void visit(Assign n) {
		n.i.accept(this);
		System.out.print(" = ");
		n.e.accept(this);
		System.out.print(";");
	}

	// Exp e1,e2;
	public void visit(And n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" && ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Exp e1,e2;
	public void visit(LessThan n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" < ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Exp e1,e2;
	public void visit(Plus n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" + ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Exp e1,e2;
	public void visit(Minus n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" - ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Exp e1,e2;
	public void visit(Times n) {
		System.out.print("(");
		n.e1.accept(this);
		System.out.print(" * ");
		n.e2.accept(this);
		System.out.print(")");
	}

	// Exp e;
	// Identifier i;
	// ExpList el;
	public void visit(Call n) {
		n.e.accept(this);
		System.out.print(".");
		n.i.accept(this);
		System.out.print("(");
		for ( int i = 0; i < n.el.size(); i++ ) {
			n.el.get(i).accept(this);
			if ( i+1 < n.el.size() ) { System.out.print(", "); }
		}
		System.out.print(")");
	}

	// int i;
	public void visit(IntegerLiteral n) {
		System.out.print(n.i);
	}

	public void visit(True n) {
		System.out.print("true");
	}

	public void visit(False n) {
		System.out.print("false");
	}

	// String s;
	public void visit(IdentifierExp n) {
		System.out.print(n.s);
	}

	// Exp e;
	public void visit(Not n) {
		System.out.print("!");
		n.e.accept(this);
	}

	// String s;
	public void visit(Identifier n) {
		System.out.print(n.s);
	}
}
