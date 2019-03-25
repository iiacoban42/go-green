package client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ControllerSolarPanels {

    @FXML
    JFXButton submit;

    @FXML
    JFXTextField panels;

    /**
     * Close window.
     * @param event submit pressed.
     */
    @FXML
    public void submitButtonPressed(ActionEvent event) {
        submit.getScene().getWindow().hide();

    }

}
