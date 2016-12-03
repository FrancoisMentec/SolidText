package command;

import memento.Memento;
import memento.MementoRemove;
import solidText.EditorEngine;

public class CommandRemove extends Command implements Reversible, Recordable{
	public static final int LEFT = 0, RIGHT = 1, SELECT = 2;
	
	private int side;
	private int startSelect;
	private int endSelect;
	private String oldContent;
	private int selectAfterExecute;
	
	public CommandRemove(EditorEngine editorEngine, int side) {
		super(editorEngine);
		this.side = side;
		this.startSelect = editorEngine.getStartSelect();
		this.endSelect = editorEngine.getEndSelect();
	}
	
	public CommandRemove(EditorEngine editorEngine) {
		super(editorEngine);
	}

	public void execute() {
		editorEngine.setSelect(this.startSelect, this.endSelect);
		if(this.side==SELECT){
			this.oldContent = editorEngine.removeSelection();
		}else{
			this.oldContent = editorEngine.remove(side);
		}
		this.selectAfterExecute = editorEngine.getStartSelect();
	}

	public void revert() {
		editorEngine.setSelect(this.selectAfterExecute, this.selectAfterExecute);
		editorEngine.replaceSelection(this.oldContent);
	}

	public Command copy() {
		return new CommandRemove(editorEngine);
	}

	public void setMemento(Memento memento) {
		MementoRemove temp = (MementoRemove) memento;
		this.side = temp.getSide();
	}

	public Memento getMemento() {
		return new MementoRemove(side);
	}
	
	
}
