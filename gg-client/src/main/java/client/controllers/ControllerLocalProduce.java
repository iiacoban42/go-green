package client.controllers;

import static client.requests.MealRequests.sendMealList;

import client.entities.Meal;
import client.entities.MealList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerLocalProduce extends  ControllerGeneral {

    @FXML
    private Button submit;

    @FXML
    private GridPane grid;

    @FXML
    private TextField vegetables;

    @FXML
    private TextField milk;

    @FXML
    private TextField cheese;

    @FXML
    private TextField nuts;

    @FXML
    private TextField egg;

    @FXML
    private TextField chicken;

    @FXML
    private TextField pork;

    @FXML
    private TextField lamb;

    @FXML
    private TextField fruits;

    /**
     * Change textField.
     *
     * @param event text field activated.
     */

    @FXML
    public void textFieldActive(ActionEvent event) {

        changeTextField(event, grid);

        TextField current = (TextField) event.getSource();
        if (current.getId().equals("lamb")) {

            submit.requestFocus();
        }

    }

    /**
     * Send information to server. Close window.
     * @param event submit pressed.
     */
    @FXML
    public void submitPressed(ActionEvent event) {

        MealList mealList =  new MealList();
        for (Node node : grid.getChildren()) {

            if (node instanceof TextField) {

                String quantity = ((TextField) node).getText();

                if (!quantity.isEmpty()) {

                    if (validNumber(quantity)) {

                        int quantityInt = Integer.parseInt(quantity);
                        String ingredient = node.getId();

                        Meal meal = new Meal(ingredient, quantityInt);
                        mealList.addMeal(meal);
                        //for testing purposes
                        System.out.println(quantity + " " + ingredient);
                    }

                    ((TextField) node).setText("");
                }
            }

        }

        try {
            sendMealList(mealList , "http://localhost:8080/api/action/localProduce");
        } catch (IOException e) {
            System.out.println("meal was not sent to the server");
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();

    }
}