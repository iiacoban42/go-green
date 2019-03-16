package client.requests;

import client.entities.Meal;
import client.entities.MealList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MealRequestsTest {

    @Test
    public void mealTest_successful() throws Exception {
        Meal meal = new Meal("beans" , 33);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        String response = MealRequests.sendMealList(mealList);
        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void mealTest_unsuccessful() throws Exception {
        String response = MealRequests.sendMealList(null);
    }

    @Test
    public void getScore_successful(){

        int response = MealRequests.getScore();

        assertNotEquals(response , -1);
    }

}
