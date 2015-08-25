package ui.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.components.dialogs.AlertBox;
import ui.components.dialogs.ConfirmBox;


/**
 * Created by shane on 8/25/15.
 */
public class Calculator extends Application{

    Scene sceneOne, sceneTwo;

    @Override
    public void start(Stage window) throws Exception {
        Label labelOne = new Label("Welcome to scene one");
        Button buttonOne = new Button("Go to scene 2");
        Button buttonTwo = new Button("Scene two");
        Button alertDialog = new Button("Display alert");
        Button confirmDialog = new Button("Confirm");

        buttonOne.setOnAction(e -> window.setScene(sceneTwo));
        buttonTwo.setOnAction(e -> window.setScene(sceneOne));
        alertDialog.setOnAction(e -> AlertBox.display("ALERT!", "This is an alert box"));
        confirmDialog.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirmation Box", "Chose an answer");
            System.out.println(result);
        });

        VBox layoutOne = new VBox(20);
        layoutOne.getChildren().addAll(labelOne, buttonOne, alertDialog, confirmDialog);

        sceneOne = new Scene(layoutOne, 200, 200);


        StackPane layoutTwo = new StackPane();
        layoutTwo.getChildren().add(buttonTwo);

        sceneTwo = new Scene(layoutTwo, 600, 300);

        window.setScene(sceneOne);
        window.setTitle("Calculator");
        window.show();
    }
}
