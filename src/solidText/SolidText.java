package solidText;

import javafx.application.Application;
import javafx.stage.Stage;
import userInterface.MainWindow;

public class SolidText extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage primaryStage) {
        primaryStage = new MainWindow();
        primaryStage.show();
    }
}
