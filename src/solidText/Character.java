package solidText;


public class Character {
	private char character;
	private Character previous;
	private Character next;
	
	public Character(char character, Character previous, Character next){
		this.character = character;
		this.previous = previous;
		this.next = next;
	}
	
	public char getChar(){
		return this.character;
	}
	
	public Character getPrevious(){
		return this.previous;
	}
	
	public Character getNext(){
		return this.next;
	}

	public void setNext(Character next) {
		this.next = next;
	}

	public void setPrevious(Character previous) {
		this.previous = previous;
	}
}
