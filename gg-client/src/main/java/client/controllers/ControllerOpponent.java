package client.controllers;

import static client.requests.FriendRequests.addFriendRequest;

import client.requests.FriendRequests;
import client.requests.MealRequests;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;


public class ControllerOpponent {


    @FXML
    JFXTextField addFriendTextField;

    @FXML
    JFXButton addButton;

    @FXML
    BarChart chart;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Text errorNotFoundMessage;

    @FXML
    Text successMessage;

    private String username;

    /**
     * Take friends' name.
     *
     * @param event username of a friend entered.
     */
    @FXML
    public void usernameOfFriendEntered(ActionEvent event) {

        username = addFriendTextField.getText();
        addButton.requestFocus();

    }

    /**
     * Add friend.
     *
     * @param event button add fired.
     * @throws IOException if something goes wrong.
     */
    @FXML
    public void addFriend(ActionEvent event)  {

        String response = "";

        try {
            response = addFriendRequest(username);
        } catch (RestClientResponseException e) {
            successMessage.setVisible(false);
            errorNotFoundMessage.setVisible(true);
        }
        if (response.equals("200 OK")) {
            errorNotFoundMessage.setVisible(false);
            successMessage.setVisible(true);
        } else {
            successMessage.setVisible(false);
            errorNotFoundMessage.setVisible(true);
        }

    }

    /**
     * show chart.
     */
    @FXML
    public  void  initialize() {

        try {
            FriendRequests friendRequests = new FriendRequests();

            String response =  friendRequests.getScoreAdNameOfFriend();

            System.out.println(friendRequests.getFriendScore().getScore());

            if (response.equals("200 OK")) {

                XYChart.Series scores = new XYChart.Series();
                scores.setName("Carbon Dioxide Saved (grams)");
                scores.getData().add(new XYChart.Data(friendRequests.getFriendScore().getName(),
                    friendRequests.getFriendScore().getScore()));

                scores.getData().add(new XYChart.Data("YOU", (double) MealRequests.getScore()));
                chart.getData().addAll(scores);
                chart.setBarGap(5);
            }

        } catch (RestClientResponseException e) {
            System.out.println("friend not found");
        }

    }
}

