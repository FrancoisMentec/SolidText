package command;

import memento.Memento;
import memento.MementoAddText;
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
	}
	
	public CommandAddText(Buffer buffer) {
		super(buffer);
	}

	public void execute() {
		this.startSelect = buffer.getStartSelect();
		this.endSelect = buffer.getEndSelect();
		System.out.println("AddText select " + startSelect + " end " + endSelect);
		this.oldContent = buffer.getSelectedText();
		buffer.setSelect(this.startSelect, this.endSelect);
		buffer.replaceSelection(text);
		endSelectAfterExecute = buffer.getEndSelect();
	}

	public void revert() {
		System.out.println(endSelectAfterExecute-text.length() + " " + endSelectAfterExecute);
		buffer.setSelect(endSelectAfterExecute-text.length(), endSelectAfterExecute);
		buffer.replaceSelection(this.oldContent);
	}

	public Command copy() { //used only for macro
		return new CommandAddText(buffer);
	}

	public void setMemento(Memento memento) {
		MementoAddText temp = (MementoAddText) memento;
		text = new String(temp.getText());
	}

	public Memento getMemento() {
		return new MementoAddText(text);
	}

}
