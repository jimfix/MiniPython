package Environment;

public class Pair {

	private Value left;
	private Value right;
	private Boolean isMarked;
	private Integer refersTo;

	public Pair(Value left, Value right) {
		this.setLeft(left);
		this.setRight(right);
		this.setIsMarked(false);
		this.setRefersTo(null);
	}

	public Value getLeft() { return left; }
	public void setLeft(Value left) { this.left = left; }
	public Value getRight() { return right; }
	public void setRight(Value right) { this.right = right; }
	public Boolean getIsMarked() { return isMarked; }
	public void setIsMarked(Boolean isMarked) {	this.isMarked = isMarked; }
	public Integer getRefersTo() { return refersTo;	}
	public void setRefersTo(Integer refersTo) {	this.refersTo = refersTo; }

}