package client.controllers;

import client.entities.ActionList;
import client.requests.LogRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;


public class ControllerLog {


    @FXML
    Button show;

    @FXML
    ListView actionList;

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
        List actionList = logRequests.getActionList();
        ActionList actions = logRequests.converter();
        List<String> actionNames = actions.getAllActionNames();

        initializeList(actionNames);


    }

    /**
     * Display parts of the action List.
     * @param list action list.
     */
    public void initializeList(List list) {

        ObservableList observableList = FXCollections.observableArrayList(list);
        actionList.setItems(observableList);
    }

}
