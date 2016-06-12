package elementum.controllers;

import elementum.controllers.utils.CursorLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {
    private Stage stage;

    public MainController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("/elementum/views/main.fxml"));
        root.setCursor(CursorLoader.getDefault());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/elementum/assets/main.css").toExternalForm());

        stage.setTitle("Elementum");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Start Button
        Button btnStart = (Button)scene.lookup("#btnStart");
        btnStart.setOnAction(this::btnStartAction);

        // Exit Button
        Button btnExit = (Button)scene.lookup("#btnExit");
        btnExit.setOnAction(this::btnExitAction);
    }

    private void btnStartAction(ActionEvent event) {
        try {
            new CardpollController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }

    private void btnExitAction(ActionEvent event) {
        System.exit(0);
    }
}
