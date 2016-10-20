package command;

import solidText.Buffer;

public class CommandRemove extends Command{

	public CommandRemove(Buffer buffer) {
		super(buffer);
	}

	public void execute() {
		buffer.remove();
	}

	@Override
	public void revert() {
		// TODO Auto-generated method stub
		
	}
}
