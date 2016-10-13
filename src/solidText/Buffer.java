package solidText;

public class Buffer {
	private Character first;
	private Character startSelect;
	private Character stopSelect;
	
	public Buffer(){
		
	}
	
	public String toString(){
		String str = "";
		Character current = first;
		while(current!=null){
			str += current.getChar();
			current = current.getNext();
		}
		return str;
	}
}
