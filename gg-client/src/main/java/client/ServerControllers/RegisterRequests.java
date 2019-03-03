package client.ServerControllers;

import client.entities.RegisterCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RegisterRequests {


    public static String sendRegisterCredentials(String username, String password, String email) throws Exception {
        String URL_Register = "http://localhost:8080/api/authentication/register";

        RegisterCredentials credentials = new RegisterCredentials(email , username, password);

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

        System.out.println(response.getStatusCode().toString());
         return  response.getStatusCode().toString();


    }
}