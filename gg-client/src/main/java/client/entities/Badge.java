package client.entities;

import java.util.Date;

public class Badge {
    private long id;
    private Date dateTime;
    private String badgeName;
    private String user;
    private int level;



    public Badge(){}


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

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
