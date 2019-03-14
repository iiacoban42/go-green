package client.requests;

import client.entities.MealList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class MealRequests {

    /**
     * Sends Meal List to the server.
     * @param mealList list with meals
     * @return responseEntity message from the server
     * @throws IOException input output exception.
     */
    public static String sendMealList(MealList mealList) throws IOException {

        String urlLogin = "http://localhost:8080/api/action/meal";

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(mealList);

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
            System.out.println("Meal send successfully");
        }
        return response.getStatusCode().toString();
    }
}