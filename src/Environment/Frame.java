package Environment;
import java.util.HashMap;

import Errors.EvalError;

// Environment objects are used to represent different
// program environments used throughout our Python program.
// The environment contains a mapping between variable and
// procedure names and the corresponding actual values.
// For example, if I've just run the line "x=5", then
// frame.get("x") will return 5.

public class Frame {
	// The "frame" HashMap uses variable and procedure names as keys
	// and maps them to their appropriate value in the current 
	// environment.  Since we have only one mapping used by both 
	// procedures and variables, we cannot have variable and a 
	// procedure that have the same name. 
	private HashMap<String,Value> frame;

	// The parent environment. If we can't find what we're looking
	// for in the current environment, we'll check in the parent.
	// Note the parent will be "null" for the global environment
	private Frame parent;

	// Constructor for the Environment class. Requires you to
	// give a parent environment and initializes the environment
	// to have no variables or procedures.
	public Frame(Frame parent_) {
		parent = parent_;
		frame = new HashMap<String,Value>();		
	}

	// Add a new variable (or procedure) to the current
	// environment
	public void addVariable(String name, Value value) {
		frame.put(name,value);
	}

	// Look up a certain name in the environment
	public Object lookupVariable(String name) {		
		// Check to see if the current environment has the
		// variable we're looking for.  If so, return its
		// value.
		if (frame.containsKey(name)) {
			return frame.get(name);
		}

		// If the variable isn't defined in this environment,
		// we need to check the parent environment.  If the 
		// parent is null, then we're in the global environment,
		// so we have to give up.
		else if (parent != null) {
			return parent.lookupVariable(name);
		}

		// If no environment has the variable we're looking for,
		// we throw a sad error message.
		else {
			throw new EvalError("Undefined name " + name);
		}
	}

	// A helper function to make the global environment
	public static Frame createGlobalEnvironment() {
		// Make the environment.  The global environment has
		// no parent, so we set its parent to "null"
		Frame env = new Frame(null);
		return env;
	}
}
