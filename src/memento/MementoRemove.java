package memento;

public class MementoRemove implements Memento {
	
	private int side;
	
	public MementoRemove(int side) {
		this.side = side;
	}

	public int getSide() {
		return side;
	}
	
}
