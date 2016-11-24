package command;

import solidText.Buffer;

abstract public class Command {
	public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3, END = 4, ALL = 5;
	
	protected Buffer buffer;
	
	public Command(Buffer buffer){
		this.buffer = buffer;
	}
	
	public abstract void execute();
	
}
