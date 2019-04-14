package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.manager.ActionManager;
import database.manager.BadgeManager;
import database.manager.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import server.entity.BadgeList;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Badges.class)
public class BadgesTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void initTest() {
        if (UserManager.getUser("TestUser") != null) {
            UserManager.deleteUser("TestUser");
        }
        UserManager.addUser("TestUser", "hunter2", "TestUser@gmail.com");
    }

    @After
    public void cleanTest() {
        // Have to delete all actions in case there are any.
        List actions = ActionManager.listActionsUser("TestUser");

        for (Object actionObj : actions) {
            database.entity.Action action = (database.entity.Action) actionObj;
            ActionManager.deleteAction(action.getId());
        }

        // Have to delete all badges in case there are any.
        List badges = BadgeManager.listBadgesUser("TestUser");

        for (Object badgeObj : badges) {
            database.entity.Badge badge = (database.entity.Badge) badgeObj;
            BadgeManager.deleteBadge(badge.getId());
        }

        UserManager.deleteUser("TestUser");
    }

    @Test
    @WithMockUser("TestUser")
    public void getAllBadges() throws Exception {
        BadgeList badgeList = new BadgeList();

        long id = BadgeManager.addBadge("badge", "TestUser");
        badgeList.addBadge(BadgeManager.getBadge(id));
        id = BadgeManager.addBadge("badge2", "TestUser");
        badgeList.addBadge(BadgeManager.getBadge(id));


        String expected = objectMapper.writeValueAsString(badgeList);

        mvc.perform(get("/badges")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(expected));
    }
}