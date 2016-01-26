package Interpreter;

public class Pair {

	private Object left;
	private Object right;

	public Pair(Object left, Object right) {
		this.left = left;
		this.right = right;
	}

	public Object getLeft() { return left; }
	public Object getRight() { return right; }
	public void setLeft(Object left_) { left = left_; }
	public void setRight(Object right_) { right = right_; }

}