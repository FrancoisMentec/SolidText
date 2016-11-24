package command;

import solidText.Buffer;

public class CommandMove extends Command implements Recordable{
	
	private int direction;

	public CommandMove(Buffer buffer, int direction) {
		super(buffer);
		this.direction = direction;
	}

	@Override
	public void execute() {
		if(direction==LEFT){
			buffer.setSelect(buffer.getStartSelect()-1, buffer.getStartSelect()-1);
		}else if(direction==RIGHT){
			buffer.setSelect(buffer.getStartSelect()+1, buffer.getStartSelect()+1);
		}else if(direction==UP){
			System.out.println("Not implemented");
		}else if(direction==DOWN){
			System.out.println("Not implemented");
		}else if(direction==END){
			buffer.setSelect(buffer.getLength(), buffer.getLength());
		}
	}

	public Command copy() {
		return new CommandMove(buffer, direction);
	}

}
