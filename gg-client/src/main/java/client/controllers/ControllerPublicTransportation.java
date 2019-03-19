package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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

        boolean nextFound = false;
        TextField current = (TextField) event.getSource();

        for (Node node : grid.getChildren()) {

            if (nextFound == true && node instanceof TextField) {

                TextField next = (TextField) node;
                next.requestFocus();
                break;

            }

            if (node instanceof TextField && node.equals(current)) {

                nextFound = true;

            }

            submitButton.requestFocus();
        }
    }

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

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }



}
