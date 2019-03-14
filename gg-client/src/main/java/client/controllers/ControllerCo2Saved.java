package client.controllers;

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
    public void showButtonPressed(ActionEvent event){

        coSavedVariable.setText("Placeholder");
        gramsLabel.setVisible(true);
        ofcoLabel.setVisible(true);



    }
}
