package AST;

import java.util.List;
import java.util.ArrayList;

public class NameList extends ASTNode {
   private List<Name> list;

   public NameList(int ln) {
      super(ln);
      list = new ArrayList<Name>();
   }

   public void add(Name n) {
      list.add(n);
   }

   public Name get(int i)  { 
      return list.get(i); 
   }

   public int size() { 
      return list.size(); 
   }
}
