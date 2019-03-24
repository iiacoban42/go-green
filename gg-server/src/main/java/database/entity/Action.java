package database.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * represents an action in the action_log table.
 * stores info in database
 */
@Entity
@Table(name = "action_log")
public class Action {

    /**
     * Primary key.
     * an unique integer representing id
     * increments by one for each action performed
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * A Date representing the time the action was performed.
     */
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    /**
     * A String representing the name of the action performed.
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * A String representing the username of the user who performed the action.
     */
    @Column(name = "user")
    private String user;

    /**
     * A Integer representing the co2_score gained by performing the action.
     */
    @Column(name = "score")
    private int score;


    /**
     * Creates an action to insert in the action log.
     * id will be auto-generated after it has been inserted in table.
     * @param actionName a String that represents the name of the action performed
     * @param user a String representing the username of the User who performed the action
     * @param score a Int representing the score gained by the action
     */
    public Action(String actionName, String user, int score) {
        this.actionName = actionName;
        this.user = user;
        this.score = score;
    }

    public Action(){}

    @PrePersist
    void creatAt() {
        this.dateTime = new Date();
    }

    public long getId() {
        return id;
    }

    private Date getDateTime() {
        return dateTime;
    }

    public String getActionName() {
        return actionName;
    }

    private void setActionName(final String actionName) {
        this.actionName = actionName;
    }

    public String getUser() {
        return user;
    }

    private void setUser(final String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    private void setScore(final int score) {
        this.score = score;
    }

}
