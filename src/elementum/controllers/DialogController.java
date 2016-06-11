package elementum.controllers;

import elementum.controllers.utils.CursorLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DialogController {
    private Stage stage;
    private Stage dialogStage;

    public DialogController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/dialog.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        dialogStage = new Stage();
        dialogStage.setTitle("Spiel beenden");
        dialogStage.setResizable(false);
        dialogStage.setScene(scene);
        dialogStage.show();

        Button buttonYes = (Button)scene.lookup("#btnYes");
        buttonYes.setOnAction(event -> {
            try {
                new MainController(stage);
                dialogStage.close();
            }
            catch (Exception ex) {
                // TODO: Catch exception
            }
        });

        Button buttonNo = (Button)scene.lookup("#btnNo");
        buttonNo.setOnAction(event -> {
            dialogStage.close();
        });
    }
}
