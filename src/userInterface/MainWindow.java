package userInterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import solidText.TextEditor;

public class MainWindow extends Stage{
	
	private TextEditor textEditor;
	
	public MainWindow(){
		this.setTitle("SolidText");
		
		textEditor = new TextEditor();
		
        StackPane root = new StackPane();
        root.getChildren().add(textEditor.getView());
        this.setScene(new Scene(root, 300, 250));
        
        this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
				public void handle(KeyEvent k) {
					textEditor.handleKeyEvent(k);
				}
        	});
	}
}
