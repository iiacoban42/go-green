package client.requests;

import client.entities.Friend;
import client.entities.FriendScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;
import org.springframework.web.client.RestClientResponseException;

import java.io.IOException;


public class FriendRequests extends GeneralRequests {

    private FriendScore friendScore;

    public FriendRequests() {
        this.friendScore = new FriendScore();
    }

    public FriendRequests(FriendScore friendScore) {
        this.friendScore = friendScore;
    }

    public void setFriendScore(FriendScore friendScore) {
        this.friendScore = friendScore;
    }

    public FriendScore getFriendScore() {
        return friendScore;
    }

    /**
     * Send friend name.
     * @param name of the friend.
     * @return http message frm the server.
     * @throws IOException if something goes wrong.
     */
    public static String addFriendRequest(String name)
            throws RestClientResponseException, JsonProcessingException {

        final String urlAddFriend = "http://localhost:8080/api/friends/add";

        Friend friend = new Friend(name);

        String  response =  doPostRequest(friend , urlAddFriend);

        return response;
    }


    /**
     * Get Score and name of Friend.
     * @return server's answer.
     */
    public String getScoreAdNameOfFriend() throws RestClientResponseException {

        String url = "http://localhost:8080/api/friends/friend";

        Pair pair =  doGetRequest(url , friendScore.getClass());

        friendScore = (FriendScore) pair.getValue();

        return (String) pair.getKey();
    }

}
