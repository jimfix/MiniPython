package Interpreter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import Errors.ParseError;

// The tokenizer takes the raw Python string and breaks it into
// the individual "tokens" that make up our Python program. Tokens
// are just a set of characters that should be bunched together. 
// For example, tokenizing the string "(12<=256)" will produce the
// following set of five tokens: (, 12, <=, 256, )

public class Tokenizer {

	// tokenize takes the raw Python string s and returns an ArrayList
	// of tokens, which are represented as Strings.  Each token must
	// be at least one character, but can otherwise be any size.
	public static ArrayList<String> tokenize(String s) {

		// This list contains all of the special characters in our 
		// language.  Each of these characters will always be given
		// their own token.  This means that we can't name a variable
		// my:var, since a variable name must be a single token.
		// We can, however, name it my_var, since "_" is not included 
		// in this list.
		Character[] breaks = {'(',')',':','+','-','/','*','<','>','=','!','.'};

		// This list contains all of the special character sets that
		// require two characters. We don't want "<=" to be treated
		// as "<" and "=" separately, so we need to treat them specially.
		// We'll do this by "peaking" ahead to see if the token after the
		// current one would complete one of these special strings.
		String[] breaks2 = {"<=",">=","==","!=","&&","||"};

		// We'll use this to build up tokens that have more than
		// one character
		String current = "";

		// Make a new empty token list
		ArrayList<String> tokens = new ArrayList<String>();

		// Keep going until we reach the end of the string
		for (int index=0; index<s.length(); index++) {
			Character c = s.charAt(index);

			// Spaces don't count as tokens, but they do separate
			// tokens, so we stop building the current token
			// if we hit a space
			if (c.equals(' ')) {
				if (current.length() > 0) {
					tokens.add(current);
					current = "";
				}			
			}

			// Time to check for special characters.  If there's
			// a special character, we end the previous token,
			// then make the special character its own token
			// by itself.
			else if (Arrays.asList(breaks).contains(c)) {
				if (current.length() > 0) {
					tokens.add(current);
					current = "";
				}

				// We have to peak ahead to the next character
				// to see if this character would make a two-character
				// special token.  
				if (index+1<s.length()) {
					Character c2 = s.charAt(index+1);
					if (Arrays.asList(breaks2).contains(c.toString()+c2.toString())) {
						tokens.add(c.toString()+c2.toString());
						index++;
					}
					else {
						tokens.add(c.toString());
					}
				}
				else {
					tokens.add(c.toString());
				}
			}

			// We need to treat newlines special. Newlines end
			// the previous token.  We also add the special 
			// NEWLINE token, then count the number of spaces
			// on the next line.
			else if (c.equals('\n')) {
				if (current.length() > 0) {
					tokens.add(current);
					current = "";
				}
				tokens.add("NEWLINE");

				// We're on a new line, let's count spaces
				index ++;
				int count = 0;
				while (index < s.length() && s.charAt(index) == ' ') {
					count ++;
					index ++;
				}
				// We'll count four spaces as a tab
				for (int i=0; i<count; i+=4) {
					tokens.add("TAB");
				}
				index --;
			}

			// Tabs are treated specially, and will be
			// treated specially and represented with the
			// "TAB" token.
			else if (c.equals('\t')) {
				if (current.length() > 0) {
					tokens.add(current);
					current = "";
				}
				tokens.add("TAB");
			}

			// No special cases! Let's add the current
			// character to the token we're building.
			else {
				current = current + c.toString();
			}	
		}	

		// If we hit the end of the file, we have to
		// add the last token we were considering to the
		// token list
		if (current.length() > 0)
			tokens.add(current);
		return tokens;
	}

	// --------------------- Preparsing -------------------------

	// The goal of the "preparse" function is to take a set of tokens
	// separated by NEWLINES and TABS and make sense of what this means
	// in terms of indents and un-indents ("dedents").  

	// If a line has more tabs than the previous line, then we add an 
	// INDENT.  If the line has fewer tabs than the previous line, we
	// add a DEDENT.  Note that having fewer tabs, could also correspond
	// to multiple DEDENTS, as in the following case:

	/* 
	 * if a>b:
	 *     if b>c:          <--- INDENT
	 *     		print c     <--- INDENT
	 * print a              <--- DEDENT DEDENT
	 * 
	 */

	// In short, there must be just as many INDENTS as DEDENTS, and they
	// must come in matched pairs, just like parentheses!

	public static TokenStream preparse(ArrayList<String> tokens) {
		// We'll use this to keep track of how indented the previous line was
		int lastindent = 0;  

		// We will copy over the tokens from the old list into this new list
		ArrayList<String> newtokens = new ArrayList<String>();

		// In order to match INDENTs and DEDENTs, we need to have a
		// stack that holds the "indent number" of the previous INDENTs,
		// since an INDENT can be arbitrarily many tabs.  i.e. if we
		// indent with two tabs, then five tabs, our stack will have the
		// values 5, 2
		Stack<Integer> indentstack = new Stack<Integer>(); 
		indentstack.push(0);

		// Time to go through each of the tokens and look for
		// tabs and newlines
		for (int i=0; i<tokens.size();) {

			// If we hit a newline, then it's time to start
			// looking for tabs!
			if (tokens.get(i).equals("NEWLINE")) {

				// We'll keep the NEWLINE
				newtokens.add("NEWLINE");

				i++;

				// This loop gets rid of completely blank lines,
				// we don't care about them
				while (tokens.size() > i && tokens.get(i).equals("NEWLINE")) {
					i++;
				}

				// We'll use "count" to count the number
				// of tabs we see on this line
				int count = 0;

				// Time to count tabs!
				if (i < tokens.size()) {

					// While the next character is a TAB,
					// increase the counter
					while (tokens.get(i).equals("TAB")) {
						count ++;
						i++;
					}
					// If there were more TABS then before,
					// this is an INDENT
					if (lastindent < count) {
						indentstack.push(lastindent);
						newtokens.add("INDENT");
					}

					// If there are fewer TABS than before,
					// this a DEDENT. There could be more
					// than one DEDENT, though, as in the 
					// example above.  We use the indentstack
					// to figure this out.
					else if (lastindent > count) {		
						while (stackCheck(indentstack) && indentstack.pop()!=count) {
							newtokens.add("DEDENT");	
						}
						newtokens.add("DEDENT");
					}
					lastindent = count;
				}
			}

			// The current token is not a newline, so
			// we should just copy it as is to the new
			// list of tokens
			else {
				newtokens.add(tokens.get(i));
				i++;
			}
		}
		TokenStream tokenstrm = new TokenStream();
		tokenstrm.addAll(newtokens);
		return tokenstrm;
	}

	// Helper function to make sure we don't read off the
	// end of our "indentstack"
	public static boolean stackCheck(Stack<Integer> stack) {
		if (stack.size() > 0) {
			return true;
		}
		throw new ParseError("There's a problem with your indentation");
	}
}