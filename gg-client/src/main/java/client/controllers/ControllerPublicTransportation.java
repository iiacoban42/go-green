package client.controllers;

import static client.requests.TransportRequests.sendTransportList;

import client.entities.Transport;
import client.entities.TransportList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;



public class ControllerPublicTransportation extends ControllerGeneral {

    @FXML
    private GridPane grid;

    @FXML
    private TextField bus;

    @FXML
    private TextField tram;

    @FXML
    private TextField metro;

    @FXML
    private TextField scooter;

    @FXML
    private Button submitButton;

    /**
     * Move to next TextField.
     * @param event text field fired.
     */
    @FXML
    public  void textFieldActive(ActionEvent event) {

        changeTextField( event , grid );

        TextField current = (TextField) event.getSource();
        if (current.getId().equals("scooter")) {

            submitButton.requestFocus();
        }

    }

    /**
     * Send public transportation activities.
     * @param event public transportation activities entered.
     */
    @FXML
    void sendPublicTransportationActivities( ActionEvent event) {

        TransportList transportList = new TransportList();

        for (Node node : grid.getChildren()) {

            if (node instanceof TextField) {

                String kilometers = ((TextField) node).getText();

                if (!kilometers.isEmpty()) {

                    if (validNumber(kilometers)) {

                        double km = Double.parseDouble(kilometers);
                        String publicTransport = node.getId();


                        Transport transport = new Transport(publicTransport , km);
                        transportList.addTransport(transport);

                        System.out.println(publicTransport + " " + kilometers);
                    }

                    ((TextField) node).setText("");
                }
            }
        }

        try {
            sendTransportList(transportList);
        } catch (IOException e) {
            System.out.println("Transport list was not send to the server");
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }



}
