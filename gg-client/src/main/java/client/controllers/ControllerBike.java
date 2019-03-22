package client.controllers;

import client.entities.Transport;
import client.entities.TransportList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static client.requests.TransportRequests.sendTransportList;


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
            Double km =  Double.parseDouble(distance);
            Transport transport = new Transport("bike" ,km);
            TransportList transportList = new TransportList();
            transportList.addTransport(transport);
            try {
                sendTransportList(transportList);
            } catch (IOException e) {
                System.out.println("Transport list was not send to the server");
            }
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }

}
