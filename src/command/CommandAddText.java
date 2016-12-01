package command;

import Memento.Memento;
import Memento.MementoAddText;
import solidText.Buffer;

public class CommandAddText extends Command implements Reversible, Recordable{
	
	private String text;
	
	private int startSelect;
	private int endSelect;
	private String oldContent;
	private int endSelectAfterExecute;
	
	public CommandAddText(Buffer buffer, String text) {
		super(buffer);
		this.text = text;
		this.startSelect = buffer.getStartSelect();
		this.endSelect = buffer.getEndSelect();
		this.oldContent = buffer.getSelectedText();
	}

	public void execute() {
		buffer.setSelect(this.startSelect, this.endSelect);
		buffer.replaceSelection(text);
		endSelectAfterExecute = buffer.getEndSelect();
	}

	public void revert() {
		buffer.setSelect(endSelectAfterExecute-text.length(), endSelectAfterExecute);
		buffer.replaceSelection(this.oldContent);
	}

	public Command copy() {
		return new CommandAddText(buffer, text);
	}

	public void setMemento(Memento memento) {
		MementoAddText temp = (MementoAddText) memento;
		startSelect = temp.getStartSelect();
		endSelect = temp.getEndSelect();
		oldContent = temp.getOldContent();
		endSelectAfterExecute = temp.getEndSelectAfterExecute();
		text = temp.getText();
	}

	public Memento getMemento() {
		return new MementoAddText(text, startSelect, endSelect, oldContent, endSelectAfterExecute);
	}

}
