package client.ServerControllers;

import client.entities.LoginCredentials;
import client.entities.RegisterCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RegisterRequests {


    public static void sendRegisterCredentials(String username, String password, String email) throws JsonProcessingException {
        String URL_Register = "http://localhost:8080/api/authentication/register";

        RegisterCredentials credentials = new RegisterCredentials(username, password, email);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(credentials);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(URL_Register, HttpMethod.POST, entity, ResponseEntity.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Register successful");

        }


    }
}