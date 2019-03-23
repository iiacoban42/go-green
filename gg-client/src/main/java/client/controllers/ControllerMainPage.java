package client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;


public class ControllerMainPage extends ControllerGeneral {
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



    /**
     * on hoovering show dialog.
     */
    @FXML
    public void loadMealDialog(MouseEvent event) {

        JFXDialogLayout content =  new JFXDialogLayout();
        content.setHeading(new Text("Meal Calculator"));
        content.setBody(new Text("We calculate the Carbon Dioxide\n"
                +
                                 "you produce by comparing your\n"
                +
                                 "meal with the Dutch average meal.\n\n"
                +

                "Please fill what you ate and then press Submit.\n"
                ));

        JFXButton button = new JFXButton("Close");

        JFXDialog dialog = new JFXDialog(stackMeal , content , JFXDialog.DialogTransition.CENTER);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        content.setActions(button);
            dialog.show();
    }

}


