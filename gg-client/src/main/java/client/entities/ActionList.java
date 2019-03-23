package client.entities;

import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private List<Action> actionList;

    public ActionList() {

        actionList = new ArrayList<>();
    }

    public ActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public List<Action> getActionList() {

        return actionList;
    }

    public void addAction(Action action) {

        actionList.add(action);
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    /**
     * Get names of all actions.
     *
     * @return List type of actions
     */
    public List<String> getAllActionNamesAndScore() {

        List<String> details = new ArrayList<>();

        for (Action action : actionList) {

            String actionDetails = action.getActionName() + "                   "  +  action.getScore();
            details.add(actionDetails);
        }

        return details;
    }


}
