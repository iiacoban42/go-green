package client.requests;

import client.entities.Friend;
import client.entities.FriendScore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class FriendRequests {

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
    public static String addFriendRequest(String name) throws RestClientResponseException {

        String urlAddFriend = "http://localhost:8080/api/friends/add";

        Friend friend = new Friend(name);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(friend);
        }   catch (JsonProcessingException e) {
            System.out.println("json prob");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
                  urlAddFriend,
                  HttpMethod.POST,
                  entity,
                  ResponseEntity.class
          );

        return response.getStatusCode().toString();
    }


    /**
     * Get Score and name of Friend.
     * @return server's answer.
     */
    public String getScoreAdNameOfFriend() throws RestClientResponseException {

        String url = "http://localhost:8080/api/friends/friend";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FriendScore> response
                = restTemplate.getForEntity(url, FriendScore.class);

        friendScore = response.getBody();

        return response.getStatusCode().toString();


    }

}
