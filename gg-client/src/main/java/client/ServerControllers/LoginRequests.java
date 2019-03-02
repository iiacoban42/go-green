package client.ServerControllers;

import client.entities.LoginCredentials;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.apache.juli.logging.Log;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class LoginRequests {

    public static void sendLoginCredentials(String username, String password) throws IOException {


        String URL_Login = "http://localhost:8080/api/authentication/login";

        LoginCredentials credentials = new LoginCredentials(username, password);

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(credentials);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);


        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(URL_Login, HttpMethod.POST, entity, ResponseEntity.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("login successful");
        }


    }

}

