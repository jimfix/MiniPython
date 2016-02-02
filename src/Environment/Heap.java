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
}
