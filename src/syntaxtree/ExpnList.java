package syntaxtree;

import java.util.Vector;

public class ExpnList {
	private Vector list;

	public ExpnList() {
		list = new Vector();
	}

	public void addElement(Expn n) {
		list.addElement(n);
	}

	public Expn elementAt(int i)  { 
		return (Expn)list.elementAt(i); 
	}

	public int size() { 
		return list.size(); 
	}
}