package command.macro;

public abstract class MacroManager {
	private static Macro[] macros = new Macro[10];
	
	public static Macro get(int index){
		if(macros[index] == null){
			macros[index] = new Macro();
		}
		return macros[index];
	}
}
