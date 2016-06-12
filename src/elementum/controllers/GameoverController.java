package elementum.controllers;

import elementum.controllers.game.Referee;
import elementum.controllers.utils.CursorLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameoverController {
    private Stage stage;

    public GameoverController(Stage stage, Referee referee) throws Exception {
        this.stage = stage;

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

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);
    }

    private void btnBackAction(ActionEvent event) {
        try {
            new MainController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }
}
