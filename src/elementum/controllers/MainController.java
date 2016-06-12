package elementum.controllers;

import elementum.controllers.utils.CursorLoader;
import elementum.controllers.utils.Locale;
import elementum.controllers.utils.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * This class is the controller for the main view.
 */
public class MainController {
    private Stage stage;
    private Logging logging;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public MainController(Stage stage, Logging logging) throws Exception {
        this.stage = stage;
        this.logging = logging;

        Locale locale = new Locale();

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/main.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setTitle("Guardians of Nature");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        // Start button
        Button btnStart = (Button)scene.lookup("#btnStart");
        btnStart.setText(locale.getString("ui", "main-button-start"));
        btnStart.setOnAction(this::btnStartAction);

        // Exit button
        Button btnExit = (Button)scene.lookup("#btnExit");
        btnExit.setText(locale.getString("ui", "main-button-exit"));
        btnExit.setOnAction(this::btnExitAction);
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnStartAction(ActionEvent event) {
        try {
            new CardpollController(stage, logging);
        }
        catch (Exception ex) {
            logging.log(Level.SEVERE, "Exception", ex);
        }
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnExitAction(ActionEvent event) {
        System.exit(0);
    }
}
