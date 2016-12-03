package command;

import memento.Memento;
import memento.MementoPaste;
import solidText.EditorEngine;

public class CommandPaste extends Command implements Reversible, Recordable{
	
	private String content;
	private String oldContent;
	private int position;

	public CommandPaste(EditorEngine editorEngine) {
		super(editorEngine);
		content = ClipboardManager.getContent();
		oldContent = editorEngine.getSelectedText();
		position = editorEngine.getFirst();
	}

	public void execute() {
		editorEngine.setSelect(position, position+oldContent.length());
		editorEngine.replaceSelection(content);
		System.out.println("Paste : "+content);
	}

	@Override
	public void revert() {
		editorEngine.setSelect(position, position+content.length());
		editorEngine.replaceSelection(oldContent);
	}

	public Command copy() {
		return new CommandPaste(editorEngine);
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
