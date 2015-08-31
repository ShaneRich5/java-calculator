package ui.components.controllers;

import function.*;
import function.util.Util;
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

    private String lastInput = Constants.EMPTY_STRING;
    private static boolean errorStatus;

    @Override public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }


    @FXML public void handleOperandAction(ActionEvent actionEvent) {
        insertNewDigit(((Button) actionEvent.getSource()).getText());
    }

    private void insertNewDigit(String digit) {
        if (Util.isNumeric(lastInput)) {
            lastInput += digit;
        } else { // resets the last input variable
            lastInput = digit;
        }

        // updates display
        displayField.setText(
                displayField.getText() + digit
        );
    }

    @FXML public void handleOperatorAction(ActionEvent actionEvent) {
        insertNewOperator(((Button) actionEvent.getSource()).getText());
    }

    private void insertNewOperator(String operator) {
        String equation = displayField.getText();
        // if the last input was numeric
        // replace it with an operator and update the display

        if (!Util.isNumeric(lastInput)) {
            // remove the operator and surrounding white spaces
            equation = equation.trim().substring(0, equation.length() - 3);
        }
        displayField.setText(equation + " " + operator + " ");
        lastInput = operator;
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
