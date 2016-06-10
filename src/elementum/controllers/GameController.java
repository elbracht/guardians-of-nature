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

        // Add player and computer cards
        for (int i = 0; i < 3; i++) {
            addPlayerCard(i);
            addComputerCard(i);
        }

        // Referee
        referee = new Referee();
        referee.addObserver(this);
        referee.setPlayersTurn(false);//Math.random() < 0.5);
    }

    private void addComputerCard(int id) {
        Card card = computer.getCard(id);
        computer.updateCard(id + 3, card);

        BufferedImage image = card.getImage();
        ImageView imageView = getImageView(computer.getId(card));
        imageView.setImage(SwingFXUtils.toFXImage(image, null));

        // Cursor
        imageView.setOnMouseEntered(t -> {
            if (cardActive != null) {
                imageView.setCursor(CursorLoader.getAttack());
            }
            else {
                imageView.setCursor(CursorLoader.getDefault());
            }
        });

        // Events for click on computer card
        imageView.setOnMouseClicked(t -> {
            if (cardActive != null) {
                // Attack computer
                computer.attack(id, cardActive.getAttack());

                // Add new image to ImageView
                if (computer.getCard(id).getHealth() <= 0) {
                    addCard(computer.getCard(id), imageView, true);
                }
                else {
                    addCard(computer.getCard(id), imageView, false);
                }

                // Change turn
                referee.setPlayersTurn(false);
            }
        });
    }

    private void addPlayerCard(int id) {
        Card card = player.getCard(id);
        player.updateCard(id, card);

        BufferedImage image = player.getCard(id).getImage();
        ImageView imageView = getImageView(player.getId(card));
        imageView.setImage(SwingFXUtils.toFXImage(image, null));

        imageView.setOnMouseEntered(t -> {
            if (referee.isPlayersTurn()) {
                imageView.setCursor(CursorLoader.getSelect());
            }
            else {
                imageView.setCursor(CursorLoader.getDefault());
            }
        });

        // Event for click on player card
        imageView.setOnMouseClicked(t -> {
            if (referee.isPlayersTurn()) {
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

    /**
     * Manage cards in ImageViews
     */
    private void addCard(Card card, ImageView imageView, Boolean grayedOut) {
        BufferedImage image = card.getImage();
        imageView.setImage(SwingFXUtils.toFXImage(image, null));

        if (grayedOut) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);

            imageView.setEffect(colorAdjust);
            imageView.setDisable(true);
        }
    }

    private void unselectAllCards() {
        cardActive = null;

        for (Node card : stage.getScene().lookup("*").lookupAll(".card")) {
            card.getStyleClass().remove("card-selected");
        }
    }

    private ImageView getImageView(int id) {
        return (ImageView)stage.getScene().lookup(String.format("#%s", id));
    }

    /**
     * Observer
     */
    public void update(Observable obs, Object obj) {
        Label lblInfo = (Label)stage.getScene().lookup("#lblInfo");

        if (referee.isPlayersTurn()) {
            lblInfo.setText("Spieler ist am Zug.");
        }
        else {
            unselectAllCards();
            lblInfo.setText("Computer ist am Zug.");

            // Attack player
            Card card = computer.makeTurn(player);

            // Add new image to ImageView
            if (card.getHealth() <= 0) {
                addCard(card, getImageView(player.getId(card)), true);
            }
            else {
                addCard(card, getImageView(player.getId(card)), false);
            }

            // Change turn
            referee.setPlayersTurn(true);
        }
    }

    /**
     * Events
     */
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
}
