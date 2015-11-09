package visitor;

import syntaxtree.*;

public class TypeDepthFirstVisitor implements TypeVisitor {

	public Type visit(TheProgram n) {
		for ( int i = 0; i < n.defnlist.size(); i++) {
			n.defnlist.elementAt(i).accept(this);
		}
		n.block.accept(this);
		return null;
	}

	public Type visit(Name n) {
		n.type.accept(this);
		n.ident.accept(this);
		return null;
	}

	public Type visit(Defn n) {
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
		return null;
	}

	public Type visit(Formal n) {
		n.type.accept(this);
		n.ident.accept(this);
		return null;
	}

	public Type visit(BoolType n) {
		return null;
	}

	public Type visit(IntType n) {
		return null;
	}

	public Type visit(IdentifierType n) {
		return null;
	}

	public Type visit(Block n) {
		for ( int i = 0; i < n.sl.size(); i++ ) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	public Type visit(If n) {
		n.exp.accept(this);
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	public Type visit(While n) {
		n.exp.accept(this);
		n.state.accept(this);
		return null;
	}

	//  public Type visit(Print n) {
	//    n.e.accept(this);
	//    return null;
	//  }

	public Type visit(Assign n) {
		n.lhs.accept(this);
		n.rhs.accept(this);
		return null;
	}

	public Type visit(LessThan n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
		return null;
	}

	public Type visit(Plus n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
		return null;
	}

	public Type visit(Minus n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
		return null;
	}

	public Type visit(Mult n) {
		n.exp1.accept(this);
		n.exp2.accept(this);
		return null;
	}

	public Type visit(Call n) {
		n.exp.accept(this);
		n.ident.accept(this);
		for ( int i = 0; i < n.explist.size(); i++ ) {
			n.explist.elementAt(i).accept(this);
		}
		return null;
	}

	public Type visit(IntLiteral n) {
		return null;
	}

	public Type visit(True n) {
		return null;
	}

	public Type visit(False n) {
		return null;
	}

	public Type visit(IdentifierExpn n) {
		return null;
	}

	public Type visit(Not n) {
		n.exp.accept(this);
		return null;
	}

	public Type visit(Identifier n) {
		return null;
	}
}