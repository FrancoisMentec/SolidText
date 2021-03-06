package solidText;

public interface Engine {
	
	// Clipboard, Copy, Cut, Paste are managed by ClipboardManager
	// TODO : copy, cut, paste methods
	
	/**
	 * Return the text buffer
	 * @return StringBuffer the buffer
	 */
	public StringBuffer getBuffer();
	
	/**
	 * Return the string representation of the current selection
	 * @return String the selected text
	 */
	public String getSelectedText();

	/**
	 * Replace the current selected text
	 * If no text is selected, insert the text at the empty selection position
	 * @param substring the text to insert
	 */
	public void replaceSelection(String substring);
	
	/**
	 * Self explained
	 * @param start index of the start of the selection
	 * @param stop index of the end of the selection
	 */
	public void setSelect(int start, int stop);

}
