package elementum;

import elementum.controllers.MainController;
import elementum.controllers.utils.Locale;
import elementum.controllers.utils.Logging;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * @author Alexander Elbracht
 *
 * Main class to start the application.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Logging logging = new Logging();

        try {
            new MainController(primaryStage, logging, new Locale("de_DE"));
        }
        catch (Exception ex) {
            logging.log(Level.SEVERE, "Exception", ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
