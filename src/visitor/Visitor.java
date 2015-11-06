package visitor;

import syntaxtree.*;

public interface Visitor {
  public void visit(Program n);
  public void visit(Python n);
  public void visit(VarDecl n);
  public void visit(MethodDecl n);
  public void visit(Formals n);
  public void visit(BooleanType n);
  public void visit(IntegerType n);
  public void visit(IdentifierType n);
  public void visit(Block n);
  public void visit(If n);
  public void visit(While n);
//  public void visit(Print n);
  public void visit(Assign n);
  public void visit(And n);
  public void visit(LessThan n);
  public void visit(Plus n);
  public void visit(Minus n);
  public void visit(Times n);
  public void visit(Call n);
  public void visit(IntegerLiteral n);
  public void visit(True n);
  public void visit(False n);
  public void visit(IdentifierExp n);
  public void visit(Not n);
  public void visit(Identifier n);
}