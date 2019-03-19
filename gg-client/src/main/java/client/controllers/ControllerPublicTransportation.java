package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


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
     * Send public transportation activities.
     * @param event public transportation activities entered.
     */
    @FXML
    void sendPublicTransportationActivities( ActionEvent event) {

        for (Node node : grid.getChildren()) {

            if (node instanceof TextField) {

                String kilometers = ((TextField) node).getText();

                if (!kilometers.isEmpty()) {

                    if (validNumber(kilometers)) {

                        double km = Double.parseDouble(kilometers);
                        String publicTransport = node.getId();


                        //for testing purposes
                        System.out.println(publicTransport + " " + kilometers);
                    }

                    ((TextField) node).setText("");
                }
            }
        }
    }



}
