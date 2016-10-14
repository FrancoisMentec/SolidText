package solidText;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import command.Command;
import command.CommandAddText;
import command.CommandMove;
import command.CommandMoveSelect;
import command.CommandRemove;

public class TextEditor {
	private Buffer buffer;
	private Collection<Command> commandList;
	private WebView view;
	private boolean shift = false;
	
	public TextEditor(){
		buffer = new Buffer();
		commandList = new ArrayList<Command>();
		view = new WebView();
		updateView();
	}
	
	public void updateView(){
		view.getEngine().loadContent(buffer.toHtml());
	}
	
	public String getText(){
		return buffer.toString();
	}

	public void addText(String text) {
		buffer.addText(text);
		updateView();
	}
	
	public Node getView(){
		return view;
	}

	public void executeCommand(Command command){
		this.commandList.add(command);
		command.execute();
		
		updateView();
	}
	
	public void handleKeyEvent(KeyEvent k) {
		//System.out.println(k.getCode());
		
		if(k.getEventType() == KeyEvent.KEY_PRESSED){
			if(k.getCode()==KeyCode.LEFT){
				if(shift) this.executeCommand(new CommandMoveSelect(buffer, Command.LEFT));
				else this.executeCommand(new CommandMove(buffer, Command.LEFT));
			}else if(k.getCode()==KeyCode.RIGHT){
				if(shift) this.executeCommand(new CommandMoveSelect(buffer, Command.RIGHT));
				else this.executeCommand(new CommandMove(buffer, Command.RIGHT));
			}else if(k.getCode()==KeyCode.UP){
				if(shift) this.executeCommand(new CommandMoveSelect(buffer, Command.UP));
				else this.executeCommand(new CommandMove(buffer, Command.UP));
			}else if(k.getCode()==KeyCode.DOWN){
				if(shift) this.executeCommand(new CommandMoveSelect(buffer, Command.DOWN));
				else this.executeCommand(new CommandMove(buffer, Command.DOWN));
			}else if(k.getCode()==KeyCode.END){
				this.executeCommand(new CommandMove(buffer, Command.END));
			}else if(k.getCode()==KeyCode.SHIFT){
				shift = true;
			}else if(k.getCode()==KeyCode.BACK_SPACE){
				this.executeCommand(new CommandRemove(buffer));
			}else{
				String text = k.getText();
				if(shift) text = text.toUpperCase();
				this.executeCommand(new CommandAddText(buffer, text));
			}
		}else if(k.getEventType() == KeyEvent.KEY_RELEASED){
			if(k.getCode()==KeyCode.SHIFT){
				shift = false;
			}
		}
	}
}
