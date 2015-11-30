package Evaluator;
import java.util.ArrayList;

// We use the Procedure class in order to keep track of
// our procedures.  Procedures consist of three parts:
// the arguments that the procedure takes, the procedure body
// and the environment in which to run the procedure.

public class Procedure {
	public ArrayList<String> args; // A list of the procedure arguments
	public ArrayList<Object> body; // The actual code for the procedure
	public Environment env; // A pointer to the environment we should use
							// when we run this procedure
	
	// Procedure constructor
	public Procedure(ArrayList<String> args_, ArrayList<Object> body_, Environment env_) {
		args = args_;
		body = body_;
		env = env_;
	}
}
