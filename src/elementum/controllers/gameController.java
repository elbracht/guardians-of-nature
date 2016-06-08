package elementum.controllers;

import elementum.models.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameController {
    private Stage stage;
    private ArrayList<Card> cards;

    public GameController(Stage stage, ArrayList<Card> cards) throws Exception {
        this.stage = stage;
        this.cards = cards;

        Parent root = FXMLLoader.load(getClass().getResource("../views/game.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../assets/main.css").toExternalForm());

        stage.setScene(scene);
    }
}
