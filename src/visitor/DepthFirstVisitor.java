package visitor;

import syntaxtree.*;

public class DepthFirstVisitor implements Visitor {

	public void visit(Program n) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			n.defnlist.elementAt(i).accept(this);
		}
		n.block.accept(this);
	}

	public void visit(Name n) {
		n.type.accept(this);
		n.ident.accept(this);
	}

	public void visit(Defn n) {
		n.type.accept(this);
		n.ident.accept(this);
		for ( int i = 0; i < n.formals.size(); i++ ) {
			n.formals.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.vars.size(); i++ ) {
			n.vars.elementAt(i).accept(this);
		}
		for ( int i = 0; i < n.statelist.size(); i++ ) {
			n.statelist.elementAt(i).accept(this);
		}
		n.exp.accept(this);
	}

	public void visit(Formal n) {
		n.type.accept(this);
		n.ident.accept(this);
	}

	public void visit(BoolType n) {
	}

	public void visit(IntType n) {
	}

	public void visit(IdentifierType n) {
	}

	public void visit(Block n) {
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
		}
	}

	public void visit(If n) {
		n.exp.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
	}

	public void visit(While n) {
		n.exp.accept(this);
		n.state.accept(this);
	}

	//  public void visit(Print n) {
	//    n.e.accept(this);
	//  }

	public void visit(Assign n) {
		n.lhs.accept(this);
		n.rhs.accept(this);
	}

	public void visit(LessThan n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
	}

	public void visit(Plus n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
	}

	public void visit(Minus n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
	}

	public void visit(Mult n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
	}

	public void visit(Call n) {
		n.exp.accept(this);
		n.ident.accept(this);
		for ( int i = 0; i < n.explist.size(); i++ ) {
			n.explist.elementAt(i).accept(this);
		}
	}

	public void visit(IntLiteral n) {
	}

	public void visit(True n) {
	}

	public void visit(False n) {
	}

	public void visit(IdentifierExpn n) {
	}

	public void visit(Not n) {
		n.exp.accept(this);
	}

	public void visit(Identifier n) {
	}
}