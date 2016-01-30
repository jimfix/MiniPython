package Environment;

public class Value {

	private String tag;
	private Object data;

	public Value(String tag_, Object data_) {
		this.setTag(tag_);
		this.setData(data_);
	}

	public String getTag() { return tag; }
	public void setTag(String tag) { this.tag = tag; }
	public Object getData() { return data; }
	public void setData(Object data) {	this.data = data; }

}