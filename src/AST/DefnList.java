package AST;

import java.util.List;
import java.util.ArrayList;

public class DefnList extends ASTNode {
	private List<Defn> list;

	public DefnList(int ln) {
		super(ln);
		list = new ArrayList<Defn>();
	}

	public void add(Defn n) {
		list.add(n);
	}

	public Defn get(int i)  { 
		return list.get(i); 
	}

	public int size() { 
		return list.size(); 
	}
}
