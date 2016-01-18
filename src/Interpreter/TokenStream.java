package Interpreter;

import java.util.ArrayList;

import Errors.ParseError;

@SuppressWarnings("serial")

// A TokenStream is just an ArrayList of Strings which we 
// power up with some additional helpful methods described here

public class TokenStream extends ArrayList<String> {

	// parseAssert takes some condition and if that condition
	// isn't true, it throws a new error with the message provided
	public static void parseAssert(boolean x, String message) {
		if (!x) { throw new ParseError(message); }		
	}

	// As we parse our Python code, we continually remove the 
	// first token as we handle each Statement and Expression until
	// no tokens are left.  "munch" returns the first element of
	// the TokenStream and then removes that first element.

	public String munch() {
		String s = this.get(0);
		this.remove(0);
		return s;
	}

	// It is often useful to make sure we get the token we expect.
	// In an if statement, for example, we might expect a token
	// "if".  By calling munchAssert("if") we check to make sure 
	// the next token is indeed "if".  If it is, we remove it and
	// move on.  If it's not, we throw an error.

	public void munchAssert(String val) {
		munchAssert(val,false);
	}
	public void munchAssert(String val, boolean b) {
		if (this.size() > 0) {
			String s = munch();
			parseAssert(s.equals(val),"Expeced Symbol " + val + ", got " + s + " instead :(");
		}
		else {
			if (!b) {
				throw new ParseError("Ran out of input while expecting: " + val);
			}
		}
	}
}
