package elementum.controllers;

import elementum.controllers.game.Player;
import elementum.controllers.game.Referee;
import elementum.controllers.utils.CursorLoader;
import elementum.controllers.game.Computer;
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
        referee = new Referee(player, computer);
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
                            // Add new image to ImageView
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

        if (!referee.isGameOver()) {
            if (referee.isPlayersTurn()) {
                lblInfo.setText("Spieler ist am Zug.");
            } else {
                unselectAllCards();
                lblInfo.setText("Computer ist am Zug.");

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
                                // Add new image to ImageView
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
            unselectAllCards();
            lblInfo.setText("Spiel beendet.");
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
