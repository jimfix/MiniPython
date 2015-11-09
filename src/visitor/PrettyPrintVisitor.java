package visitor;

import syntaxtree.*;

public class PrettyPrintVisitor implements Visitor {

	public void visit(TheProgram n) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			System.out.println();
			n.defnlist.elementAt(i).accept(this);
		}
		n.block.accept(this);
	}

	public void visit(Name n) {
		n.type.accept(this);
		System.out.print(" ");
		n.ident.accept(this);
		System.out.print(";");
	}

	public void visit(Defn n) {
		System.out.print("  public ");
		n.type.accept(this);
		System.out.print(" ");
		n.ident.accept(this);
		System.out.print(" (");
		for ( int i = 0; i < n.formals.size(); i++ ) {
			n.formals.elementAt(i).accept(this);
			if (i+1 < n.formals.size()) { System.out.print(", "); }
		}
		System.out.println(") { ");
		for ( int i = 0; i < n.vars.size(); i++ ) {
			System.out.print("    ");
			n.vars.elementAt(i).accept(this);
			System.out.println("");
		}
		for ( int i = 0; i < n.statelist.size(); i++ ) {
			System.out.print("    ");
			n.statelist.elementAt(i).accept(this);
			if ( i < n.statelist.size() ) { System.out.println(""); }
		}
		System.out.print("    return ");
		n.exp.accept(this);
		System.out.println(";");
		System.out.print("  }");
	}

	public void visit(Formal n) {
		n.type.accept(this);
		System.out.print(" ");
		n.ident.accept(this);
	}

	public void visit(BoolType n) {
		System.out.print("boolean");
	}

	public void visit(IntType n) {
		System.out.print("int");
	}

	public void visit(IdentifierType n) {
		System.out.print(n.s);
	}

	public void visit(Block n) {
		System.out.println("{ ");
		for ( int i = 0; i < n.sl.size(); i++ ) {
			System.out.print("      ");
			n.sl.elementAt(i).accept(this);
			System.out.println();
		}
		System.out.print("    } ");
	}

	public void visit(If n) {
		System.out.print("if (");
		n.exp.accept(this);
		System.out.println(") ");
		System.out.print("    ");
		n.s1.accept(this);
		System.out.println();
		System.out.print("    else ");
		n.s2.accept(this);
	}

	public void visit(While n) {
		System.out.print("while (");
		n.exp.accept(this);
		System.out.print(") ");
		n.state.accept(this);
	}

	//  public void visit(Print n) {
	//    System.out.print("System.out.println(");
	//    n.e.accept(this);
	//    System.out.print(");");
	//  }

	public void visit(Assign n) {
		n.lhs.accept(this);
		System.out.print(" = ");
		n.rhs.accept(this);
		System.out.print(";");
	}

	public void visit(LessThan n) {
		System.out.print("(");
		n.exp1.accept(this);
		System.out.print(" < ");
		n.exp2.accept(this);
		System.out.print(")");
	}

	public void visit(Plus n) {
		System.out.print("(");
		n.exp1.accept(this);
		System.out.print(" + ");
		n.exp2.accept(this);
		System.out.print(")");
	}

	public void visit(Minus n) {
		System.out.print("(");
		n.exp1.accept(this);
		System.out.print(" - ");
		n.exp2.accept(this);
		System.out.print(")");
	}

	public void visit(Mult n) {
		System.out.print("(");
		n.exp1.accept(this);
		System.out.print(" * ");
		n.exp2.accept(this);
		System.out.print(")");
	}

	public void visit(Call n) {
		n.exp.accept(this);
		System.out.print(".");
		n.ident.accept(this);
		System.out.print("(");
		for ( int i = 0; i < n.explist.size(); i++ ) {
			n.explist.elementAt(i).accept(this);
			if ( i+1 < n.explist.size() ) { System.out.print(", "); }
		}
		System.out.print(")");
	}

	public void visit(IntLiteral n) {
		System.out.print(n.i);
	}

	public void visit(True n) {
		System.out.print("true");
	}

	public void visit(False n) {
		System.out.print("false");
	}

	public void visit(IdentifierExpn n) {
		System.out.print(n.s);
	}

	public void visit(Not n) {
		System.out.print("!");
		n.exp.accept(this);
	}

	public void visit(Identifier n) {
		System.out.print(n.s);
	}
}