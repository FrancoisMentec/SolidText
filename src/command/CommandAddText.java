package command;

import memento.Memento;
import memento.MementoAddText;
import solidText.EditorEngine;

public class CommandAddText extends Command implements Reversible, Recordable{
	
	private String text;
	
	private int startSelect;
	private int endSelect;
	private String oldContent;
	private int endSelectAfterExecute;
	
	public CommandAddText(EditorEngine editorEngine, String text) {
		super(editorEngine);
		this.text = text;
	}
	
	public CommandAddText(EditorEngine editorEngine) {
		super(editorEngine);
	}

	public void execute() {
		this.startSelect = editorEngine.getStartSelect();
		this.endSelect = editorEngine.getEndSelect();
		this.oldContent = editorEngine.getSelectedText();
		editorEngine.setSelect(this.startSelect, this.endSelect);
		editorEngine.replaceSelection(text);
		endSelectAfterExecute = editorEngine.getEndSelect();
	}

	public void revert() {
		System.out.println(endSelectAfterExecute-text.length() + " " + endSelectAfterExecute);
		editorEngine.setSelect(endSelectAfterExecute-text.length(), endSelectAfterExecute);
		editorEngine.replaceSelection(this.oldContent);
	}

	public Command copy() { //used only for macro
		return new CommandAddText(editorEngine);
	}

	public void setMemento(Memento memento) {
		MementoAddText temp = (MementoAddText) memento;
		text = new String(temp.getText());
	}

	public Memento getMemento() {
		return new MementoAddText(text);
	}

}
