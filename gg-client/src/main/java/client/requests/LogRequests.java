package client.requests;

import client.entities.Action;
import client.entities.ActionList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class LogRequests {

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

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/action/manage/actions";

        ResponseEntity<List> response
            = restTemplate.getForEntity(url, List.class);

        this.actionList = response.getBody();
        System.out.println(actionList.toString());

        return response.getStatusCode().toString();

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