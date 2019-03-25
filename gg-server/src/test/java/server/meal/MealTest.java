package server.meal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MealTest {

    private  Meal meal = new Meal("pizza", 0.4, false);

    @Test
    public void testConstructor() {
        assertEquals(meal.getProduct(), "pizza");
        assertEquals(meal.getCo2(), 0.4, 0.01);
    }

    @Test
    public void testGetProduct() {
        assertEquals(meal.getProduct(), "pizza");
    }

    @Test
    public void testGetCo2() {
        assertEquals(meal.getCo2(), 0.4, 0.001);
    }

    @Test
    public void testIsVegetarian() {
        assertFalse(meal.isVegetarian());
    }
}