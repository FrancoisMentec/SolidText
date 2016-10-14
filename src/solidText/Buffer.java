package solidText;

public class Buffer {
	public static final String CSS_STYLE = "<style>"
			+ "body{"
				+ "white-space: pre;"
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
				+ "background-color: blue;"
				+ "color: white;"
			+ "}"
			+ "</style>";
	
	private String text = "";
	private int startSelect = 0;
	private int endSelect = 0;
	
	public Buffer(){
		
	}
	
	public int getStartSelect() {
		return startSelect;
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
	}
	
	public int getLength() {
		return text.length();
	}
	
	public String toString(){
		return text;
	}

	public void addText(String text) {
		//System.out.println("Add text ["+text+"]");
		//System.out.println("start="+startSelect+"  end="+endSelect);
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

	public String toHtml() {
		/*String html = text.substring(0, startSelect) + "<span class=\"cursor\"></span>" + text.substring(startSelect);
		html = "<html>" + CSS_STYLE + "<body>" + html.replace("\n", "<br>").replace("\r", "<br>") + "</body></html>";*/
		String html = text.substring(0, Math.min(startSelect, endSelect))
				+ "<span class=\"cursor\"></span><span class=\"selection\">"
				+ text.substring(Math.min(startSelect, endSelect), Math.max(startSelect, endSelect))
				+ "</span>"
				+ text.substring(Math.max(startSelect, endSelect));
		String str = "";
		String line = "";
		int start = 0;
		for(int i=0;i<html.length();i++){
			if(html.charAt(i)!='\r'){
				line += html.charAt(i);
			}else{
				if(startSelect>=start && startSelect<=i){
					line = "<div class=\"line current-line\">" + line + "</div>";
				}else{
					line = "<div class=\"line\">" + line + "</div>";
				}
				str += line;
				start = i+1;
				line = "";
			}
		}
		if(startSelect>=start && startSelect<=html.length()){
			line = "<div class=\"line current-line\">" + line + "</div>";
		}else{
			line = "<div class=\"line\">" + line + "</div>";
		}
		str += line;
		
		html = str;
		html = "<html>" + CSS_STYLE + "<body>" + html + "</body></html>";
		//html = "<html>" + CSS_STYLE + "<body><div class=\"line\">" + html.replace("\n", "</div><div class=\"line\">").replace("\r", "</div><div class=\"line\">") + "</div></body></html>";
		return html;
	}
}
