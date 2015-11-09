package visitor;

import syntaxtree.*;

public interface Visitor {
	public void visit(Program n);
	public void visit(Name n);
	public void visit(Defn n);
	public void visit(Formal n);
	public void visit(BoolType n);
	public void visit(IntType n);
	public void visit(IdentifierType n);
	public void visit(Block n);
	public void visit(If n);
	public void visit(While n);
	//  public void visit(Print n);
	public void visit(Assign n);
	public void visit(LessThan n);
	public void visit(Plus n);
	public void visit(Minus n);
	public void visit(Times n);
	public void visit(Call n);
	public void visit(IntLiteral n);
	public void visit(True n);
	public void visit(False n);
	public void visit(IdentifierExpn n);
	public void visit(Not n);
	public void visit(Identifier n);
}