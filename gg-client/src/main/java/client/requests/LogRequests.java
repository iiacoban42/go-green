package client.requests;

import client.entities.Action;
import client.entities.ActionList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LogRequests extends  GeneralRequests {

    private List actionList;

    /**
     * Constructor. Makes request to the server for the actionList.
     */
    public LogRequests() {

        this.actionList = new ArrayList();
    }

    /**
     * Request all actions from the server.
     *
     * @return status Code.
     */
    public String getAllActionsFromTheServer() {


        String url = "http://localhost:8080/api/action/manage/actions";

        Pair pair =  doGetRequest(url , actionList.getClass());

        this.actionList = (List) pair.getValue();
        System.out.println(actionList.toString());

        return (String) pair.getKey();






    }

    /**
     * Getter for action list.
     *
     * @return action list or a new ArrayList if its empty.
     */
    public List getActionList() {
        return actionList;
    }

    /**
     * Set actionList.
     * @param actionList list.
     */
    public void setActionList(List actionList) {
        this.actionList = actionList;
    }

    /**
     * .
     *
     * @return Action action
     */
    public ActionList converter() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ActionList actions = new ActionList();

        for (Object o : actionList) {

            Action action = objectMapper.convertValue(o, Action.class);
            actions.addAction(action);
        }

        return actions;
    }


}
