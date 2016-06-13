package gon.controllers;

import gon.controllers.utils.CursorLoader;
import gon.controllers.utils.Locale;
import gon.controllers.utils.Logging;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * @author Alexander Elbracht
 *
 * This class is the controller for the dialog view.
 */
public class DialogController {
    private Stage stage;
    private Logging logging;
    private Locale locale;
    private Stage dialogStage;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public DialogController(Stage stage, Logging logging, Locale locale, Thread thread) throws Exception {
        this.stage = stage;
        this.logging = logging;
        this.locale = locale;

        Parent root = FXMLLoader.load(getClass().getResource("/gon/views/dialog.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/gon/assets/main.css").toExternalForm());

        dialogStage = new Stage();
        dialogStage.setTitle("Spiel beenden");
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        dialogStage.show();

        // Yes button
        Button btnYes = (Button)scene.lookup("#btnYes");
        btnYes.setText(locale.getString("ui", "dialog-button-yes"));
        btnYes.setOnAction(event -> {
            try {
                thread.stop();
                new MainController(stage, logging, locale);
                dialogStage.close();
            }
            catch (Exception ex) {
                logging.log(Level.SEVERE, "Exception", ex);
            }
        });

        // No button
        Button btnNo = (Button)scene.lookup("#btnNo");
        btnNo.setText(locale.getString("ui", "dialog-button-no"));
        btnNo.setOnAction(event -> {
            dialogStage.close();
        });

        // Text label
        Label lblText = (Label)scene.lookup("#lblText");
        lblText.setText(locale.getString("ui", "dialog-label-text"));
    }
}
