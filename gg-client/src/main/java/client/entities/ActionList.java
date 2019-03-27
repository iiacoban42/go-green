package client.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public int getSize() {

        return actionList.size();
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

            StringBuilder stringBuilder = new StringBuilder(50);
            stringBuilder.insert(0 , action.getActionName() + "              ");
            stringBuilder.insert(16 , action.getScore());
            stringBuilder.delete(16 + String.valueOf(action.getScore()).length(), action.getActionName().length() + String.valueOf(action.getScore()).length() + 14);
            System.out.println(stringBuilder.toString());

            String actionDetails = stringBuilder.toString();
            details.add(actionDetails);
        }

        System.out.println( details.toString());

        return details;
    }


}
