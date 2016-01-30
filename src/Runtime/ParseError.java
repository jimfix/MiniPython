package Runtime;


@SuppressWarnings("serial")
public class ParseError extends Error {
	public ParseError(String string) {
		super(string);
	}
}
