package server;

import org.junit.Assert;
import org.junit.Test;
import server.meal.Meal;
import server.meal.MealCalculator;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MealTest {
    @Test
    public void mealTest() {
        server.meal.Meal mealTest = new server.meal.Meal("beans", 0.001, true);
        assertEquals("beans", mealTest.getProduct());
        assertEquals(0.001, mealTest.getCo2(), 0.0005);
        assertTrue(mealTest.isVegetarian());

    }
    
    @Test
    public void notMeal() {
        server.meal.Meal meal1 = new server.meal.Meal("beans", 0.001, true);
        String testString = "whatever";
        assertNotEquals(testString, meal1);
    }

    @Test
    public void productNotEqual() {
        server.meal.Meal meal1 = new server.meal.Meal("tofu", 0.001, true);
        server.meal.Meal meal2 = new server.meal.Meal("beans", 0.001, true);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void co2NotEqual() {
        server.meal.Meal meal1 = new server.meal.Meal("beans", 0.002, true);
        server.meal.Meal meal2 = new server.meal.Meal("beans", 0.001, true);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void vegetarianNotEqual() {
        server.meal.Meal meal1 = new server.meal.Meal("beans", 0.001, false);
        server.meal.Meal meal2 = new server.meal.Meal("beans", 0.001, true);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void dutchAverageMeal() {
<<<<<<< HEAD
        Assert.assertEquals(6000, MealCalculator.dutchAverageMeal(), 10);
=======
        Assert.assertEquals(6000, MealCalculator.dutchAverageMeal(), 7);
>>>>>>> b7fc630f78221149385add8f3436989963a1b45c
    }
}
