package command;

import solidText.Buffer;

public class CommandPaste extends Command implements Reversible{
	
	private String content;
	private String oldContent;
	private int position;

	public CommandPaste(Buffer buffer) {
		super(buffer);
		content = ClipboardManager.getContent();
		oldContent = buffer.getSelectedText();
		position = buffer.getFirst();
	}

	public void execute() {
		buffer.setSelect(position, position+oldContent.length());
		buffer.replaceSelection(content);
		System.out.println("Paste : "+content);
	}

	@Override
	public void revert() {
		buffer.setSelect(position, position+content.length());
		buffer.replaceSelection(oldContent);
	}

}
