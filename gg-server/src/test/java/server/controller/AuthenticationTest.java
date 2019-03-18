package server.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.manager.UserManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import server.entity.LoginCredentials;
import server.entity.RegisterCredentials;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Authentication.class)
public class AuthenticationTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeClass
    public void createTestUser() {
        if (UserManager.getUser("Test") != null) {
            if (!UserManager.getUser("Test").getHashPassword().equals("pass")) {
                UserManager.changePassword("Test", "pass");
            }

            UserManager.addUser("Test", "pass", "email");
        }
    }

    @Test
    public void loginTest_Success() throws Exception {
        createTestUser();
        LoginCredentials credentials = new LoginCredentials("Test", "pass");

        mvc.perform(post("/authentication/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk());

        deleteTestUser();
    }

    @Test
    public void wrongUserName() throws Exception {
        createTestUser();
        LoginCredentials credentials = new LoginCredentials("user", "pass");

        mvc.perform(post("/authentication/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());

        deleteTestUser();
    }

    @Test
    public void wrongPassWord() throws Exception {
        createTestUser();
        LoginCredentials credentials = new LoginCredentials("Test", "1");

        mvc.perform(post("/authentication/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());

        deleteTestUser();
    }

    @Test
    public void registerTest_Success() throws Exception{
        assertNull(UserManager.getUser("Test1"));
        RegisterCredentials credentials = new RegisterCredentials("mail", "Test1", "pass");

        mvc.perform(post("/authentication/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk());
        assertNotNull(UserManager.getUser("Test1"));
        UserManager.deleteUser("Test1");
        assertNull(UserManager.getUser("Test1"));
    }

    @Test
    public void registerTest_Fail() throws Exception{
        RegisterCredentials credentials = new RegisterCredentials("mail", "Test", "pass");

        mvc.perform(post("/authentication/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
    }

    @AfterClass
    public void deleteTestUser() {
        if (UserManager.getUser("Test") != null) {
            UserManager.deleteUser("Test");
        }
    }
}