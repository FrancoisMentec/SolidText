package command;

import solidText.Buffer;

abstract public class Command {
	protected Buffer buffer;
	
	public Command(Buffer buffer){
		this.buffer = buffer;
	}
	
	public abstract void execute();
}
