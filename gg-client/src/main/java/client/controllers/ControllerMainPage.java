package client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ControllerMainPage {

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
    Button leaderboardButton;

    @FXML
    Button savedcoButton;

    @FXML
    Button friendsButton;

    @FXML
    Button achievementsButton;

    /**
     * When the mealButton is pressed new window for meal feature appears.
     *
     * @param event meal button is fired
     * @throws IOException input output exception is thrown.
     */
    public void mealButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/veggie_meal.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("GoGreen");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }

    /**
     * When Co2 saved button is pressed a new window appears with the Co2 saved by the user.
     *
     * @param event savedCo2 button pressed.
     * @throws IOException if something goes wrong.
     */
    @FXML
    public void savedcoButtonPressed(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/savedCarbon.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("GoGreen");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);

    }

    /**
     * When bike button is pressed a new window appears.
     *
     * @param event bike button pressed.
     * @throws IOException if something goes wrong.
     */
   @FXML
   public void bikeButtonPressed(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bike_feature.fxml"));
       Parent root = fxmlLoader.load();
       Stage stage = new Stage();
       stage.initModality(Modality.APPLICATION_MODAL);
       stage.initStyle(StageStyle.DECORATED);
       stage.setTitle("GoGreen");
       stage.setScene(new Scene(root));
       stage.show();
       stage.setResizable(false);

   }

}
