package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerTemperature {

    private String stringSurface;

    private String stringEnergy;


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
     * @param event surface Entered
     */
    @FXML
    public void surfaceTextFieldActivated(ActionEvent event) {

        stringSurface = surface.getText();
        energy.requestFocus();

    }

    /**
     * Get energy from the user.
     * @param event energy entered.
     */
    @FXML
    public void energyTextFieldActivated(ActionEvent event) {

        stringEnergy = energy.getText();
        electricity.requestFocus();

    }

    /**
     * Close the window.
     * @param event submit button pressed.
     */
    @FXML
    public void submitButtonPressed(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }





}