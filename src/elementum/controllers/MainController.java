package elementum.controllers;

import elementum.controllers.utils.CursorLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class is the controller for the main view.
 */
public class MainController {
    private Stage stage;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public MainController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/main.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setTitle("Elementum");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        // Start button
        Button btnStart = (Button)scene.lookup("#btnStart");
        btnStart.setOnAction(this::btnStartAction);

        // Exit button
        Button btnExit = (Button)scene.lookup("#btnExit");
        btnExit.setOnAction(this::btnExitAction);
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnStartAction(ActionEvent event) {
        try {
            new CardpollController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
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
