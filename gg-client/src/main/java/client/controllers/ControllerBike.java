package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ControllerBike extends ControllerGeneral{

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
    }

}
