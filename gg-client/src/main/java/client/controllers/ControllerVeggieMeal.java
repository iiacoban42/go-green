
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

public class ControllerVeggieMeal extends ControllerGeneral {

    @FXML
    private Button submit;

    @FXML
    private GridPane grid;

    @FXML
    private TextField beans;

    @FXML
    private TextField veggieBurger;

    @FXML
    private TextField veggieBurgerCheese;

    @FXML
    private TextField quorn;

    @FXML
    private TextField nuts;

    @FXML
    private TextField tofu;

    @FXML
    private TextField egg;

    @FXML
    private TextField beefCroquette;

    @FXML
    private TextField chicken;

    @FXML
    private TextField pork;

    @FXML
    private TextField steak;

    @FXML
    private TextField lamb;

    @FXML
    private TextField hamburger;

    @FXML
    private TextField mincedMeat;

    @FXML
    private TextField mixedMincedMeat;

    @FXML
    private TextField insects;

    @FXML
    private TextField cheese;

    /**
     * Move to next textField.
     * @param event text field activated.
     */
    @FXML
    public  void textFieldActive(ActionEvent event) {

        changeTextField( event , grid );

        TextField current = (TextField) event.getSource();
        if (current.getId().equals("cheese")) {

            submit.requestFocus();
        }
    }

    @FXML
    void sendVeggieMeal(ActionEvent event) {

        MealList list = new MealList();

        for (Node node : grid.getChildren()) {

            if (node instanceof TextField) {

                String quantity = ((TextField) node).getText();

                if (!quantity.isEmpty()) {

                    if (validNumber(quantity)) {

                        int quantityInt = Integer.parseInt(quantity);
                        String ingredient = node.getId();

                        Meal meal = new Meal(ingredient, quantityInt);
                        list.addMeal(meal);
                        //for testing purposes
                        System.out.println(quantity + " " + ingredient);
                    }

                    ((TextField) node).setText("");
                }
            }
        }

        try {
            sendMealList(list);
        } catch (IOException e) {
            System.out.println("meal was not sent to the server");
        }

        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }




}