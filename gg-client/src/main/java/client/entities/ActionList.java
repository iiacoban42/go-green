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
    public List<String> getAllActionNames() {

        List<String> names = new ArrayList<>();

        for (Action action : actionList) {

            String actionName = action.getActionName();
            names.add(actionName);
        }

        return names;
    }

    /**
     * Get all scores for each action.
     *
     * @return List
     */
    public List<Integer> getAllScores() {

        List<Integer> actionScores = new ArrayList<>();

        for (Action action : actionList) {

            int actionScore = action.getScore();
            actionScores.add(actionScore);
        }

        return actionScores;
    }
}
