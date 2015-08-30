package ui.components.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by shane on 8/30/15.
 */
public class CalculatorController implements Initializable {

    @FXML private TextField displayField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }

    @FXML public void handleZeroAction(ActionEvent event){
        System.out.println("0 pressed");
    }

}
