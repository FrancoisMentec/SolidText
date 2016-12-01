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
		shift.put("!", "§");
		shift.put("ù", "%");
		shift.put("^", "¨");
		shift.put("*", "µ");
		shift.put("$", "£");
		shift.put("&", "1");
		shift.put("é", "2");
		shift.put("\"", "3");
		shift.put("'", "4");
		shift.put("(", "5");
		shift.put("-", "6");
		shift.put("è", "7");
		shift.put("_", "8");
		shift.put("ç", "9");
		shift.put("à", "0");
		shift.put(")", "°");
		shift.put("=", "+");
		
		alt = new HashMap<String, String>();
		alt.put("$", "¤");
		alt.put("é", "~");
		alt.put("\"", "#");
		alt.put("'", "{");
		alt.put("(", "[");
		alt.put("-", "|");
		alt.put("è", "`");
		alt.put("_", "\\");
		alt.put("ç", "^");
		alt.put("à", "@");
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
