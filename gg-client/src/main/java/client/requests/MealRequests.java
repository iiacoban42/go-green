package client.requests;

import client.entities.MealList;
import client.entities.Score;
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

    /**
     * Request score from the server.
     * @return responseEntity message from the server.
     */
    public static int getScore() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/action/score";

        ResponseEntity<Score> response
                = restTemplate.getForEntity(url  , Score.class);

        Score score = response.getBody();
        System.out.println(String.valueOf(score.getTotalScore()));

        if (response.getStatusCode() == HttpStatus.OK) {
            return score.getTotalScore();
        }

        return -1;
    }
}