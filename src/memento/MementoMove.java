package memento;

public class MementoMove implements Memento {
	
	private int direction;
	
	public MementoMove(int direction) {
		this.direction = direction;
	}
	
	public int getDirection(){
		return this.direction;
	}

}
