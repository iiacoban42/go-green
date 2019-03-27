package client.requests;

import client.entities.LoginCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;



public class LoginRequests  {





    /**
     * Sends the username and password to the server in json format using a post request.
     * @param username of client
     * @param password of client
     * @throws RestClientResponseException if something went wrong
     */
    public static String sendLoginCredentials(String username,
                                              String password) throws RestClientResponseException {

        String urlLogin = "http://localhost:8080/api/users/login";

        LoginCredentials credentials = new LoginCredentials(username, password);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(credentials);
        } catch (JsonProcessingException e) {
            System.out.println("json prob");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
            urlLogin,
            HttpMethod.POST,
            entity,
            ResponseEntity.class
        );

        String tokenResponse = response.getHeaders().get( "Authorization").get(0);
        Token token = new Token(tokenResponse);
        Session.setToken(token);

        return response.getStatusCode().toString();
    }

}

