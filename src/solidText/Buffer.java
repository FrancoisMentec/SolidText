package solidText;

public class Buffer {
	private String text = "";
	private int startSelect = 0;
	private int endSelect = 0;
	
	public Buffer(){
		
	}
	
	public String toString(){
		return text;
	}

	public void addText(String text) {
		this.text = this.text.substring(0, startSelect) + text + this.text.substring(endSelect);
		startSelect += text.length();
		endSelect = startSelect;
	}

	public void remove() {
		startSelect = startSelect-1;
		if(startSelect<0) startSelect = 0;
		this.text = this.text.substring(0, startSelect) + this.text.substring(endSelect);
		endSelect = startSelect;
	}
}
