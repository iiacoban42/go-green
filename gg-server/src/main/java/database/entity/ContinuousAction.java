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
@Table(name = "continuous_action")
public class ContinuousAction {

    /**
     * Primary key.
     * an unique integer representing id
     * increments by one for each continous action
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * a String representing the username/primary key of user performing action.
     */
    @Column(name = "user")
    private String user;

    /**
     * a String representing the name of the continouses action.
     */
    @Column(name = "action_name")
    private String actionName;

    /**
     * A Date representing the time the continouse action was initiated.
     */
    @Column(name = "date_action_begane")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    /**
     * an integer representing the score gained each day loged in.
     */
    @Column(name = "score_per_day")
    private int scorePerDay;

    /**
     * an integer representing the amount of times the user has cashed in the score.
     */
    @Column(name = "days_cashed_in")
    private int daysCashedIn;

    /**
     * a Date representing the last day user cashed in.
     */
    @Column(name = "date_last_cashed_in")
    private Date dateLastCashedIn;

    /**
     * a integer representing the total score recieved by continouse action.
     */
    @Column(name = "total_score")
    private int totalScore;

    /**
     * a Date representing the date the action ended.
     */
    @Column(name = "date_ended")
    private Date dateEnded;

    /**
     * an integer representing relevant information such as number of solar panels.
     */
    @Column(name = "number_solar_panels")
    private int numSolarPanels;

    /**
     * Constructs a continouse action with total score set to scorePerDay.
     * @param user a Stiring representing the primary key/username of user.
     * @param actionName a String representing the name of the continous action.
     * @param scorePerDay a Int representing the score gained per day
     */
    public ContinuousAction(String user, String actionName, int scorePerDay, int numSolarPanels) {
        this.user = user;
        this.actionName = actionName;
        this.scorePerDay = scorePerDay;
        this.numSolarPanels = numSolarPanels;
        this.totalScore = scorePerDay;
    }

    public ContinuousAction() {}

    @PrePersist
    void creatAt() {
        this.dateTime = new Date();
        this.dateLastCashedIn = new Date();
    }

    public long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getScorePerDay() {
        return scorePerDay;
    }

    public void setScorePerDay(int scorePerDay) {
        this.scorePerDay = scorePerDay;
    }

    public int getDaysCashedIn() {
        return daysCashedIn;
    }

    public void setDaysCashedIn(int daysCashedIn) {
        this.daysCashedIn = daysCashedIn;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public int getNumSolarPanels() {
        return numSolarPanels;
    }

    public void setNumSolarPanels(int numSolarPanels) {
        this.numSolarPanels = numSolarPanels;
    }

    public Date getDateLastCashedIn() {
        return dateLastCashedIn;
    }

    public void setDateLastCashedIn(Date dateLastCashedIn) {
        this.dateLastCashedIn = dateLastCashedIn;
    }

    /**
     * Does everything needed to check in a day.
     */
    public void chashIn() {
        this.totalScore += this.scorePerDay;
        this.dateLastCashedIn = new Date();
        this.daysCashedIn ++;
    }
}
