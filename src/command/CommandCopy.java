package command;

import solidText.Buffer;

public class CommandCopy extends Command {

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
}
