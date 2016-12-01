package memento;

public class MementoPaste implements Memento {

	private String content;
	private String oldContent;
	private int position;
	
	public MementoPaste(String content, String oldContent, int position) {
		this.content = content;
		this.oldContent = oldContent;
		this.position = position;
	}

	public String getContent() {
		return content;
	}

	public String getOldContent() {
		return oldContent;
	}

	public int getPosition() {
		return position;
	}
	
		
}
