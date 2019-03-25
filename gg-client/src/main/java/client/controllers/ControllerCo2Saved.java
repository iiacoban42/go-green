package client.controllers;

import client.requests.MealRequests;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ControllerCo2Saved {

    @FXML
    Button buttonShow;

    @FXML
    Label coSavedVariable;

    @FXML
    Label ofcoLabel;

    @FXML
    Label gramsLabel;


    @FXML
    void initialize() {
        buttonShow.fire();
    }

    /**
     * When show button is pressed  CO2 SAVED appears.
     */
    @FXML
    public void showButtonPressed(ActionEvent event) {

        int score = MealRequests.getScore();
        coSavedVariable.setText(String.valueOf(score));
        gramsLabel.setVisible(true);
        ofcoLabel.setVisible(true);

    }
}
