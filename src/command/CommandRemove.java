package command;

import solidText.Buffer;

public class CommandRemove extends Command implements Reversible{
	public static final int LEFT = 0, RIGHT = 1;
	
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

	public void execute() {
		buffer.setSelect(this.startSelect, this.endSelect);
		this.oldContent = buffer.remove(side);
		this.selectAfterExecute = buffer.getStartSelect();
	}

	public void revert() {
		buffer.setSelect(this.selectAfterExecute, this.selectAfterExecute);
		buffer.replaceSelection(this.oldContent);
	}
}
