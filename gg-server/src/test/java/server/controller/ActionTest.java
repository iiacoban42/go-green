package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.manager.ActionManager;
import database.manager.UserManager;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import server.energy.Temperature;
import server.energy.TemperatureCalculator;
import server.entity.*;
import server.meal.MealCalculator;
import server.transportation.TransportationCalculator;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static server.energy.TemperatureCalculator.getAmountCo2;

@RunWith(SpringRunner.class)
@WebMvcTest(Action.class)
public class ActionTest {

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
        List actions = ActionManager.listActionsUser("TestUser");

        for (Object actionObj : actions) {
            database.entity.Action action = (database.entity.Action)actionObj;
            ActionManager.deleteAction(action.getId());
        }

        UserManager.deleteUser("TestUser");
    }

    /*@Before
    @After
    @WithMockUser("admin")
    public void deleteActions() {
        List actions = ActionManager.listActionsUser("admin");

        for (Object actionObj : actions) {
            database.entity.Action action = (database.entity.Action)actionObj;
            ActionManager.deleteAction(action.getId());
        }
    }*/

    @Test
    @WithMockUser("TestUser")
    public void listActionsTest() throws Exception {
        ActionManager.addAction("meal", "TestUser", 5);
        ActionManager.addAction("meal", "TestUser", 20);

        String expected = objectMapper.writeValueAsString(ActionManager.listActionsUser("TestUser"));

        mvc.perform(get("/action/manage/actions")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    @WithMockUser("TestUser")
    public void removeActionTest() throws Exception {
        ActionManager.addAction("meal", "TestUser", 5);

        String expected = objectMapper.writeValueAsString(ActionManager.listActionsUser("TestUser"));

        long id = ActionManager.addAction("meal", "TestUser", 20);

        mvc.perform(get("/action/manage/remove").param("id", String.valueOf(id))
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(expected));
        assertEquals(5, UserManager.getUser("TestUser").gettotalScore());
    }

    @Test
    @WithMockUser("TestUser")
    public void mealTest() throws Exception {
        MealList mealList = new MealList();
        mealList.addMeal(new Meal("VeggieBurger", 200));

        int score = (int) MealCalculator.getAmountCo2(mealList);
        int oldTotalScore = UserManager.getUser("TestUser").gettotalScore();

        mvc.perform(post("/action/meal")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(mealList))
        ).andExpect(status().isOk());

        int newTotalScore = UserManager.getUser("TestUser").gettotalScore();
        assertEquals(score, newTotalScore - oldTotalScore);
    }

    @Test
    @WithMockUser("TestUser")
    public void transportTest() throws Exception {
        TransportList transportList = new TransportList();
        transportList.addTransport(new Transport("bus", 100));

        int score = (int)TransportationCalculator.getAmountCo2(transportList);
        int oldTotalScore = UserManager.getUser("TestUser").gettotalScore();

        mvc.perform(post("/action/transport")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(transportList))
        ).andExpect(status().isOk());

        int newTotalScore = UserManager.getUser("TestUser").gettotalScore();
        assertEquals(score, newTotalScore - oldTotalScore);
    }

    @Test
    @WithMockUser("TestUser")
    public void temperatureTest() throws Exception {
        Temperature temp = new Temperature(56, 6000, "electricity");

        int score = (int) TemperatureCalculator.getAmountCo2(temp);
        int oldTotalScore = UserManager.getUser("TestUser").gettotalScore();

        mvc.perform(post("/action/temperature")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(temp))
        ).andExpect(status().isOk());

        int newTotalScore = UserManager.getUser("TestUser").gettotalScore();
        assertEquals(score, newTotalScore - oldTotalScore);
    }

    @Test
    @WithMockUser("TestUser")
    public void scoreTest() throws Exception {
        Score score = new Score();
        score.setTotalScore(UserManager.getUser("TestUser").gettotalScore());

        mvc.perform(get("/action/score")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(score)));
    }
}