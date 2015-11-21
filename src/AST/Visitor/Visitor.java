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
import AST.While;

public interface Visitor {
  // Display added for toy example language.  Not used in MiniJava AST
  public void visit(Display n);
  public void visit(Program n);
  public void visit(Defn n);
  public void visit(Formal n);
  public void visit(BooleanType n);
  public void visit(IntegerType n);
  public void visit(IdentifierType n);
  public void visit(Block n);
  public void visit(If n);
  public void visit(While n);
  public void visit(Print n);
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
