package ui.components;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author shane on 8/25/15.
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
