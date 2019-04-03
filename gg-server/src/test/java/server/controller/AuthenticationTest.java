package server.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.manager.UserManager;
import org.apache.catalina.core.ApplicationContext;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import server.entity.LoginCredentials;
import server.entity.RegisterCredentials;
import server.security.CreateJwt;
import server.security.WebSecurityConfig;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Authentication.class)
@ContextConfiguration
public class AuthenticationTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void createTestUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (UserManager.getUser("Test") != null) {
            UserManager.deleteUser("Test");
        }
        UserManager.addUser("Test", encoder.encode("pass"), "email");
    }

    @Test
    public void loginTest_Success() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "pass");

        mvc.perform(post("/users/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk());
    }

    @Test
    public void wrongUserName() throws Exception {
        LoginCredentials credentials = new LoginCredentials("henk", "pass");

        mvc.perform(post("/users/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void wrongPassWord() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "notpass");

        mvc.perform(post("/users/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void registerTest_Success() throws Exception{
        RegisterCredentials credentials = new RegisterCredentials("mail", "Test1", "pass");

        if (UserManager.getUser("Test1") != null) {
            UserManager.deleteUser("Test1");
        }

        mvc.perform(post("/users/register")
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

        mvc.perform(post("/users/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void changePasswordTest_Forbidden() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "password");

        mvc.perform(post("/users/changePassword")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void changePasswordTest_Success() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "pass");

        MvcResult mvcResult = mvc.perform(post("/users/login")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk()).andReturn();

        String token = mvcResult.getResponse().getHeader("Authorization");
        System.out.println("token: " + token);

        LoginCredentials newCredentials = new LoginCredentials("Test", "password");

        mvc.perform(post("/users/changePassword")
            .header("Authorization", token)
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(newCredentials))
        ).andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest_No_Bearer() throws Exception {
        // Remove "Bearer " for extra branch coverage of the AuthorizationFilter
        String token = "This is not a valid token";
        System.out.println("token: " + token);

        mvc.perform(post("/users/deleteUser")
            .header("Authorization", token)
            .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void deleteUserTest_Wrong_Token() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "pass");

        MvcResult mvcResult = mvc.perform(post("/users/login")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk()).andReturn();

        // Remove "Bearer " for extra branch coverage of the AuthorizationFilter
        String token = CreateJwt.createJwt("Test");

        mvc.perform(post("/users/deleteUser")
            .header("Authorization", token)
            .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isForbidden());
    }

    @Test
    public void deleteUserTest_Success() throws Exception {
        LoginCredentials credentials = new LoginCredentials("Test", "pass");

        MvcResult mvcResult = mvc.perform(post("/users/login")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(credentials))
        ).andExpect(status().isOk()).andReturn();

        String token = mvcResult.getResponse().getHeader("Authorization");

        mvc.perform(post("/users/deleteUser")
            .header("Authorization", token)
            .contentType(APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk());
    }

    @AfterClass
    public static void deleteTestUser() {
        if (UserManager.getUser("Test") != null) {
            UserManager.deleteUser("Test");
        }
    }
}