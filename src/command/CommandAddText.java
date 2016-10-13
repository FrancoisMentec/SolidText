package command;

import solidText.Buffer;

public class CommandAddText extends Command{
	
	private String text;
	
	public CommandAddText(Buffer buffer, String text) {
		super(buffer);
		this.text = text;
	}

	public void execute() {
		buffer.addText(text);
	}

}
