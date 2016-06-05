package elementum.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class cardpollController {
    private int cards = 0;
    private int cardsLimit = 2;

    private Stage stage;

    public cardpollController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("../views/cardpoll.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../views/cardpoll.css").toExternalForm());
        stage.setScene(scene);

        // Continue Button
        Button btnContinue = (Button)scene.lookup("#btnContinue");
        btnContinue.setOnAction(this::btnContinueAction);

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);

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
                    if (cards < cardsLimit) {
                        cards++;
                        styleClass.add("card-selected");
                    }
                    else if (cards == cardsLimit) {
                        cards++;
                        styleClass.add("card-selected");
                        btnContinue.setDisable(false);
                    }
                    else {
                        btnContinue.setDisable(false);
                    }
                }
            });
        }
    }

    private void btnContinueAction(ActionEvent event) {

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
