package elementum.controllers;

import elementum.models.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameController {
    private Stage stage;
    private Stage dialogStage;
    private ArrayList<Card> cards;

    public GameController(Stage stage, ArrayList<Card> cards) throws Exception {
        this.stage = stage;
        this.cards = cards;

        Parent root = FXMLLoader.load(getClass().getResource("../views/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Help Button
        Button btnHelp = (Button)scene.lookup("#btnHelp");
        btnHelp.setOnAction(this::btnHelpAction);

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);
    }

    private void btnHelpAction(ActionEvent event) {
        //
    }

    private void btnBackAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../views/dialog.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../assets/main.css").toExternalForm());

            dialogStage = new Stage();
            dialogStage.setTitle("Spiel beenden");
            dialogStage.setResizable(false);
            dialogStage.setScene(scene);
            dialogStage.show();

            Button btnYes = (Button)scene.lookup("#btnYes");
            btnYes.setOnAction(e -> {
                try {
                    new MainController(stage);
                }
                catch (Exception ex) {
                    // TODO: Catch exception
                }

                dialogStage.close();
            });

            Button btnNo = (Button)scene.lookup("#btnNo");
            btnNo.setOnAction(e -> {
                dialogStage.close();
            });
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }
}
