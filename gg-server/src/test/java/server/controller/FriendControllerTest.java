package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entity.User;
import database.manager.ActionManager;
import database.manager.UserManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

    @Before
    public void initTests() {
        UserManager.addUser("Daniel", "hunter2", "daniel99@gmail.com");
        UserManager.addScore("Daniel", 200);
    }

    @After
    public void cleanTests() {
        UserManager.deleteUser("Daniel");
    }

    @Test
    public void getFriendTest_success() throws Exception {
        UserManager.addFriend("admin", "Daniel");
        FriendScore friendScore = new FriendScore("Daniel", 200);
        String expected = objectMapper.writeValueAsString(friendScore);

        mvc.perform(get("/friends/friend")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    public void getFriendTest_fail() throws Exception {
        mvc.perform(get("/friends/friend")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isNotFound());
    }

    @Test
    public void addFriendTest_success() throws Exception {
        Friend friend = new Friend("Daniel");

        mvc.perform(post("/friends/add")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(friend)))
            .andExpect(status().isOk());
    }

    @Test
    public void addFriendTest_fail() throws Exception {
        Friend friend = new Friend("Lisa");

        mvc.perform(post("/friends/add")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(friend)))
            .andExpect(status().isNotFound());
    }
}