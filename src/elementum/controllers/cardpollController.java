package elementum.controllers;

import elementum.models.Card;
import elementum.models.Cards;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardpollController {
    private Stage stage;
    private Cards cards;
    private ArrayList<Card> cardsSelected;
    private int cardsLimit = 3;

    public CardpollController(Stage stage) throws Exception {
        this.stage = stage;
        this.cards = new Cards();
        this.cardsSelected = new ArrayList<>();

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
        lblHelp.setText(String.format("Wähle %d Karten", cardsLimit - cardsSelected.size()));

        // Cards
        for (Node card : scene.lookup("*").lookupAll(".card")) {
            // Add images
            int cardId = Integer.parseInt(card.getId());
            ImageView cardImageView = (ImageView)card;
            BufferedImage cardImage = cards.getCards().get(cardId).getImage();
            cardImageView.setImage(SwingFXUtils.toFXImage(cardImage, null));

            // Add event
            card.setOnMouseClicked(t -> {
                ImageView imageView = (ImageView)t.getSource();
                ObservableList styleClass = imageView.getStyleClass();
                int imageId = Integer.parseInt(imageView.getId());

                if (styleClass.contains("card-selected")) {
                    // Remove Card
                    cardsSelected.remove(cards.getCards().get(imageId));
                    styleClass.remove("card-selected");
                    btnContinue.setDisable(true);
                }
                else {
                    // Add Card
                    if (cardsSelected.size() < cardsLimit - 1) {
                        cardsSelected.add(cards.getCards().get(imageId));
                        styleClass.add("card-selected");
                    }
                    else if (cardsSelected.size() == cardsLimit - 1) {
                        cardsSelected.add(cards.getCards().get(imageId));
                        styleClass.add("card-selected");
                        btnContinue.setDisable(false);
                    }
                    else {
                        btnContinue.setDisable(false);
                    }
                }

                if (cardsSelected.size() == cardsLimit) {
                    lblHelp.setText("");
                }
                else if (cardsSelected.size() == cardsLimit - 1) {
                    lblHelp.setText("Wähle 1 Karte");
                }
                else if (cardsSelected.size() < cardsLimit) {
                    lblHelp.setText(String.format("Wähle %d Karten", cardsLimit - cardsSelected.size()));
                }
            });
        }
    }

    private void btnContinueAction(ActionEvent event) {
        try {
            new GameController(stage, cardsSelected);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
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
