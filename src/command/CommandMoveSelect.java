package command;

import memento.Memento;
import memento.MementoMove;
import solidText.EditorEngine;

public class CommandMoveSelect extends Command implements Recordable {

	private int direction;

	public CommandMoveSelect(EditorEngine editorEngine, int direction) {
		super(editorEngine);
		this.direction = direction;
	}
	
	public CommandMoveSelect(EditorEngine buffer) {
		super(buffer);
	}

	public void execute() {
		if (direction == LEFT) {
			editorEngine.setSelect(editorEngine.getStartSelect(), editorEngine.getEndSelect() - 1);
		} else if (direction == RIGHT) {
			if (editorEngine.getEndSelect() < editorEngine.getLength())
				editorEngine.setSelect(editorEngine.getStartSelect(), editorEngine.getEndSelect() + 1);
		} else if (direction == UP) {
			System.out.println("Not implemented");
		} else if (direction == DOWN) {
			System.out.println("Not implemented");
		} else if (direction == ALL) {
			editorEngine.setSelect(0, editorEngine.getLength());
		}
	}

	public Command copy() {
		return new CommandMoveSelect(editorEngine, direction);
	}
	
	public void setMemento(Memento memento) {
		this.direction = ((MementoMove) memento).getDirection();
	}

	public Memento getMemento() {
		return new MementoMove(direction);
	}

}
