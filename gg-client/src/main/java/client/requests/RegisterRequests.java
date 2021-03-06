package client.requests;

import client.entities.RegisterCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class RegisterRequests {

    /**
     * Sends the username and password to the server in json format using a post request.
     * @param username of client
     * @param password of client
     * @param email of client
     * @throws JsonProcessingException if something went wrong
     */
    public static String sendRegisterCredentials(
        String username,
        String password,
        String email
    ) throws RestClientResponseException, JsonProcessingException {
        String urlRegister = "http://localhost:8080/api/users/register";

        RegisterCredentials credentials = new RegisterCredentials(email, username, password);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        json = objectMapper.writeValueAsString(credentials);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
            urlRegister,
            HttpMethod.POST,
            entity,
            ResponseEntity.class
        );

        return response.getStatusCode().toString();
    }
}