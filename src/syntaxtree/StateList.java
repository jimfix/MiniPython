package syntaxtree;

import java.util.Vector;

public class StateList {
	private Vector list;

	public StateList() {
		list = new Vector();
	}

	public void addElement(Statement n) {
		list.addElement(n);
	}

	public Statement elementAt(int i)  { 
		return (Statement)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}