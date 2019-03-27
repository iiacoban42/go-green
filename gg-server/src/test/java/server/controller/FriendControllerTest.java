package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entity.User;
import database.manager.ActionManager;
import database.manager.UserManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import server.entity.Friend;
import server.entity.FriendScore;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class FriendControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeClass
    public static void initTest() {
        if (UserManager.getUser("TestUser") != null) {
            UserManager.deleteUser("TestUser");
        }
        UserManager.addUser("TestUser", "hunter2", "TestUser@gmail.com");
    }

    @AfterClass
    public static void cleanTest() {
        UserManager.deleteUser("TestUser");
    }

    @Before
    public void initFriend() {
        if (UserManager.getUser("Daniel") != null) {
            UserManager.deleteUser("Daniel");
        }
        UserManager.addUser("Daniel", "hunter2", "daniel99@gmail.com");
        UserManager.addScore("Daniel", 200);
    }

    @After
    public void cleanFriend() {
        UserManager.deleteUser("Daniel");
    }

    @Test
    @WithMockUser("TestUser")
    public void getFriendTest_success() throws Exception {
        UserManager.addFriend("TestUser", "Daniel");
        FriendScore friendScore = new FriendScore("Daniel", 200);
        String expected = objectMapper.writeValueAsString(friendScore);

        mvc.perform(get("/friends/friend")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    @WithMockUser("TestUser")
    public void getFriendTest_fail() throws Exception {
        mvc.perform(get("/friends/friend")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("TestUser")
    public void addFriendTest_success() throws Exception {
        Friend friend = new Friend("Daniel");

        mvc.perform(post("/friends/add")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(friend)))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("TestUser")
    public void addFriendTest_fail() throws Exception {
        Friend friend = new Friend("Lisa");

        mvc.perform(post("/friends/add")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(friend)))
            .andExpect(status().isNotFound());
    }
}