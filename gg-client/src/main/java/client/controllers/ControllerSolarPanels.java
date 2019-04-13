package client.controllers;

import static client.requests.SolarPanelRequests.getSolarPanels;
import static client.requests.SolarPanelRequests.sendSolarPanels;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.web.client.RestClientResponseException;


public class ControllerSolarPanels {

    @FXML
    Label numSolarPanels;

    @FXML
    JFXButton submit;

    @FXML
    JFXTextField panels;

    /**
     * Display number of solar panels.
     */
    public void initialize() {

        int num;
        try {
            num = getSolarPanels();
        } catch (RestClientResponseException e) {
            num = 0;
        }
        numSolarPanels.setText(Integer.toString(num));
    }

    /**
     * Close window.
     *
     * @param event submit pressed.
     */
    @FXML
    public void submitButtonPressed(ActionEvent event) {
        int amount = Integer.parseInt(panels.getText());
        try {
            sendSolarPanels(amount);
        } catch (JsonProcessingException e) {
            System.out.print("something went wrong");
        }
        submit.getScene().getWindow().hide();

    }


}
