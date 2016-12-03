package command;

import memento.Memento;
import memento.MementoCopy;
import solidText.EditorEngine;

public class CommandCopy extends Command implements Recordable {

	public CommandCopy(EditorEngine editorEngine) {
		super(editorEngine);
	}

	public void execute() {
		String content = editorEngine.getSelectedText();
		System.out.println("Copy : "+content);
		if(!(content == "")) {
			ClipboardManager.setContent(content);
		}
	}

	public Command copy() {
		return new CommandCopy(editorEngine);
	}

	public void setMemento(Memento memento) {
		// Nothing to do
	}

	public Memento getMemento() {
		return new MementoCopy();
	}
}
