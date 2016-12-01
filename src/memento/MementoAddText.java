package memento;

public class MementoAddText implements Memento {

	private String text;

	public MementoAddText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}
