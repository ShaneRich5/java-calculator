package ui.components.controllers;

import function.components.FunctionTree;
import function.components.NullTree;
import function.components.Tree;
import function.exceptions.MalformedNumberException;
import function.util.Constants;
import function.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by shane on 8/30/15.
 */
public class CalculatorController implements Initializable {

    @FXML private TextField displayField;

    private String lastInput = Constants.EMPTY_STRING;

    private static boolean errorStatus = false;
    private static int incompleteBracktCount = 0;

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

        Tree tree = FunctionTree.buildTree(displayField.getText());

        String result = Constants.ERROR_STRING;

        errorStatus = true;

        if (tree instanceof NullTree) {
            System.out.println(result);
            displayField.setText(result);
        } else {
            try {
                result = ((FunctionTree) tree).execute();
                ((FunctionTree) tree).inOrder();
                System.out.print(" = " + result);
                errorStatus = false;
            } catch(MalformedNumberException e) {
                System.out.println("Malformed Exception");
            } catch(NullPointerException e) {
                System.out.println("NullPointer Exception");
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
            }
            displayField.setText(result);
        }
    }

    public void handleBracketAction(ActionEvent actionEvent) {

        insertBracket(((Button) actionEvent.getSource()).getText());

    }

    private void insertBracket(String bracket) {

        switch (bracket){
            case "(":
                incompleteBracktCount++;
                break;
            case ")":
                if (incompleteBracktCount <= 0)
                    return;
                incompleteBracktCount--;
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

    public void handleResetAction(ActionEvent actionEvent) {
        errorStatus = false;
        displayField.setText(Constants.EMPTY_STRING);
    }

    private void resetErrorStatus() {
        if (errorStatus) {
            errorStatus = false;
            displayField.setText(Constants.EMPTY_STRING);
        }
    }
}
