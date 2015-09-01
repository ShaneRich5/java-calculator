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

        Tree tree = FunctionAdapter.newInstance(tokens).buildTree();

        if (tree instanceof NullTree) {
            System.out.println("Error");
            displayField.setText("ERROR");
        } else {
            String result = ((FunctionTree) tree).execute();
            ((FunctionTree) tree).inOrder();
            System.out.print(" = " + result);
            displayField.setText(result);
        }
    }

    public void handleBracketAction(ActionEvent actionEvent) {
        insertBracket(((Button) actionEvent.getSource()).getText());
    }

    private void insertBracket(String bracket) {

        switch (bracket){
            case "(":

                break;
            case ")":

                break;
        }
        displayField.setText(displayField.getText() + bracket);
    }

    public void handleUndoAction(ActionEvent actionEvent) {
        String equation = displayField.getText().trim();

        int length = equation.length();

        String lastChar = equation.substring(length - 1);

        if (length <= 1) {
            displayField.setText(Constants.EMPTY_STRING);
            return;
        }

        if (Util.isNumeric(lastChar))
            equation = equation.substring(0, length - 1);
        else
            equation = equation.substring(0, length - 3);

        displayField.setText(equation);
    }
}
