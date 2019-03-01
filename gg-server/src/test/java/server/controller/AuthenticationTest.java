package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(Authentication.class)
public class AuthenticationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginTest_Success() throws Exception {
        LoginCredentials credentials = new LoginCredentials("admin", "pass");

        mvc.perform(post("/authentication/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk());
    }

    @Test
    public void loginTest_Fail() throws Exception {
        LoginCredentials credentials = new LoginCredentials("user", "pass");

        mvc.perform(post("/authentication/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void registerTest_Success() throws Exception{
        RegisterCredentials credentials = new RegisterCredentials("mail", "user", "pass");

        mvc.perform(post("/authentication/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk());
    }

    @Test
    public void registerTest_Fail() throws Exception{
        RegisterCredentials credentials = new RegisterCredentials("mail", "admin", "pass");

        mvc.perform(post("/authentication/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
        //mvc.perform(post("/authentication/register").contentType(APPLICATION_JSON_UTF8).content("{\"test\":\"test\"")).andExpect(status().isOk());
    }
}