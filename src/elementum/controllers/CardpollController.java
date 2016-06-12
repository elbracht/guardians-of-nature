package elementum.controllers;

import elementum.controllers.game.Computer;
import elementum.controllers.game.Player;
import elementum.controllers.utils.CursorLoader;
import elementum.controllers.utils.Logging;
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

/**
 * This class is the controller for the card poll view.
 */
public class CardpollController {
    private Stage stage;
    private Logging logging;
    private Cards cards;
    private Player player;
    private Computer computer;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public CardpollController(Stage stage, Logging logging) throws Exception {
        this.stage = stage;
        this.logging = logging;

        cards = new Cards();
        player = new Player();
        computer = new Computer();

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/cardpoll.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setScene(scene);

        // Continue button
        Button btnContinue = (Button)scene.lookup("#btnContinue");
        btnContinue.setOnAction(this::btnContinueAction);

        // Back button
        Button btnBack = (Button)scene.lookup("#btnBack");
        btnBack.setOnAction(this::btnBackAction);

        // Help label
        Label lblHelp = (Label)scene.lookup("#lblHelp");
        lblHelp.setText(String.format("Wähle %d Karten", player.CARD_LIMIT - player.getCardsCount()));

        // Cards
        for (Node imageView : scene.lookup("*").lookupAll(".card")) {
            // Add images
            int id = Integer.parseInt(imageView.getId());
            BufferedImage cardImage = cards.getCards().get(id).getImage();
            ((ImageView)imageView).setImage(SwingFXUtils.toFXImage(cardImage, null));

            // Cursor
            imageView.setCursor(CursorLoader.getSelect());

            // Add event
            imageView.setOnMouseClicked(t -> {
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
                        player.addCard(0, cards.getCards().get(imageId));
                        styleClass.add("card-selected");
                    }
                    else if (player.getCardsCount() == player.CARD_LIMIT - 1) {
                        player.addCard(0, cards.getCards().get(imageId));
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

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnContinueAction(ActionEvent event) {
        try {
            new GameController(stage, logging, computer, player);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            // TODO: Catch exception
        }
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
            System.out.println(ex.getMessage());
            // TODO: Catch exception
        }
    }
}
