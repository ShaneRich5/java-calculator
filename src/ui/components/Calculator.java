package ui.components;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shane on 8/25/15.
 */
public class Calculator extends Application {

    private Stage window;

    @Override public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("views/CalculatorOverview.fxml"));

        Scene scene = new Scene(root);

        window = primaryStage;
        window.setScene(scene);
        window.setTitle(Calculator.class.getSimpleName());
        window.show();
    }

    /**
     * @param args  command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
