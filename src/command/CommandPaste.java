package command;

import solidText.Buffer;

public class CommandPaste extends Command implements Reversible{
	
	private String oldContent;
	private int position;
	private int length;

	public CommandPaste(Buffer buffer) {
		super(buffer);
		
	}

	public void execute() {
		String content = ClipboardManager.getContent();
		oldContent = buffer.getSelectedText();
		position = buffer.getFirst();
		length = content.length();
		buffer.replaceSelection(content);
		System.out.println("Paste : "+content);
	}

	@Override
	public void revert() {
		buffer.setSelect(position, position+length);
		buffer.replaceSelection(oldContent);
	}

}
