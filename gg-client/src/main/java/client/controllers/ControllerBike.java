package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerBike extends ControllerGeneral {

    @FXML
    TextField distanceTextField;

    /**
     * Get distance.
     * @param event distance entered.
     */
    @FXML
    public void distanceEntered(ActionEvent event) {

        String distance = distanceTextField.getText();
        if (validNumber(distance)) {

            System.out.println(distance);

        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }

}
