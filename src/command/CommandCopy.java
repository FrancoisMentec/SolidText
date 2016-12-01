package command;

import memento.Memento;
import memento.MementoCopy;
import solidText.Buffer;

public class CommandCopy extends Command implements Recordable {

	public CommandCopy(Buffer buffer) {
		super(buffer);
	}

	public void execute() {
		String content = buffer.getSelectedText();
		System.out.println("Copy : "+content);
		if(!(content == "")) {
			ClipboardManager.setContent(content);
		}
	}

	public Command copy() {
		return new CommandCopy(buffer);
	}

	public void setMemento(Memento memento) {
		// Nothing to do
	}

	public Memento getMemento() {
		return new MementoCopy();
	}
}
