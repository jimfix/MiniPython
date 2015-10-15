public class ProgramOutput {

	Defn   defn;
	Block  main;

	public Program(Defn d, Block m) {
		this.defn = d;
		this.main = m;
	}

	public String toString() {
		String s = "Program([";
		s += this.defn;
		s += "],";
		s += this.main;
		s += ")";
		return s;
	}
}