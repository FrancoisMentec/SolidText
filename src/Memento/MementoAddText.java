package Memento;

public class MementoAddText implements Memento {

	private String text;

	private int startSelect;
	private int endSelect;
	private String oldContent;
	private int endSelectAfterExecute;

	public MementoAddText(String text2, int startSelect2, int endSelect2, String oldContent2, int endSelectAfterExecute2) {
		text = text2;
		startSelect = startSelect2;
		endSelect = endSelect2;
		oldContent = oldContent2;
		endSelectAfterExecute = endSelectAfterExecute2;
	}

	public String getText() {
		return text;
	}

	public int getStartSelect() {
		return startSelect;
	}

	public int getEndSelect() {
		return endSelect;
	}

	public String getOldContent() {
		return oldContent;
	}

	public int getEndSelectAfterExecute() {
		return endSelectAfterExecute;
	}
	
}
