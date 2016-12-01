package command;

import memento.Memento;
import memento.MementoMove;
import solidText.Buffer;

public class CommandMoveSelect extends Command implements Recordable {

	private int direction;

	public CommandMoveSelect(Buffer buffer, int direction) {
		super(buffer);
		this.direction = direction;
	}
	
	public CommandMoveSelect(Buffer buffer) {
		super(buffer);
	}

	public void execute() {
		if (direction == LEFT) {
			buffer.setSelect(buffer.getStartSelect(), buffer.getEndSelect() - 1);
		} else if (direction == RIGHT) {
			if (buffer.getEndSelect() < buffer.getLength())
				buffer.setSelect(buffer.getStartSelect(), buffer.getEndSelect() + 1);
		} else if (direction == UP) {
			System.out.println("Not implemented");
		} else if (direction == DOWN) {
			System.out.println("Not implemented");
		} else if (direction == ALL) {
			buffer.setSelect(0, buffer.getLength());
		}
	}

	public Command copy() {
		return new CommandMoveSelect(buffer, direction);
	}
	
	public void setMemento(Memento memento) {
		this.direction = ((MementoMove) memento).getDirection();
	}

	public Memento getMemento() {
		return new MementoMove(direction);
	}

}
