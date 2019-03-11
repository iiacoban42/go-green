package server.meal;

import org.junit.Test;

import static org.junit.Assert.*;

public class MealTest {

    private final Meal meal = new Meal("pizza", 0.4f, 200, false);

    @Test
    public void testConstructor() {
        assertEquals(meal.getProduct(), "pizza");
        assertEquals(meal.getQuantity(), 200);
        assertEquals(meal.getCo2(), 0.4f, 0.01f);
    }

    @Test
    public void testGetProduct() {
        assertEquals(meal.getProduct(), "pizza");
    }

    @Test
    public void testGetQuantity() {
        assertEquals(meal.getQuantity(), 200);
    }

    @Test
    public void testSetQuantity() {
        meal.setQuantity(100);
        assertEquals(meal.getQuantity(), 100);
    }

    @Test
    public void testGetCo2() {
        assertEquals(meal.getCo2(), 0.4f, 0.001f);
    }

    @Test
    public void testIsVegetarian() {
        assertFalse(meal.isVegetarian());
    }
}