package database.entity;

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
     * a String representing the users hashed password.
     */
    @Column(name = "hash_password")
    private String hashPassword;

    /**
     * a String representing the users email.
     */
    @Column(name = "email")
    private String email;

    /**
     * an Integer representing the users total carbon emission saved.
     */
    @Column(name = "total_co2_score")
    private int totalScore;

    /**
     * a String representing the username of this users friend.
     * it links to the friends primary key
     */
    @Column(name = "friend")
    private String friend;

    /**
     * a String representing the token.
     */
    @Column(name = "token")
    private String token;

    /**
     * creates a user with a totalScore of 0.
     */
    public User() {
        totalScore = 0;
    }

    /**
     * creates a user with a totalScore of 0 and all the atributes.
     * @param username primary key, string represents Users username
     * @param hashPassword string represents encoded password
     * @param email stgring represents users email
     */
    public User(String username, String hashPassword, String email) {
        this.username = username;
        this.hashPassword = hashPassword;
        this.email = email;
        totalScore = 0;
    }

    /**
     * gets username of user.
     *
     * @return a String representing the username of the User
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets hashed password of user.
     *
     * @return a String representing the hashed password of the User
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * gets email of user.
     *
     * @return a String representing the  email of user
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets email of user.
     *
     * @param email a String representing the email of the user
     */
    private void setEmail(final String email) {
        this.email = email;
    }

    /**
     * gets total score of user.
     *
     * @return a integer representing the score of the user
     */
    public int gettotalScore() {
        return totalScore;
    }

    /**
     * sets total score of user.
     *
     * @param totalScore a integer representing the total score of the user
     */
    public void settotalScore(final int totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * gets friend of the user.
     *
     * @return a String representing the username--primarykey of the user's friend
     */
    public String getFriend() {
        return friend;
    }

    /**
     * sets friend of user.
     *
     * @param friend a String representing the username--primarykey of the user's friend
     */
    public void setFriend(final String friend) {
        this.friend = friend;
    }

    /**
     * sets the users username.
     *
     * @param username A String representing the user's username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * sets the users password.
     *
     * @param hashPassword A String representing the users hashed password
     */
    public void setHashPassword(final String hashPassword) {
        this.hashPassword = hashPassword;
    }

    /**
     * returns token.
     * @return a String representing the token of the user.
     */
    public String getToken() {
        return token;
    }

    /**
     * sets token.
     * @param token a String representing the token of the latest session
     */
    public void setToken(String token) {
        this.token = token;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }


}