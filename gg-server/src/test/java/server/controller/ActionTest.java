package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.entity.SolPanelAction;
import database.manager.ActionManager;
import database.manager.SolPanelActionManager;
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
import server.meal.LocalProduceCalc;
import server.meal.MealCalculator;
import server.transportation.TransportationCalculator;

import java.util.List;

import static org.junit.Assert.assertNull;
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
        mealList.addMeal(new Meal("chicken", 97));

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
    public void mealVeggieTest() throws Exception {
        MealList mealList = new MealList();
        mealList.addMeal(new Meal("VeggieBurger", 125));

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
    public void localProduceTest() throws Exception {
        MealList mealList = new MealList();
        mealList.addMeal(new Meal("fruits", 200));

        int score = (int) LocalProduceCalc.getAmountCo2(mealList);
        int oldTotalScore = UserManager.getUser("TestUser").gettotalScore();

        mvc.perform(post("/action/localProduce")
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
    public void transportMultipleTest() throws Exception {
        TransportList transportList = new TransportList();
        transportList.addTransport(new Transport("bus", 20));
        transportList.addTransport(new Transport("train", 40));

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
    public void transportBikeTest() throws Exception {
        TransportList transportList = new TransportList();
        transportList.addTransport(new Transport("bike", 69));

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
    public void setSolarPanelsTest() throws Exception {
        SolarPanels solarPanels = new SolarPanels(4);

        mvc.perform(post("/action/setSolarPanels")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(solarPanels))
        ).andExpect(status().isOk());

        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser("TestUser");
        assertEquals(4, solPanelAction.getNumSolarPanels());
        SolPanelActionManager.deleteSp(solPanelAction.getId());
    }

    @Test
    @WithMockUser("TestUser")
    public void setSolarPanelsChangeTest() throws Exception {
        SolarPanels solarPanels = new SolarPanels(4);

        SolPanelActionManager.createSp("TestUser", 10);

        mvc.perform(post("/action/setSolarPanels")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(solarPanels))
        ).andExpect(status().isOk());

        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser("TestUser");
        assertEquals(4, solPanelAction.getNumSolarPanels());
        SolPanelActionManager.deleteSp(solPanelAction.getId());
    }

    @Test
    @WithMockUser("TestUser")
    public void setSolarPanelsZeroTest() throws Exception {
        SolarPanels solarPanels = new SolarPanels(0);

        SolPanelActionManager.createSp("TestUser", 10);

        mvc.perform(post("/action/setSolarPanels")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(solarPanels))
        ).andExpect(status().isOk());

        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser("TestUser");
        assertNull(solPanelAction);
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

    @Test
    @WithMockUser("TestUser")
    public void solarPanelsZeroTest() throws Exception {
        SolarPanels solarPanels = new SolarPanels(0);

        mvc.perform(get("/action/solarPanels")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(solarPanels)));
    }

    @Test
    @WithMockUser("TestUser")
    public void solarPanelsNonZeroTest() throws Exception {
        SolarPanels solarPanels = new SolarPanels();

        long id = SolPanelActionManager.createSp("TestUser", 4);
        solarPanels.setamount(4);

        mvc.perform(get("/action/solarPanels")
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(solarPanels)));
        SolPanelActionManager.deleteSp(id);
    }
}