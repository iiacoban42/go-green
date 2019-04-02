package client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GeneralRequests {

    /**
     * Post request to server.
     * @param object to send.
     * @param url target url.
     * @return string response of server.
     * @throws JsonProcessingException if something goes wrong with object mapper.
     */
    public static String doPostRequest(Object object, String url) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(object);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", Session.getToken().getToken());

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ResponseEntity.class
        );

        return  response.getStatusCode().toString();
    }

    /**
    *Do get request.
    * @param url target url
    * @return object returned from the server , response status from server.
    */
    public static Pair<String , Object> doGetRequest(String url ,  Class classObject) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", Session.getToken().getToken());
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(
               url,
               HttpMethod.GET,
               entity,
              classObject
        );

        Pair<String , Object> pair =
                new Pair<>(response.getStatusCode().toString() , response.getBody());
        return  pair;
    }

}
