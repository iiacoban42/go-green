package client.entities;

import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private  List<Action> actionList;

    public ActionList() {

        actionList = new ArrayList<Action>();
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
     * @return List<String> type of actions.
     */
    public List<String> getAllActionNames() {

        List<String> names = new ArrayList<String>();

        for (int i = 0; i < actionList.size(); i++) {

            String actionName = actionList.get(i).getAction();
            names.add(actionName);
        }

        return names;
    }

    /**
     * Get all scores for each action.
     * @return List<Integer>
     */
    public List<Integer> getAllScores() {

        List<Integer> actionScores = new ArrayList<>();

        for (int i = 0; i < actionList.size(); i++) {

            int actionScore = actionList.get(i).getScore();
            actionScores.add(actionScore);
        }

        return actionScores;
    }
}
