package client.requests;

import client.entities.LoginCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class LoginRequests {

    /**
     * Sends the username and password to the server in json format using a post request.
     * @param username of client
     * @param password of client
     * @throws IOException if something went wrong
     */
    public static String sendLoginCredentials(String username, String password) throws IOException {

        String urlLogin = "http://localhost:8080/api/authentication/login";

        LoginCredentials credentials = new LoginCredentials(username, password);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(credentials);

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

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Register successful");

        }
        return response.getStatusCode().toString();
    }

}

