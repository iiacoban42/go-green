package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class ControllerPublicTransportation {

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

                    if (valid(kilometers)) {

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



    /**
     * Test if given message is valid.
     *
     * @param message to check if valid
     * @return boolean
     */
    public boolean valid(String message) {

        try {
            double number = Double.parseDouble(message);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is NAN");
            return false;
        }
    }

}
