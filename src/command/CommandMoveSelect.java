package command;

import solidText.Buffer;

public class CommandMoveSelect extends Command{
	
	private int direction;

	public CommandMoveSelect(Buffer buffer, int direction) {
		super(buffer);
		this.direction = direction;
	}

	@Override
	public void execute() {
		if(direction==LEFT){
			buffer.setSelect(buffer.getStartSelect(), buffer.getEndSelect()-1);
		}else if(direction==RIGHT){
			if(buffer.getEndSelect()<buffer.getLength()) buffer.setSelect(buffer.getStartSelect(), buffer.getEndSelect()+1);
		}else if(direction==UP){
			System.out.println("Not implemented");
		}else if(direction==DOWN){
			System.out.println("Not implemented");
		}
	}

	@Override
	public void revert() {
		// TODO Auto-generated method stub
		
	}

}
