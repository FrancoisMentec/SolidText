package command;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public abstract class ClipboardManager {
	
	private static Clipboard clipboard = Clipboard.getSystemClipboard();
	
	public static String getContent(){
		String r = clipboard.getString();
		if(r == null) {
			r = "";
		}
		return r;
	}
	
	public static void setContent(String content) {
		ClipboardContent cc = new ClipboardContent();
		cc.putString(content);
		clipboard.setContent(cc);
	}

}
