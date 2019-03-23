package client.controllers;

import client.entities.ActionList;
import client.requests.LogRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.text.Font;

import java.util.List;


public class ControllerLog {



    @FXML
    Button show;

    @FXML
    JFXListView listView;

    @FXML
    void initialize() {
        show.fire();
    }

    /**
     * Get request to server.
     * @param event show button pressed.
     */
    @FXML
    public void showButtonPressed(ActionEvent event) throws JsonProcessingException {

        LogRequests logRequests = new LogRequests();
        logRequests.getAllActionsFromTheServer();
        logRequests.getActionList();
        ActionList actions = logRequests.converter();
        List<String> actionDetails = actions.getAllActionNamesAndScore();

        initializeList(actionDetails);







    }

    /**
     * Display parts of the action List.
     */
    public void initializeList(List list) {

        ObservableList data = FXCollections.observableArrayList(list);
        listView.setItems(data);

    }

}
