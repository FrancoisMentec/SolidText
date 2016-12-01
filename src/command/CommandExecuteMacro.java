package command;

import command.macro.Macro;
import command.macro.MacroManager;
import solidText.Buffer;

public class CommandExecuteMacro extends Command implements Recordable{

	private CommandManager cmdM;
	private int macro;
	
	public CommandExecuteMacro(Buffer buffer, CommandManager cmdM, int macro) {
		super(buffer);
		this.cmdM = cmdM;
		this.macro = macro;
	}

	public void execute() {
		Macro macro = MacroManager.get(this.macro);
		for(int i=0;i<macro.size();i++){
			Recordable command = (Recordable) macro.get(i);
			cmdM.executeCommand(((Recordable) macro.get(i)).copy());
		}
	}

	public Command copy() {
		return new CommandExecuteMacro(buffer, cmdM, macro);
	}
}
