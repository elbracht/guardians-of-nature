package elementum.controllers;

import elementum.controllers.game.Computer;
import elementum.controllers.game.Player;
import elementum.controllers.utils.CursorLoader;
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

public class CardpollController {
    private Stage stage;
    private Cards cards;
    private Player player;
    private Computer computer;

    public CardpollController(Stage stage) throws Exception {
        this.stage = stage;

        cards = new Cards();
        player = new Player();
        computer = new Computer(cards);

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/cardpoll.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Continue Button
        Button btnContinue = (Button)scene.lookup("#btnContinue");
        btnContinue.setOnAction(this::btnContinueAction);

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);

        // Help label
        Label lblHelp = (Label)scene.lookup("#lblHelp");
        lblHelp.setText(String.format("Wähle %d Karten", player.CARD_LIMIT - player.getCardsCount()));

        // Cards
        for (Node card : scene.lookup("*").lookupAll(".card")) {
            // Add images
            int cardId = Integer.parseInt(card.getId());
            ImageView cardImageView = (ImageView)card;
            BufferedImage cardImage = cards.getCards().get(cardId).getImage();
            cardImageView.setImage(SwingFXUtils.toFXImage(cardImage, null));

            // Cursor
            card.setCursor(CursorLoader.getSelect());

            // Add event
            card.setOnMouseClicked(t -> {
                ImageView imageView = (ImageView)t.getSource();
                ObservableList styleClass = imageView.getStyleClass();
                int imageId = Integer.parseInt(imageView.getId());

                if (styleClass.contains("card-selected")) {
                    // Remove Card
                    player.removeCard(cards.getCards().get(imageId));
                    styleClass.remove("card-selected");
                    btnContinue.setDisable(true);
                }
                else {
                    // Add Card
                    if (player.getCardsCount() < player.CARD_LIMIT - 1) {
                        player.addCard(cards.getCards().get(imageId));
                        styleClass.add("card-selected");
                    }
                    else if (player.getCardsCount() == player.CARD_LIMIT - 1) {
                        player.addCard(cards.getCards().get(imageId));
                        styleClass.add("card-selected");
                        btnContinue.setDisable(false);
                    }
                    else {
                        btnContinue.setDisable(false);
                    }
                }

                if (player.getCardsCount() == player.CARD_LIMIT) {
                    lblHelp.setText("");
                }
                else if (player.getCardsCount() == player.CARD_LIMIT - 1) {
                    lblHelp.setText("Wähle 1 Karte");
                }
                else if (player.getCardsCount() < player.CARD_LIMIT) {
                    lblHelp.setText(String.format("Wähle %d Karten", player.CARD_LIMIT - player.getCardsCount()));
                }
            });
        }
    }

    private void btnContinueAction(ActionEvent event) {
        try {
            new GameController(stage, computer, player);
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
