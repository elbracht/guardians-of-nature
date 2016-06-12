package elementum.controllers;

import elementum.controllers.game.Referee;
import elementum.controllers.utils.CursorLoader;
import elementum.controllers.utils.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * This class is the controller for the game over view.
 */
public class GameoverController {
    private Stage stage;
    private Logging logging;

    /**
     * Constructor
     * @param stage Stage
     * @param referee Referee
     * @throws Exception
     */
    public GameoverController(Stage stage, Logging logging, Referee referee) throws Exception {
        this.stage = stage;
        this.logging = logging;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/gameover.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Winner label
        Label lblGameover = (Label)scene.lookup("#lblGameover");
        if (referee.isComputerGameOver()) {
            lblGameover.setText("Sie haben gewonnen.");
        }
        else if (referee.isPlayerGameOver()) {
            lblGameover.setText("Der Computer hat gewonnen.");
        }

        // Back button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnBackAction(ActionEvent event) {
        try {
            new MainController(stage, logging);
        }
        catch (Exception ex) {
            logging.log(Level.SEVERE, "Exception", ex);
        }
    }
}
