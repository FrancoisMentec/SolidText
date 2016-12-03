package command;

import memento.Memento;
import memento.MementoMove;
import solidText.EditorEngine;

public class CommandMove extends Command implements Recordable{
	
	private int direction;

	public CommandMove(EditorEngine editorEngine, int direction) {
		super(editorEngine);
		this.direction = direction;
	}
	
	public CommandMove(EditorEngine buffer) {
		super(buffer);
	}

	@Override
	public void execute() {
		if(direction==LEFT){
			editorEngine.setSelect(editorEngine.getStartSelect()-1, editorEngine.getStartSelect()-1);
		}else if(direction==RIGHT){
			editorEngine.setSelect(editorEngine.getStartSelect()+1, editorEngine.getStartSelect()+1);
		}else if(direction==UP){
			System.out.println("Not implemented");
		}else if(direction==DOWN){
			System.out.println("Not implemented");
		}else if(direction==END){
			editorEngine.setSelect(editorEngine.getLength(), editorEngine.getLength());
		}
	}

	public Command copy() {
		return new CommandMove(editorEngine);
	}

	public void setMemento(Memento memento) {
		this.direction = ((MementoMove) memento).getDirection();
	}

	public Memento getMemento() {
		return new MementoMove(direction);
	}

}
