package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class ControllerMainPage extends ControllerGeneral {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Button logoutButton;

    @FXML
    Button mealButton;

    @FXML
    Button transportButton;

    @FXML
    Button temperatureButton;

    @FXML
    Button localProduceButton;

    @FXML
    Button solarButton;

    @FXML
    Button bikeButton;

    @FXML
    Button homePageButton;

    @FXML
    Button savedcoButton;

    @FXML
    Button friendsButton;

    @FXML
    Button achievementsButton;

    @FXML
    Button logButton;

    /**
     * When the mealButton is pressed new window for meal feature appears.
     *
     * @param event meal button is fired
     * @throws IOException input output exception is thrown.
     */
    public void mealButtonPressed(ActionEvent event) throws IOException {
        changeWindow("veggie_meal.fxml");
    }

    /**
     * When Co2 saved button is pressed a new window appears with the Co2 saved by the user.
     *
     * @param event savedCo2 button pressed.
     * @throws IOException if something goes wrong.
     */
    @FXML
    public void savedcoButtonPressed(ActionEvent event) throws IOException {

        changeScene("savedCarbon.fxml", anchorPane);
    }

    @FXML
    public void homePageButtonPressed(ActionEvent event) throws IOException {

        changeScene("mainMenu.fxml" ,anchorPane);
    }

    /**
    * When bike button is pressed a new window appears.
    * @param event bike button pressed.
    * @throws IOException if something goes wrong.
    */

    @FXML
    public void bikeButtonPressed(ActionEvent event) throws IOException {

        changeWindow("bike_feature.fxml");

    }


    /**
    * Change window.
    * @param event button for public transportation pressed.
    * @throws IOException if something goes wrong.
    */


    @FXML
    public void publicTransportationPressed( ActionEvent event) throws IOException {

        changeWindow("publicTransportation.fxml");

    }

    @FXML
    public void logButtonPressed(ActionEvent event) throws IOException {

        changeScene("log.fxml" , anchorPane );
    }





}
