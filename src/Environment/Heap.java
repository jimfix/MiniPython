package Environment;

import java.util.Vector;

public class Heap {

	private Vector<Pair> heap;

	public Heap() {
		heap = new Vector<Pair>();		
	}

	public void addPair(Pair pair) {
		heap.addElement(pair);
	}

	public int indexOf(Pair newcells) {
		return heap.indexOf(newcells);
	}
	
	public Pair get(int index) {
		return heap.get(index);
	}
	
	public String print(int index) {
		Pair result = heap.get(index);
		return "(" + result.getLeft().getData() + "," + result.getRight().getData() + ")";
	}
}
