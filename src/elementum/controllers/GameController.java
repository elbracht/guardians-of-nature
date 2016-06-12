package elementum.controllers;

import elementum.controllers.game.Computer;
import elementum.controllers.game.Player;
import elementum.controllers.game.Referee;
import elementum.controllers.utils.CursorLoader;
import elementum.models.Card;
import javafx.application.Platform;
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

/**
 * @author Alexander Elbracht
 *
 * This class is the controller for the game view.
 * This class observe the Referee to get a update if the turn change
 */
public class GameController implements Observer {
    private Stage stage;
    private Referee referee;
    private Computer computer;
    private Player player;
    private Card cardActive = null;

    /**
     * Constructor
     * @param stage Stage
     * @param computer Computer
     * @param player Player
     * @throws Exception
     */
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
        referee = new Referee(player, computer);
        referee.addObserver(this);
        referee.setPlayersTurn(false);//Math.random() < 0.5);
    }

    /**
     * Add computer card to the view and add click events
     * @param id Id for the card
     */
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
                Thread animationThread = new Thread(() -> {
                    try {
                        // Attack computer
                        computer.attack(id, cardActive.getAttack());

                        Platform.runLater(() -> {
                            // Select computer card
                            ImageView computerImageView = getImageView(id + 3);
                            ObservableList styleClass = computerImageView.getStyleClass();
                            styleClass.add("card-selected");
                        });

                        Thread.sleep(300);

                        Platform.runLater(() -> {
                            // Add new image to image view
                            if (computer.getCard(id).getHealth() <= 0) {
                                addCard(computer.getCard(id), imageView, true);
                            }
                            else {
                                addCard(computer.getCard(id), imageView, false);
                            }
                        });

                        Thread.sleep(500);

                        Platform.runLater(() -> {
                            // Change turn
                            referee.setPlayersTurn(false);

                            // Unselect all cards
                            unselectAllCards();
                        });
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        // TODO: Catch exception
                    }
                });

                animationThread.start();
            }
        });
    }

    /**
     * Add player card to the view and add click events
     * @param id Id for the card
     */
    private void addPlayerCard(int id) {
        Card card = player.getCard(id);
        player.updateCard(id, card);

        BufferedImage image = player.getCard(id).getImage();
        ImageView imageView = getImageView(player.getId(card));
        imageView.setImage(SwingFXUtils.toFXImage(image, null));

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

    /* Manage cards in image views */

    /**
     * Add a card to image view
     * @param card Card to add
     * @param imageView ImageView
     * @param grayedOut Is the image view grayed out
     */
    private void addCard(Card card, ImageView imageView, boolean grayedOut) {
        BufferedImage image = card.getImage();
        imageView.setImage(SwingFXUtils.toFXImage(image, null));

        if (grayedOut) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(-0.5);

            imageView.setEffect(colorAdjust);
            imageView.setDisable(true);
        }
    }

    /**
     * Unselect all cards
     */
    private void unselectAllCards() {
        cardActive = null;

        for (Node card : stage.getScene().lookup("*").lookupAll(".card")) {
            card.getStyleClass().remove("card-selected");
        }
    }

    /**
     * Get the image view with a card
     * @param id Id for the card
     * @return Image view
     */
    private ImageView getImageView(int id) {
        return (ImageView)stage.getScene().lookup(String.format("#%s", id));
    }

    /* Cursor */

    /**
     * Update cursor
     */
    private void updateCursor() {
        ImageView imageView0 = getImageView(0);
        ImageView imageView1 = getImageView(1);
        ImageView imageView2 = getImageView(2);
        ImageView imageView3 = getImageView(3);
        ImageView imageView4 = getImageView(4);
        ImageView imageView5 = getImageView(5);

        if (referee.isPlayersTurn()) {
            imageView0.setCursor(CursorLoader.getSelect());
            imageView1.setCursor(CursorLoader.getSelect());
            imageView2.setCursor(CursorLoader.getSelect());
        }
        else {
            imageView0.setCursor(CursorLoader.getDefault());
            imageView1.setCursor(CursorLoader.getDefault());
            imageView2.setCursor(CursorLoader.getDefault());
            imageView3.setCursor(CursorLoader.getDefault());
            imageView4.setCursor(CursorLoader.getDefault());
            imageView5.setCursor(CursorLoader.getDefault());
        }
    }

    /* Observer */

    /**
     * This update is called if the player or computer turn changed
     * @param obs Observable
     * @param obj Object
     */
    public void update(Observable obs, Object obj) {
        Label lblInfo = (Label)stage.getScene().lookup("#lblInfo");

        if (!referee.isGameOver()) {
            if (referee.isPlayersTurn()) {
                lblInfo.setText("Spieler ist am Zug.");
                updateCursor();
            } else {
                unselectAllCards();
                lblInfo.setText("Computer ist am Zug.");
                updateCursor();

                Thread animationThread = new Thread(() -> {
                    try {
                        Thread.sleep(1200);

                        // Attack player
                        Card[] cards = computer.makeTurn(player);
                        Card attackCard = cards[0];
                        Card defenseCard = cards[1];

                        if (cards != null) {
                            Platform.runLater(() -> {
                                // Select computer card
                                ImageView imageView = getImageView(computer.getId(attackCard));
                                ObservableList styleClass = imageView.getStyleClass();
                                styleClass.add("card-selected");
                            });

                            Thread.sleep(500);

                            Platform.runLater(() -> {
                                // Select player card
                                ImageView imageView = getImageView(player.getId(defenseCard));
                                ObservableList styleClass = imageView.getStyleClass();
                                styleClass.add("card-selected");
                            });

                            Thread.sleep(300);

                            Platform.runLater(() -> {
                                // Add new image to image view
                                if (defenseCard.getHealth() <= 0) {
                                    addCard(defenseCard, getImageView(player.getId(defenseCard)), true);
                                } else {
                                    addCard(defenseCard, getImageView(player.getId(defenseCard)), false);
                                }
                            });

                            Thread.sleep(500);

                            Platform.runLater(() -> {
                                // Change turn
                                referee.setPlayersTurn(true);

                                // Unselect all cards
                                unselectAllCards();
                            });
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        // TODO: Catch exception
                    }
                });

                animationThread.start();
            }
        }
        else {
            // Game over
            try {
                new GameoverController(stage, referee);
            }
            catch (Exception ex) {
                // TODO: Catch exception
            }
        }
    }

    /* Events */

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnHelpAction(ActionEvent event) {
        //
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnBackAction(ActionEvent event) {
        try {
            new DialogController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }
}
