package command;

import java.util.ArrayList;

import command.macro.MacroManager;

public class CommandManager {

	private ArrayList<Command> commandList;
	private int index = -1;
	private int currentMacro = -1;

	public CommandManager() {
		this.commandList = new ArrayList<Command>();
	}

	public void toggleCurrentMacro(int i) {
		if (currentMacro == i) {
			currentMacro = -1;
		} else {
			currentMacro = i;
		}
	}

	public void executeCommand(Command cmd) {
		if (currentMacro >= 0 && cmd instanceof Recordable) {
			MacroManager.get(currentMacro).addCommand(cmd);
		}
		if (cmd instanceof Reversible) {
			if (index < commandList.size() - 1) {
				System.out.println("drop command from " + index + " to " + commandList.size());
				for (int i = index + 1; i < commandList.size(); i++) {
					commandList.remove(i);
				}
			}
			commandList.add(cmd);
			index = commandList.size() - 1;
		}
		cmd.execute();
	}

	public void undo() {
		if (index >= 0) {
			((Reversible) commandList.get(index--)).revert();
		}
	}

	public void redo() {
		if (index < commandList.size() - 1) {
			commandList.get(++index).execute();
		}
	}

}
