package syntaxtree;

import java.util.Vector;

public class NameList {
	private Vector list;

	public NameList() {
		list = new Vector();
	}

	public void addElement(Name n) {
		list.addElement(n);
	}

	public Name elementAt(int i)  { 
		return (Name)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}