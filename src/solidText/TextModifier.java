package solidText;

import java.util.HashMap;

public class TextModifier {
	private static TextModifier instance = new TextModifier();
	
	private String letter; 
	private HashMap<String, String> shift;
	private HashMap<String, String> alt;
	
	private TextModifier(){
		letter = "abcdefghijklmnopqrstuvwxyz";
		
		shift = new HashMap<String, String>();
		shift.put("<", ">");
		shift.put(",", "?");
		shift.put(";", ".");
		shift.put(":", "/");
		shift.put("!", "�");
		shift.put("�", "%");
		shift.put("^", "�");
		shift.put("*", "�");
		shift.put("$", "�");
		shift.put("&", "1");
		shift.put("�", "2");
		shift.put("\"", "3");
		shift.put("'", "4");
		shift.put("(", "5");
		shift.put("-", "6");
		shift.put("�", "7");
		shift.put("_", "8");
		shift.put("�", "9");
		shift.put("�", "0");
		shift.put(")", "�");
		shift.put("=", "+");
		
		alt = new HashMap<String, String>();
		alt.put("$", "�");
		alt.put("�", "~");
		alt.put("\"", "#");
		alt.put("'", "{");
		alt.put("(", "[");
		alt.put("-", "|");
		alt.put("�", "`");
		alt.put("_", "\\");
		alt.put("�", "^");
		alt.put("�", "@");
		alt.put(")", "]");
		alt.put("=", "}");
	}
	
	public static String shift(String text){
		return instance.getShift(text);
	}
	
	private String getShift(String text){
		if(letter.contains(text)){
			text = text.toUpperCase();
		}else{
			String t = shift.get(text);
			if(t!=null)
				text = t;
		}
		return text;
	}
	
	public static String alt(String text){
		return instance.getAlt(text);
	}
	
	private String getAlt(String text){
		String t = alt.get(text);
		if(t!=null)
			text = t;
		return text;
	}
}
