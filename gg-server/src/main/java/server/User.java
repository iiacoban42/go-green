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
     *  a String representing the username of this users freind
     *  it links to the freinds primary key
     */
    @Column (name = "freind")
    private String freind;

    /**
     * creates a user with a tScore of 0.
     */
    public User() {
        tScore = 0;
    }

    /**
     * gets username of user.
     * @return a String representing the username of the User
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets hashed password of user.
     * @return a String representing the hashed password of the User
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * gets email of user.
     * @return a String representing the  email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email of user.
     * @param email a String representing the email of the user
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * gets total score of user.
     * @return a integer representing the score of the user
     */
    public int gettScore() {
        return tScore;
    }

    /**
     * sets total score of user.
     * @param tScore a integer representing the total score of the user
     */
    public void settScore(final int tScore) {
        this.tScore = tScore;
    }


    /**
     * gets freind of the user
     * @return a String representing the username--primarykey of the user's freind
     */
    public String getFreind() {
        return freind;
    }


    /**
     * sets freind of user
     * @param freind a String representing the username--primarykey of the user's freind
     */
    public void setFreind(final String freind) {
        this.freind = freind;
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
