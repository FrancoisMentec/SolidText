package command;

import solidText.Buffer;

public class CommandRemove extends Command implements Reversible{
	public static final int LEFT = 0, RIGHT = 1;
	
	private int side;
	
	public CommandRemove(Buffer buffer, int side) {
		super(buffer);
		this.side = side;
	}

	public void execute() {
		buffer.remove(side);
	}

	@Override
	public void revert() {
		// TODO Auto-generated method stub
		
	}
}
