package command;

import solidText.Buffer;

public class CommandAddText extends Command implements Reversible{
	
	private String text;
	
	private int endSelect;
	
	public CommandAddText(Buffer buffer, String text) {
		super(buffer);
		this.text = text;
	}

	public void execute() {
		buffer.replaceSelection(text);
		endSelect = buffer.getEndSelect();
	}

	public void revert() {
		buffer.setSelect(endSelect-text.length(), endSelect);
		buffer.remove();
	}

}
