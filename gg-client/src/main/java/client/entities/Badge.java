package client.entities;

import java.util.Date;

public class Badge {
    private long id;
    private Date dateTime;
    private String badgeName;
    private String user;
    private int level;

    public Badge() {}

    /**
     * Constructor.
     * @param id .
     * @param dateTime .
     * @param badgeName .
     * @param user .
     * @param level .
     */
    public Badge(long id , Date dateTime , String badgeName , String user , int level) {

        this.id = id;
        this.dateTime = dateTime;
        this.badgeName = badgeName;
        this.user = user;
        this.level = level;
    }


    public long getId() {
        return id;
    }


    public String getBadgeName() {
        return badgeName;
    }


    public String getUser() {
        return user;
    }



    public int getLevel() {
        return level;
    }

}
