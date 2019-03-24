package client.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class ControllerMainPage extends ControllerGeneral {

    public static boolean dialogBoxOn = false;

    @FXML
    JFXButton close;

    @FXML
    StackPane stackPanels;

    @FXML
    StackPane stackTemperature;

    @FXML
    StackPane stackLProduce;

    @FXML
    StackPane stackTransport;

    @FXML
    StackPane stackBike;

    @FXML
    StackPane stackMeal;

    @FXML
    Pane pane;

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

    @FXML
    ImageView imageMeal;




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

    /**
     * Change scene.
     * @param event Home page button pressed.
     * @throws IOException if something goes wrong.
     */
    @FXML
    public void homePageButtonPressed(ActionEvent event) throws IOException {

        dialogBoxOn = false;
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

    @FXML
    public  void tempereturePressed(ActionEvent event) throws IOException {

        changeWindow("temperature.fxml");
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



    /**
     * On hovering show dialog.
     * @param event  hovering.
     */
    @FXML
    public void loadMealDialogMeal(MouseEvent event) {

        String text = "We calculate the Carbon Dioxide\n"
                +
                "you save by comparing your\n"
                +
                "meal with the Dutch average meal.\n\n"
                +

                "Please fill in  what you ate "
                +
                 "and then press Submit.\n";

        if (!dialogBoxOn) {
            showDialog(text, "Meal Calculator", stackMeal);
        }
    }


    /**
     * Show dialog box.
     * @param event mouse hovering
     */
    @FXML
    public void loadMealDialogBike(MouseEvent event) {

        String text = "We calculate the Carbon Dioxide\n"
                +
                "you save by cycling instead of using the\n"
                +
                "car.\n\n"
                +

                "Please fill in the kilometers you have traveled\n"
                +
                 "and then press Submit";

        if (!dialogBoxOn) {
            showDialog(text, "Calculator for cycling", stackBike);
        }
    }

    /**
     * Show dialog box.
     * @param event mouse hovering
     */
    @FXML
    public void loadMealDialogTransport(MouseEvent event) {

        String text = "We calculate the Carbon Dioxide\n"
                +
                "you save by using public transportation\n"
                +
                 "instead of using the car.\n\n"
                +
                 "Please fill in the kilometers you have traveled\n"
                +
                "and then press Submit";

        if (!dialogBoxOn) {
            showDialog(text, "Calculator for public Transportation", stackTransport);
        }
    }


}


