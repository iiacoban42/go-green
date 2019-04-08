package client.controllers;

import static client.requests.TemperatureRequests.sendTemperature;

import client.entities.Temperature;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerTemperature {

    @FXML
    VBox box;

    private String stringSurface;

    private String stringEnergy;

    private String heatingSystem;


    @FXML
    private TextField surface;

    @FXML
    private TextField energy;

    @FXML
    private RadioButton electricity;

    @FXML
    private RadioButton naturalGas;

    @FXML
    private RadioButton heatingOil;

    @FXML
    private Button submit;


    /**
     * Take surface from user.
     *
     * @param event surface Entered
     */
    @FXML
    public void surfaceTextFieldActivated(ActionEvent event) {

        stringSurface = surface.getText();
        energy.requestFocus();

    }

    /**
     * Get energy from the user.
     *
     * @param event energy entered.
     */
    @FXML
    public void energyTextFieldActivated(ActionEvent event) {

        stringEnergy = energy.getText();
        electricity.requestFocus();

    }


    /**
     * Close the window.
     *
     * @param event submit button pressed.
     */
    @FXML
    public void submitButtonPressed(ActionEvent event) throws IOException {
        stringSurface = surface.getText();
        stringEnergy = energy.getText();

        for (Node node : box.getChildren()) {


            if (node instanceof RadioButton) {

                if (((RadioButton) node).isSelected()) {

                    heatingSystem = node.getId();

                }


            }

        }

        Temperature temperature = new Temperature(Double.parseDouble(stringSurface),
                Double.parseDouble(stringEnergy), heatingSystem);

        sendTemperature(temperature);


        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }


}