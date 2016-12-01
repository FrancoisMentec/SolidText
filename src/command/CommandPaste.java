package command;

import memento.Memento;
import memento.MementoPaste;
import solidText.Buffer;

public class CommandPaste extends Command implements Reversible, Recordable{
	
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

	public Command copy() {
		return new CommandPaste(buffer);
	}

	public void setMemento(Memento memento) {
		this.content = new String(((MementoPaste) memento).getContent());
		this.oldContent = new String(((MementoPaste) memento).getOldContent());
		this.position = ((MementoPaste) memento).getPosition();
	}

	public Memento getMemento() {
		return new MementoPaste(content, oldContent, position);
	}

}
