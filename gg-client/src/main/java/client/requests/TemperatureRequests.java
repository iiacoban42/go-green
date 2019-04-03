package client.requests;

import client.entities.Temperature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class TemperatureRequests {

    /**
     * Send home Temperature.
     * @param temperature home temperature.
     * @return servers' response.
     * @throws IOException if something goes wrong.
     */
    public static String sendTemperature(Temperature temperature) throws IOException {

        String urlLogin = "http://localhost:8080/api/action/temperature";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(temperature);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", Session.getToken().getToken());

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
                urlLogin,
                HttpMethod.POST,
                entity,
                ResponseEntity.class
        );


        return response.getStatusCode().toString();
    }


}
