package client.requests;

import client.entities.LoginCredentials;
import client.requests.RegisterRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static client.requests.LoginRequests.sendLoginCredentials;
import static org.junit.Assert.assertEquals;


public class RegisterRequestsTest {


    @Test
    public void registerTest_successful() throws Exception {

        String response = RegisterRequests.sendRegisterCredentials("username", "pass" , "email");

        sendLoginCredentials("username" , "pass");
        String url = "http://localhost:8080/api/users/deleteUser";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", Session.getToken().getToken());


        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

          restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                ResponseEntity.class
        );

        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void registerTest_unsuccessful() throws Exception {

        String response = RegisterRequests.sendRegisterCredentials("userForTests", "admin" , "pass");
        assertEquals("401 UNAUTHORIZED", response);
    }
//
//    @After
//    public void deleteUser() throws JsonProcessingException {
//        String url = "http://localhost:8080/api/users/deleteUser";
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "";
//
//        json = objectMapper.writeValueAsString(Session.getToken());
//
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//
//        HttpEntity<String> entity = new HttpEntity<>(json, headers);
//        RestTemplate restTemplate = new RestTemplate();
//
//          restTemplate.exchange(
//                url,
//                HttpMethod.POST,
//                entity,
//                ResponseEntity.class
//        );
//
//
//    }
}

