package memento;

public class MementoExecuteMacro implements Memento {
	
	int macroNumber;

	public MementoExecuteMacro(int macroNumber) {
		this.macroNumber = macroNumber;
	}

	public int getMacroNumber() {
		return macroNumber;
	}	

}
