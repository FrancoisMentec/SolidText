package command;

import memento.Memento;
import memento.MementoRemove;
import solidText.Buffer;

public class CommandRemove extends Command implements Reversible, Recordable{
	public static final int LEFT = 0, RIGHT = 1, SELECT = 2;
	
	private int side;
	private int startSelect;
	private int endSelect;
	private String oldContent;
	private int selectAfterExecute;
	
	public CommandRemove(Buffer buffer, int side) {
		super(buffer);
		this.side = side;
		this.startSelect = buffer.getStartSelect();
		this.endSelect = buffer.getEndSelect();
	}
	
	public CommandRemove(Buffer buffer) {
		super(buffer);
	}

	public void execute() {
		buffer.setSelect(this.startSelect, this.endSelect);
		if(this.side==SELECT){
			this.oldContent = buffer.removeSelection();
		}else{
			this.oldContent = buffer.remove(side);
		}
		this.selectAfterExecute = buffer.getStartSelect();
	}

	public void revert() {
		buffer.setSelect(this.selectAfterExecute, this.selectAfterExecute);
		buffer.replaceSelection(this.oldContent);
	}

	public Command copy() {
		return new CommandRemove(buffer);
	}

	public void setMemento(Memento memento) {
		MementoRemove temp = (MementoRemove) memento;
		this.side = temp.getSide();
	}

	public Memento getMemento() {
		return new MementoRemove(side);
	}
	
	
}
