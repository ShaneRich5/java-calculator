package ui.components.dialogs;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by shane on 8/25/15.
 */
public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(title);
        Button positiveBtn = new Button("Confirm");
        Button negativeBtn = new Button("Decline");

        positiveBtn.setOnAction(e -> {
            answer = true;
            window.close();
        });

        negativeBtn.setOnAction(e -> {
            answer = false;
            window.close();
        });

    }
}
