package server.meal;

import org.junit.Test;
import server.entity.Meal;
import server.entity.MealList;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MealCalculatorTest {

    @Test
    public void testGetAmountCo2() {
        server.entity.Meal[] list = new server.entity.Meal[] {
                new server.entity.Meal(MealCalculator.Meal_Menu[0].getProduct(), 200),
                new server.entity.Meal(MealCalculator.Meal_Menu[1].getProduct(), 100)
        };
        MealList mealList = new MealList(Arrays.asList(list));

        assertEquals(MealCalculator.getAmountCo2(mealList), MealCalculator.Meal_Menu[0].getCo2() * 200 + MealCalculator.Meal_Menu[1].getCo2() * 100, 0.001f);
    }

    @Test
    public void testIsVegetarian() {
        server.entity.Meal[] list = new server.entity.Meal[] {
                new server.entity.Meal(MealCalculator.Meal_Menu[0].getProduct(), 200),
                new server.entity.Meal(MealCalculator.Meal_Menu[1].getProduct(), 100)
        };
        MealList mealList = new MealList(Arrays.asList(list));

        assertTrue(MealCalculator.isVegetarian(mealList));

        mealList.addMeal(new server.entity.Meal("Insects", 20));
        assertFalse(MealCalculator.isVegetarian(mealList));
    }
}