package command;

import solidText.Buffer;

public class CommandMove extends Command implements Reversible{
	
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
			System.out.println("plop");
			buffer.setSelect(buffer.getLength(), buffer.getLength());
		}
	}

	@Override
	public void revert() {
		// TODO Auto-generated method stub
		
	}

}
