package command.macro;

import java.util.ArrayList;

import command.Command;
import command.Recordable;

// Act as Caretaker for the Memento design pattern (used for macros) 

public class Macro {
	private ArrayList<Command> commands;
	
	public Macro(){
		commands = new ArrayList<Command>();
	}
	
	public void reset(){
		commands.clear();
	}
	
	public void addCommand(Command command){
		if(command instanceof Recordable){
			commands.add(command);
		}else{
			System.out.println("Erreur, tentative d'ajout d'une command non Recordable à une macro");
		}
	}
	
	public Command get(int i){
		return commands.get(i);
	}
	
	public int size(){
		return commands.size();
	}
}
