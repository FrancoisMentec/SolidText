package solidText;

import java.util.Observable;
import java.util.Observer;

import command.Command;
import command.CommandAddText;
import command.CommandCopy;
import command.CommandExecuteMacro;
import command.CommandManager;
import command.CommandMove;
import command.CommandMoveSelect;
import command.CommandPaste;
import command.CommandRemove;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

public class TextEditor implements Observer {
	private EditorEngine buffer;
	private CommandManager cmdM;
	private WebView view;

	public TextEditor() {
		cmdM = new CommandManager();
		buffer = new EditorEngine();
		buffer.addObserver(this);

		view = new WebView();
		view.setDisable(true);

		updateView();
	}

	public void updateView() {
		view.getEngine().loadContent(buffer.toHtml());
	}

	public String getText() {
		return buffer.toString();
	}

	public void addText(String text) {
		buffer.replaceSelection(text);
		updateView();
	}

	public Node getView() {
		return view;
	}

	/*
	 * public void executeCommand(Command command){
	 * this.commandList.add(command); command.execute();
	 * 
	 * updateView(); }
	 */

	public void update(Observable o, Object arg) {
		this.updateView();
	}

	public void handleKeyEvent(KeyEvent k) {
		// System.out.println(k.getCode());

		if (k.getEventType() == KeyEvent.KEY_PRESSED) {
			if (k.getCode() == KeyCode.LEFT) {
				if (k.isShiftDown())
					this.cmdM.executeCommand(new CommandMoveSelect(buffer, Command.LEFT));
				else
					this.cmdM.executeCommand(new CommandMove(buffer, Command.LEFT));
			} else if (k.getCode() == KeyCode.RIGHT) {
				if (k.isShiftDown())
					this.cmdM.executeCommand(new CommandMoveSelect(buffer, Command.RIGHT));
				else
					this.cmdM.executeCommand(new CommandMove(buffer, Command.RIGHT));
			} else if (k.getCode() == KeyCode.UP) {
				if (k.isShiftDown())
					this.cmdM.executeCommand(new CommandMoveSelect(buffer, Command.UP));
				else
					this.cmdM.executeCommand(new CommandMove(buffer, Command.UP));
			} else if (k.getCode() == KeyCode.DOWN) {
				if (k.isShiftDown())
					this.cmdM.executeCommand(new CommandMoveSelect(buffer, Command.DOWN));
				else
					this.cmdM.executeCommand(new CommandMove(buffer, Command.DOWN));
			} else if (k.getCode() == KeyCode.END) {
				this.cmdM.executeCommand(new CommandMove(buffer, Command.END));
			} else if (k.getCode() == KeyCode.BACK_SPACE) {
				this.cmdM.executeCommand(new CommandRemove(buffer, CommandRemove.LEFT));
			} else if (k.getCode() == KeyCode.DELETE) {
				this.cmdM.executeCommand(new CommandRemove(buffer, CommandRemove.RIGHT));
			} else if (k.isControlDown() && k.getCode() == KeyCode.C) {
				this.cmdM.executeCommand(new CommandCopy(buffer));
			} else if (k.isControlDown() && k.getCode() == KeyCode.X) {
				this.cmdM.executeCommand(new CommandCopy(buffer));
				this.cmdM.executeCommand(new CommandRemove(buffer, CommandRemove.SELECT));
			} else if (k.isControlDown() && k.getCode() == KeyCode.V) {
				this.cmdM.executeCommand(new CommandPaste(buffer));
			} else if (k.isControlDown() && k.getCode() == KeyCode.Z) {
				this.cmdM.undo();
			} else if (k.isControlDown() && k.getCode() == KeyCode.Y) {
				this.cmdM.redo();
			} else if (k.isControlDown() && k.getCode() == KeyCode.A) {
				this.cmdM.executeCommand(new CommandMoveSelect(buffer, Command.ALL));
			} else if (k.isAltDown() && k.getText().length() == 1 && "0123456789".contains(k.getText())) {
				this.cmdM.toggleCurrentMacro(Integer.parseInt(k.getText()));
			} else if (k.isControlDown() && k.getText().length() == 1 && "0123456789".contains(k.getText())) {
				this.cmdM.executeCommand(new CommandExecuteMacro(buffer, cmdM, Integer.parseInt(k.getText())));
			} else {
				String text = k.getText();
				if (text.length() > 0) {
					if (k.isShiftDown())
						text = TextModifier.shift(text);
					else if (k.isAltDown())
						text = TextModifier.alt(text);
					this.cmdM.executeCommand(new CommandAddText(buffer, text));
				}
			}
		}

	}

}
