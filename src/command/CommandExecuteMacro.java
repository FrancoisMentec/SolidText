package command;

import command.macro.Macro;
import command.macro.MacroManager;
import javafx.util.Pair;
import memento.Memento;
import memento.MementoExecuteMacro;
import solidText.EditorEngine;

public class CommandExecuteMacro extends Command implements Recordable{

	private CommandManager cmdM;
	private int macro;
	
	public CommandExecuteMacro(EditorEngine editorEngine, CommandManager cmdM, int macro) {
		super(editorEngine);
		this.cmdM = cmdM;
		this.macro = macro;
	}
	
	public CommandExecuteMacro(EditorEngine editorEngine, CommandManager cmdM) {
		super(editorEngine);
		this.cmdM = cmdM;
	}

	public void execute() {
		Macro macro = MacroManager.get(this.macro);
		for(int i=0;i<macro.size();i++){
			Pair<Recordable, Memento> pair = macro.get(i);
			Recordable toExecute = (Recordable) pair.getKey().copy();
			toExecute.setMemento(pair.getValue());
			cmdM.executeCommand((Command) toExecute);
		}
	}

	public Command copy() {
		return new CommandExecuteMacro(editorEngine, cmdM);
	}

	public void setMemento(Memento memento) {
		this.macro = ((MementoExecuteMacro) memento).getMacroNumber();
		
	}

	public Memento getMemento() {
		return new MementoExecuteMacro(macro);
	}
}
