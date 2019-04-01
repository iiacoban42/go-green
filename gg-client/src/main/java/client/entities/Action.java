package client.entities;

public class Action {

    private long id;
    private String actionName;
    private String user;
    private int score;

    /**
     * Constructor.
     *
     * @param id         action id
     * @param actionName action type
     * @param user       user
     * @param score      score
     */
    public Action(long id, String actionName, String user, int score) {

        this.actionName = actionName;
        this.id = id;
        this.score = score;
        this.user = user;
    }

    /**
     * Empty constructor.
     */
    public Action() {
    }

    public long getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id action id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get action.
     *
     * @return action
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Set action.
     *
     * @param action action type.
     */
    public void setActionName(String action) {
        this.actionName = action;
    }

    /**
     * Get user.
     *
     * @return user.
     */
    public String getUser() {
        return user;
    }

    /**
     * SetUser.
     *
     * @param user user.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Get score.
     *
     * @return score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Set score.
     *
     * @param score score
     */
    public void setScore(int score) {
        this.score = score;
    }
}
