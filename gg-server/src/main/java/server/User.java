package server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * represents a User.
 * stores info in database
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * primary key
     * a String representing the users username.
     */
    @Id
    @Column(name = "username")
    private String username;

    /**
     * a String representing the usres hashed password.
     */
    @Column(name = "hash_password")
    private String hashPassword;

    /**
     * a String representing the users email.
     */
    @Column(name = "email")
    private String email;

    /**
     * an Intiger representing the users total carbon emmisions saved
     */
    @Column (name = "total_co2_score")
    private int tScore;

    /**
     * creates a user with a tScore of 0.
     */
    public User() {
        tScore = 0;
    }

    /**
     * gets username of user.
     * @return a String representing the username of the User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets the hashed password of user.
     * @return a String representing the hashed password of the User
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * sets the users username.
     * @param username A String representing the user's username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * sets the users password.
     * @param hashPassword A String representing the users hashed password
     */
    public void setHashPassword(final String hashPassword) {
        this.hashPassword = hashPassword;
    }

}
