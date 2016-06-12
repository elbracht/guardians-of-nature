package elementum;

import elementum.controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Alexander Elbracht
 *
 * Main class to start the application.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        new MainController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
