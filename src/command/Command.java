package command;

import solidText.EditorEngine;

abstract public class Command {
	public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3, END = 4, ALL = 5;
	
	protected EditorEngine editorEngine;
	
	public Command(EditorEngine buffer){
		this.editorEngine = buffer;
	}
	
	public abstract void execute();
	
}
