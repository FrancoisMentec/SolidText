package solidText;

import java.util.Observable;

public class Buffer extends Observable{
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
	
	private StringBuffer text;
	private int startSelect = 0;
	private int endSelect = 0;
	
	public Buffer(){
		text = new StringBuffer("");
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
	
	public void setSelect(int start,int end){
		//System.out.println("start="+startSelect+"  end="+endSelect);
		if(start<0) start = 0;
		if(start>text.length()) start = text.length();
		if(end<0) end = 0;
		if(end>text.length()) end = text.length();
		startSelect = start;
		endSelect = end;
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getLength() {
		return text.length();
	}
	
	public String toString(){
		return text.toString();
	}
	
	public boolean inSelect(int pos){
		return pos>=Math.min(startSelect, endSelect) && pos<=Math.max(startSelect, endSelect);
	}

	/*public void addText(String text) {
		//System.out.println("Add text ["+text+"]");
		//System.out.println("start="+startSelect+"  end="+endSelect);
		if(startSelect!=endSelect) remove(); //Remove selection
		this.text.insert(startSelect, text); // = this.text.substring(0, startSelect) + text + this.text.substring(endSelect);
		startSelect += text.length();
		endSelect = startSelect;
		this.setChanged();
		this.notifyObservers();
	}*/
	
	//Remove the selection
	public void removeSelection(){
		int start = getFirst();
		int end = getLast();
		this.text.delete(start, end);
		startSelect = endSelect = start;
		
		this.setChanged();
		this.notifyObservers();
	}
	
	//Replace the selection
	public void replaceSelection(String text){
		int start = getFirst();
		int end = getLast();
		this.text.delete(start, end);
		this.text.insert(start, text);
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
	 */
	public void remove(){ remove(LEFT); }
	
	public void remove(int side){
		if(startSelect != endSelect){
			removeSelection();
			
			this.setChanged();
			this.notifyObservers();
		}else if(side==LEFT && startSelect>0){
			this.text.delete(startSelect - 1, startSelect);
			endSelect = --startSelect;
			
			this.setChanged();
			this.notifyObservers();
		}else if(side==RIGHT && startSelect<text.length()){
			this.text.delete(startSelect, startSelect + 1);
			
			this.setChanged();
			this.notifyObservers();
		}
	}

	/*public void remove() {
		startSelect = startSelect-1;
		if(startSelect<0) startSelect = 0;
		if(endSelect < startSelect) {
			int t = startSelect;
			startSelect = endSelect;
			endSelect = t;
		}
		if((endSelect - startSelect) > 1) {
			endSelect++;
		}
		this.text.delete(startSelect, endSelect); // = this.text.substring(0, startSelect) + this.text.substring(endSelect);
		endSelect = startSelect;
		this.setChanged();
		this.notifyObservers();
	}*/
	
	public String getSelectedText() {
		String r = "";
		int start = Math.min(startSelect, endSelect);
		int end = Math.max(startSelect, endSelect);
		if(end-start != 0){
			r = text.substring(start, end);
		}
		return r;
	}

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
		
		for(int i=0;i<text.length();i++){
			char c = text.charAt(i);
			
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
	
}
