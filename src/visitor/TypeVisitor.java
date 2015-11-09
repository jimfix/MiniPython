package visitor;

import syntaxtree.*;

public interface TypeVisitor {
	public Type visit(TheProgram n);
	public Type visit(Name n);
	public Type visit(Defn n);
	public Type visit(Formal n);
	public Type visit(BoolType n);
	public Type visit(IntType n);
	public Type visit(IdentifierType n);
	public Type visit(Block n);
	public Type visit(If n);
	public Type visit(While n);
	//  public Type visit(Print n);
	public Type visit(Assign n);
	public Type visit(LessThan n);
	public Type visit(Plus n);
	public Type visit(Minus n);
	public Type visit(Mult n);
	public Type visit(Call n);
	public Type visit(IntLiteral n);
	public Type visit(True n);
	public Type visit(False n);
	public Type visit(IdentifierExpn n);
	public Type visit(Not n);
	public Type visit(Identifier n);
}