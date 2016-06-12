package elementum.controllers;

import elementum.controllers.utils.CursorLoader;
import elementum.controllers.utils.Logging;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.logging.Level;

/**
 * This class is the controller for the dialog view.
 */
public class DialogController {
    private Stage stage;
    private Logging logging;
    private Stage dialogStage;

    /**
     * Constructor
     * @param stage Stage
     * @throws Exception
     */
    public DialogController(Stage stage, Logging logging, Thread thread) throws Exception {
        this.stage = stage;
        this.logging = logging;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/dialog.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        dialogStage = new Stage();
        dialogStage.setTitle("Spiel beenden");
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        dialogStage.show();

        // Yes button
        Button buttonYes = (Button)scene.lookup("#btnYes");
        buttonYes.setOnAction(event -> {
            try {
                thread.stop();
                new MainController(stage, logging);
                dialogStage.close();
            }
            catch (Exception ex) {
                logging.log(Level.SEVERE, "Exception", ex);
            }
        });

        // No button
        Button buttonNo = (Button)scene.lookup("#btnNo");
        buttonNo.setOnAction(event -> {
            dialogStage.close();
        });
    }
}
