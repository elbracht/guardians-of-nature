package elementum.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class cardpollController {
    private int cards;
    private int cardsLimit = 3;

    private Stage stage;

    public cardpollController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("../views/cardpoll.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Continue Button
        Button btnContinue = (Button)scene.lookup("#btnContinue");
        btnContinue.setOnAction(this::btnContinueAction);

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);

        // Help label
        Label lblHelp = (Label)scene.lookup("#lblHelp");
        lblHelp.setText(String.format("Wähle %d Karten", cardsLimit - cards));

        // Cards
        for (Node card : scene.lookup("*").lookupAll(".card")) {
            card.setOnMouseClicked(t -> {
                ImageView imageView = (ImageView)t.getSource();
                ObservableList styleClass = imageView.getStyleClass();

                if (styleClass.contains("card-selected")) {
                    // Remove card
                    cards--;
                    styleClass.remove("card-selected");
                    btnContinue.setDisable(true);
                }
                else {
                    // Add card
                    if (cards < cardsLimit - 1) {
                        cards++;
                        styleClass.add("card-selected");
                    }
                    else if (cards == cardsLimit - 1) {
                        cards++;
                        styleClass.add("card-selected");
                        btnContinue.setDisable(false);
                    }
                    else {
                        btnContinue.setDisable(false);
                    }
                }

                if (cards == cardsLimit) {
                    lblHelp.setText("");
                }
                else if (cards == cardsLimit - 1) {
                    lblHelp.setText("Wähle 1 Karte");
                }
                else if (cards < cardsLimit) {
                    lblHelp.setText(String.format("Wähle %d Karten", cardsLimit - cards));
                }

            });
        }
    }

    private void btnContinueAction(ActionEvent event) {
        try {
            new gameController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }

    private void btnBackAction(ActionEvent event) {
        try {
            new mainController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }
}
