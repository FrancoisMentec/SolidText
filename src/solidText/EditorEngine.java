package solidText;

import java.util.Observable;

public class EditorEngine extends Observable implements Engine{
	public static final String CSS_STYLE = "<style>"
			+ "body{"
				+ "white-space: pre;"
				+ "overflow: auto;"
			+ "}"
			+ ""
			+ ".line{"
				+ "width: 100%;"
				+ "height: 1em;"
				+ "line-height: 1em;"
				+ "margin: 0;"
				+ "overflow: hidden;"
			+ "}"
			+ ""
			+ ".line-number{"
				+ "display: inline-block;"
				+ "font-size: 0.8em;"
				+ "width: 3em;"
				+ "text-align: right;"
				+ "border-right: 1px solid gray;"
				+ "color: gray;"
				+ "padding-right: 3px;"
				+ "margin-right: 3px;"
			+ "}"
			+ ""
			+ ".current-line{"
				+ "background-color: #e8f2fe;"
			+ "}"
			+ ""
			+ "@-webkit-keyframes blink{"
				+ "0%{opacity: 1;}"
				+ "50%{opacity: 1;}"
				+ "51%{opacity: 0;}"
				+ "100%{opacity: 0;}"
			+ "}"
			+ ""
			+ ".cursor{"
				+ "display: inline-block;"
				+ "position: relative;"
				+ "top: 50%;"
				+ "-webkit-transform: translateY(-50%);"
				+ "margin: 0;"
				+ "box-sizing: border-box;"
				+ "width: 2px;"
				+ "height: 80%;"
				+ "background-color: black;"
				+ "-webkit-animation: blink 1s infinite;"
			+ "}"
			+ ""
			+ ".selection{"
				+ "background-color: #3399ff;"
				+ "color: white;"
			+ "}"
			+ "</style>";
	
	public static final int LEFT = 0, RIGHT = 1;
	
	private StringBuffer buffer;
	private int startSelect = 0;
	private int endSelect = 0;
	
	public EditorEngine(){
		buffer = new StringBuffer("");
	}
	
	public int getStartSelect() {
		return startSelect;
	}
	
	//Get the first select pos encounter (start or end)
	
	public int getFirst(){
		return Math.min(this.startSelect, this.endSelect);
	}
	
	//Get the last select pos encounter (start or end)
	public int getLast(){
		return Math.max(this.startSelect, this.endSelect);
	}
	
	public int getEndSelect() {
		return endSelect;
	}
	
	public void setSelect(int start, int end){
		//System.out.println("start="+startSelect+"  end="+endSelect);
		if(start<0) start = 0;
		if(start>buffer.length()) start = buffer.length();
		if(end<0) end = 0;
		if(end>buffer.length()) end = buffer.length();
		startSelect = start;
		endSelect = end;
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getLength() {
		return buffer.length();
	}
	
	public String toString(){
		return buffer.toString();
	}
	
	public boolean inSelect(int pos){
		return pos>=Math.min(startSelect, endSelect) && pos<=Math.max(startSelect, endSelect);
	}
	
	/**
	 * Remove the selection
	 * @return the deleted text
	 */
	public String removeSelection(){
		String deletedText = this.getSelectedText();
		int start = getFirst();
		int end = getLast();
		this.buffer.delete(start, end);
		startSelect = endSelect = start;
		
		this.setChanged();
		this.notifyObservers();
		
		return deletedText;
	}
	

	//Replace the selection
	public void replaceSelection(String text){
		int start = getFirst();
		int end = getLast();
		this.buffer.delete(start, end);
		this.buffer.insert(start, text);
		startSelect = endSelect = start + text.length();
		
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Remove current selection, previous char or next char
	 * If selection remove it
	 * else
	 * 	If No param or side=LEFT remove previous
	 * 	Else remove next
	 * @return the deleted text
	 */
	public String remove(){ return remove(LEFT); }
	
	public String remove(int side){
		String deletedText = "";
		if(startSelect != endSelect){
			deletedText = removeSelection();
			
			this.setChanged();
			this.notifyObservers();
		}else if(side==LEFT && startSelect>0){
			deletedText = this.buffer.substring(startSelect - 1, startSelect);
			this.buffer.delete(startSelect - 1, startSelect);
			endSelect = --startSelect;
			
			this.setChanged();
			this.notifyObservers();
		}else if(side==RIGHT && startSelect<buffer.length()){
			deletedText = this.buffer.substring(startSelect, startSelect + 1);
			this.buffer.delete(startSelect, startSelect + 1);
			
			this.setChanged();
			this.notifyObservers();
		}
		return deletedText;
	}
	
	public String getSelectedText() {
		String r = "";
		int start = Math.min(startSelect, endSelect);
		int end = Math.max(startSelect, endSelect);
		if(end-start != 0){
			r = buffer.substring(start, end);
		}
		return r;
	}

	
	/**
	 * Generate the HTML representation of the buffer
	 * @return the HTML
	 */
	public String toHtml() {
		boolean inSelect = false;
		boolean currentLine = false;
		int nbLine = 1; 
		String html = "";
		String line = "";
		
		//Affichage du curseur (Cas particulier)
		if(endSelect==0){
			line += "<span class=\"cursor\"></span>";
			currentLine = true;
		}
		
		for(int i=0;i<buffer.length();i++){
			char c = buffer.charAt(i);
			
			//System.out.println("i = "+i+" s = "+startSelect+" e = "+endSelect);
			
			//Affichage de la selection
			if(i==Math.min(startSelect, endSelect)){
				line += "<span class=\"selection\">";
				inSelect = true;
				currentLine = true;
			}
			
			if(i==Math.max(startSelect, endSelect)){
				line += "</span>";
				inSelect = false;
			}
			
			
			if(c=='\r' || c=='\n'){
				//Gestion du retour � ligne
				if(inSelect){
					line += "</span>";
				}
				
				if(currentLine){
					html += "<div class=\"line current-line\">";
				}else{
					html += "<div class=\"line\">";
				}
				html += "<span class=\"line-number\">" + nbLine + "</span>" + line + "</div>";
				nbLine++;
				
				line = "";
				if(!inSelect){
					currentLine = false;//Seul endroit o� current line peut-�tre mit � false
				}
				
				if(inSelect){
					line += "<span class=\"selection\">";
				}
			}else{
				line += c;//Affichage du caract�re
			}
			
			//Affichage du curseur
			if(i+1==endSelect){
				line += "<span class=\"cursor\"></span>";
				currentLine = true;
			}
		}
		
		//Affichage de la derni�re ligne (hors du for)
		if(currentLine){
			html += "<div class=\"line current-line\">";
		}else{
			html += "<div class=\"line\">";
		}
		html += "<span class=\"line-number\">" + nbLine + "</span>" + line + "</div>";
		nbLine++;
		
		//Concat�nation du contenu avec le reste de la vue
		html = "<html>" + CSS_STYLE + "<body>" + html + "</body></html>";
		return html;
	}

	public StringBuffer getBuffer() {
		return this.buffer;
	}
	
}
