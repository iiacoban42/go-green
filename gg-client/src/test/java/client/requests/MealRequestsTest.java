package client.requests;

import client.entities.Meal;
import client.entities.MealList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static client.requests.LoginRequests.sendLoginCredentials;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MealRequestsTest {

    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }

    @Test
    public void mealTest_successful() throws Exception {
        Meal meal = new Meal("beans" , 33);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        String response = MealRequests.sendMealList(mealList , "http://localhost:8080/api/action/meal");
        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void mealTest_unsuccessful() throws Exception {
     MealRequests.sendMealList(null, "http://localhost:8080/api/action/meal");
    }

    @Test
    public void getScore_successful(){
        int response = MealRequests.getScore();

        assertNotEquals(response , -1);
    }

}
