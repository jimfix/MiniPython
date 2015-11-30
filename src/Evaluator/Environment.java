package Evaluator;
import java.util.HashMap;

// Environment objects are used to represent different
// program environments used throughout our Python program.
// The environment contains a mapping between variable and
// procedure names and the corresponding actual values.
// For example, if I've just run the line "x=5", then
// frame.get("x") will return 5.

public class Environment {
	// The "frame" HashMap uses variable and procedure names as keys
	// and maps them to their appropriate value in the current 
	// environment.  Since we have only one mapping used by both 
	// procedures and variables, we cannot have variable and a 
	// procedure that have the same name. 
	private HashMap<String,Object> frame;

	// The parent environment. If we can't find what we're looking
	// for in the current environment, we'll check in the parent.
	// Note the parent will be "null" for the global environment
	private Environment parent;

	// Constructor for the Environment class. Requires you to
	// give a parent environment and initializes the environment
	// to have no variables or procedures.
	public Environment(Environment parent_) {
		parent = parent_;
		frame = new HashMap<String,Object>();		
	}

	// Add a new variable (or procedure) to the current
	// environment
	public void addVariable(String name, Object value) {
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
			throw new Error("Undefined name " + name);
		}
	}

	// A helper function to make the global environment
	public static Environment createGlobalEnvironment() {
		// Make the environment.  The global environment has
		// no parent, so we set its parent to "null"
		Environment env = new Environment(null);

		// true and false are not part of the actual grammar
		// for our version of Python, so we add them to the 
		// environment as if they were normal variables.  Incidentally,
		// this means that we can override the value of "true" as if
		// it were any other variable.  Don't do that.
		env.addVariable("True", true);
		env.addVariable("False", false);
		return env;
	}
}
