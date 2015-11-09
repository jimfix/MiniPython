package syntaxtree;

import java.util.Vector;

public class DefnList {
	private Vector list;

	public DefnList() {
		list = new Vector();
	}

	public void addElement(Defn n) {
		list.addElement(n);
	}

	public Defn elementAt(int i)  { 
		return (Defn)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}