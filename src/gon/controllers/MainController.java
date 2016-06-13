package gon.controllers;

import gon.controllers.utils.CursorLoader;
import gon.controllers.utils.Locale;
import gon.controllers.utils.Logging;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * This class is the controller for the main view.
 */
public class MainController {
    private Stage stage;
    private Logging logging;
    private Locale locale;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public MainController(Stage stage, Logging logging, Locale locale) throws Exception {
        this.stage = stage;
        this.logging = logging;
        this.locale = locale;

        Parent root = FXMLLoader.load(getClass().getResource("/gon/views/main.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/gon/assets/main.css").toExternalForm());

        stage.setTitle("Guardians of Nature");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        // Start button
        Button btnStart = (Button)scene.lookup("#btnStart");
        btnStart.setText(locale.getString("ui", "main-button-start"));
        btnStart.setOnAction(this::btnStartAction);

        // Exit button
        Button btnExit = (Button)scene.lookup("#btnExit");
        btnExit.setText(locale.getString("ui", "main-button-exit"));
        btnExit.setOnAction(this::btnExitAction);

        // Language german
        ImageView btnGerman = (ImageView)scene.lookup("#language-de");

        if (locale.getLanguage() == "de_DE") {
            ObservableList styleClass = btnGerman.getStyleClass();
            styleClass.add("card-selected");
        }

        btnGerman.setOnMouseEntered(event -> {
            if (locale.getLanguage() != "de_DE") {
                btnGerman.setCursor(CursorLoader.getSelect());
            }
        });

        btnGerman.setOnMouseClicked(event -> {
            try {
                new MainController(stage, logging, new Locale("de_DE"));
            }
            catch (Exception ex) {
                logging.log(Level.SEVERE, "Exception", ex);
            }
        });

        // Language english
        ImageView btnEnglish = (ImageView)scene.lookup("#language-en");

        if (locale.getLanguage() == "en_US") {
            ObservableList styleClass = btnEnglish.getStyleClass();
            styleClass.add("card-selected");
        }

        btnEnglish.setOnMouseEntered(event -> {
            if (locale.getLanguage() != "en_US") {
                btnEnglish.setCursor(CursorLoader.getSelect());
            }
        });

        btnEnglish.setOnMouseClicked(event -> {
            try {
                new MainController(stage, logging, new Locale("en_US"));
            }
            catch (Exception ex) {
                logging.log(Level.SEVERE, "Exception", ex);
            }
        });


    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnStartAction(ActionEvent event) {
        try {
            new CardpollController(stage, logging, locale);
        }
        catch (Exception ex) {
            logging.log(Level.SEVERE, "Exception", ex);
        }
    }

    /**
     * Event for click on button
     * @param event ActionEvent
     */
    private void btnExitAction(ActionEvent event) {
        System.exit(0);
    }
}
