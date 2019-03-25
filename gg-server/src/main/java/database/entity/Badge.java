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
 * represents a badge in badges.
 * stores info in database
 */
@Entity
@Table(name = "badges")
public class Badge {

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
     * A Date representing the time the badge was given.
     */
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    /**
     * A String representing the name of the badge.
     */
    @Column(name = "name")
    private String badgeName;

    /**
     * A String representing the username of the user who performed the action.
     */
    @Column(name = "user")
    private String user;



    /**
     * Creates a badge to insert in badges.
     * id will be auto-generated after it has been inserted in table.
     * @param badgeName a String that represents the name of the badge
     * @param user a String representing the username of the User who performed the action
     */
    public Badge(String badgeName, String user) {
        this.badgeName = badgeName;
        this.user = user;
    }

    public Badge(){}

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

    public String getBadgeName() {
        return badgeName;
    }

    private void setBadgeName(final String badgeName) {
        this.badgeName = badgeName;
    }

    public String getUser() {
        return user;
    }

    private void setUser(final String user) {
        this.user = user;
    }
}
