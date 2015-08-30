package ui.components.controllers;

import function.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by shane on 8/30/15.
 */
public class CalculatorController implements Initializable {

    @FXML private TextField displayField;

    private String lastInput;
    private static boolean errorStatus;

    @Override public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }


    @FXML public void handleOperandAction(ActionEvent actionEvent) {
        String digit = ((Button) actionEvent.getSource()).getText();
        String equation = displayField.getText();

        equation += digit;

        displayField.setText(equation);
    }

    @FXML public void handleOperatorAction(ActionEvent actionEvent) {
        String operator = ((Button) actionEvent.getSource()).getText();
        String equation = displayField.getText();

        equation += " " + operator + " ";

        displayField.setText(equation);
    }

    @FXML public void handleEqualAction(ActionEvent actionEvent) {
        String expression = displayField.getText();

        String[] tokens = FunctionParser.tokenize(expression);

        System.out.println(Arrays.toString(tokens));

        Tree tree = FunctionAdapter.newInstance(tokens).buildTree();

        if (tree instanceof NullTree) {
            System.out.println("Error");
            displayField.setText("ERROR");
        } else {
            ((FunctionTree) tree).postOrder();
            displayField.setText(((FunctionTree) tree).execute());
        }
    }
}
