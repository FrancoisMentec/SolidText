package command.macro;

import java.util.ArrayList;

import command.Command;
import command.Recordable;
import javafx.util.Pair;
import memento.Memento;

// Act as Caretaker for the Memento design pattern (used for macros) 

public class Macro {
	private ArrayList<Pair<Recordable, Memento>> commands; 
	
	public Macro(){
		commands = new ArrayList<>();
	}
	
	public void reset(){
		commands.clear();
	}
	
	public void addCommand(Command command){
		if(command instanceof Recordable){
			Recordable recCmd = (Recordable) command;
			commands.add(new Pair<Recordable, Memento>(recCmd, recCmd.getMemento()));
		}else{
			System.err.println("Error, non-recordable commands can not be part of a macro");
		}
	}
	
	public Pair<Recordable, Memento> get(int i) {
		return commands.get(i);
	}
	
	public int size(){
		return commands.size();
	}
}
