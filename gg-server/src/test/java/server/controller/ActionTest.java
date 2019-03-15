package server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.manager.UserManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import server.entity.Meal;
import server.entity.MealList;
import server.entity.Score;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Action.class)
public class ActionTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void mealTest() throws Exception {
        MealList mealList = new MealList();
        mealList.addMeal(new Meal("VeggieBurger", 200));

        mvc.perform(post("/action/meal")
            .contentType(APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(mealList))
        ).andExpect(status().isOk());
    }

    @Test
    public void scoreTest() throws Exception {
        Score score = new Score();
        score.setTotalScore(UserManager.getUser("admin").gettotalScore());

        mvc.perform(get("/action/score")
            .contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(score)));
    }
}