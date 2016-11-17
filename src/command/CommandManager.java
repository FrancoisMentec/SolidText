package command;

import java.util.ArrayList;
import java.util.Collection;

public class CommandManager {
	
	private ArrayList<Command> commandList;
	private int index = -1;
	
	public CommandManager(){
		this.commandList = new ArrayList<Command>();
	}
	
	public void executeCommand(Command cmd) {
		if(cmd instanceof Reversible){
			if(index<commandList.size()-1){
				System.out.println("drop command from "+index+" to "+commandList.size());
				for(int i=index+1; i<commandList.size(); i++){
					commandList.remove(i);
				}
			}
			commandList.add(cmd);
			index = commandList.size() - 1;
		}
		cmd.execute();
	}

	public void undo() {
		if(index>=0){
			((Reversible) commandList.get(index--)).revert();
		}
	}

	public void redo() {
		if(index<commandList.size()-1){
			commandList.get(++index).execute();
		}
	}
	
}
