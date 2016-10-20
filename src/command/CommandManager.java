package command;

import java.util.ArrayList;
import java.util.Collection;

public class CommandManager {
	
	private Collection<Command> commandList;
	
	public CommandManager(){
		this.commandList = new ArrayList<Command>();
	}
	
	public void executeCommand(Command cmd) {
		cmd.execute();
		commandList.add(cmd);
	}
	
}
