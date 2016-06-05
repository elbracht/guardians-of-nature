package elementum.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class mainController {
    private Stage stage;

    public mainController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../assets/main.css").toExternalForm());

        stage.setTitle("Elementum");
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
            new cardpollController(stage);
        }
        catch (Exception ex) {
            // TODO: Catch exception
        }
    }

    private void btnExitAction(ActionEvent event) {
        System.exit(0);
    }
}
