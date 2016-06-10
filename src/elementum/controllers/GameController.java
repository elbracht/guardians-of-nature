package elementum.controllers;

import elementum.controllers.game.Player;
import elementum.controllers.game.Referee;
import elementum.controllers.utils.CursorLoader;
import elementum.controllers.game.Computer;
import elementum.models.Card;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class GameController implements Observer {
    private Stage stage;
    private Referee referee;
    private Computer computer;
    private Player player;
    private Card cardActive = null;

    public GameController(Stage stage, Computer computer, Player player) throws Exception {
        this.stage = stage;
        this.computer = computer;
        this.player = player;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/game.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Help Button
        Button btnHelp = (Button)scene.lookup("#btnHelp");
        btnHelp.setOnAction(this::btnHelpAction);

        // Back Button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);

        // Referee
        referee = new Referee();
        referee.addObserver(this);
        referee.setPlayersTurn(Math.random() < 0.5);

        // Cards
        for (Node card : scene.lookup("*").lookupAll(".card")) {
            int cardId = Integer.parseInt(card.getId());
            if (cardId < 3) {
                initPlayerCard(card, cardId);
            }
            else {
                initComputerCard(card, cardId - 3);
            }
        }
    }

    private void initComputerCard(Node card, int cardId) {
        ImageView cardImageView = (ImageView)card;
        BufferedImage cardImage = computer.getCard(cardId).getImage();
        cardImageView.setImage(SwingFXUtils.toFXImage(cardImage, null));

        // Cursor
        card.setOnMouseEntered(t -> {
            if (cardActive != null) {
                card.setCursor(CursorLoader.getAttack());
            }
            else {
                card.setCursor(CursorLoader.getDefault());
            }
        });

        // Events for click on computer card
        card.setOnMouseClicked(t -> {
            if (cardActive != null) {
                ImageView imageView = (ImageView)t.getSource();
                int id = Integer.parseInt(imageView.getId()) - 3;

                Card computerCard = computer.getCard(id);
                computerCard.setHealth(computerCard.getHealth() - cardActive.getAttack());
                computerCard.paint();

                BufferedImage computerImage = computerCard.getImage();
                imageView.setImage(SwingFXUtils.toFXImage(computerImage, null));

                if (computerCard.getHealth() <= 0) {
                    ColorAdjust colorAdjust = new ColorAdjust();
                    colorAdjust.setBrightness(-0.5);

                    imageView.setEffect(colorAdjust);
                    imageView.setDisable(true);
                }

                referee.setPlayersTurn(false);
            }
        });
    }

    private void initPlayerCard(Node card, int cardId) {
        ImageView cardImageView = (ImageView)card;
        BufferedImage cardImage = player.getCard(cardId).getImage();
        cardImageView.setImage(SwingFXUtils.toFXImage(cardImage, null));

        card.setOnMouseEntered(t -> {
            if (referee.isPlayersTurn()) {
                card.setCursor(CursorLoader.getSelect());
            }
            else {
                card.setCursor(CursorLoader.getDefault());
            }
        });

        // Event for click on player card
        card.setOnMouseClicked(t -> {
            if (referee.isPlayersTurn()) {
                ImageView imageView = (ImageView) t.getSource();
                ObservableList styleClass = imageView.getStyleClass();

                if (styleClass.contains("card-selected")) {
                    if (cardActive != null) {
                        cardActive = null;
                        styleClass.remove("card-selected");
                    }
                } else {
                    unselectAllCards();
                    cardActive = player.getCard(Integer.parseInt(imageView.getId()));
                    styleClass.add("card-selected");
                }
            }
        });
    }

    private void unselectAllCards() {
        cardActive = null;

        for (Node card : stage.getScene().lookup("*").lookupAll(".card")) {
            card.getStyleClass().remove("card-selected");
        }
    }

    private void btnHelpAction(ActionEvent event) {
        //
    }

    private void btnBackAction(ActionEvent event) {
        try {
            new DialogController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }

    public void update(Observable obs, Object obj) {
        Label lblInfo = (Label)stage.getScene().lookup("#lblInfo");

        if (referee.isPlayersTurn()) {
            lblInfo.setText("Spieler ist am Zug.");
        }
        else {
            unselectAllCards();
            lblInfo.setText("Computer ist am Zug.");
        }
    }
}
