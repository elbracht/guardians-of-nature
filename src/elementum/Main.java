package elementum;

import elementum.controllers.mainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        new mainController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
