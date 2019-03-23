package client.requests;

import client.entities.TransportList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class TransportRequests {


    /**
     * Sends transport List to server.
     * @param transportList transport List
     * @return server response.
     * @throws IOException if something goes wrong in the communication with the server.
     */
    public static String sendTransportList(TransportList transportList) throws IOException {

        String urlTransport = "http://localhost:8080/api/action/transport";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(transportList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
                urlTransport,
                HttpMethod.POST,
                entity,
                ResponseEntity.class
        );

        return response.getStatusCode().toString();
    }

}
