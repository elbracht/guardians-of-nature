package elementum.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class mainController {
    private Stage stage;

    public mainController(Stage stage) throws Exception {
        this.stage = stage;

        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);

        // Start Button
        Button btnStart = (Button)scene.lookup("#btnStart");
        btnStart.setOnAction(this::btnStartAction);

        // Exit Button
        Button btnExit = (Button)scene.lookup("#btnExit");
        btnExit.setOnAction(this::btnExitAction);

        //
        stage.setTitle("Elementum");
        stage.setScene(scene);
        stage.show();
    }

    private void btnStartAction(ActionEvent event) {
        stage.setScene(new Scene(new Pane()));
    }

    private void btnExitAction(ActionEvent event) {
        System.exit(0);
    }
}
