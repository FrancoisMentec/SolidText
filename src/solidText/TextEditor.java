package solidText;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import command.Command;
import command.CommandAddText;
import command.CommandRemove;

public class TextEditor {
	private Buffer buffer;
	private Collection<Command> commandList;
	private Label view;
	
	public TextEditor(){
		buffer = new Buffer();
		commandList = new ArrayList<Command>();
		view = new Label(buffer.toString());
	}
	
	public String getText(){
		return buffer.toString();
	}

	public void addText(String text) {
		buffer.addText(text);
		view.setText(buffer.toString());
	}
	
	public Label getView(){
		return view;
	}

	public void executeCommand(Command command){
		this.commandList.add(command);
		command.execute();
		
		view.setText(buffer.toString());
	}
	
	public void handleKeyEvent(KeyEvent k) {
		if(k.getCode()==KeyCode.BACK_SPACE){
			this.executeCommand(new CommandRemove(buffer));
		}else{
			this.executeCommand(new CommandAddText(buffer, k.getText()));
		}
	}
}
