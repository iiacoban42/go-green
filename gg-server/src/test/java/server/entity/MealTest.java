package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MealTest {

    private final Meal meal = new Meal("pizza", 0.4f);

    @Test
    public void testConstructor() {
        assertEquals(meal.getProduct(), "pizza");
        assertEquals(meal.getQuantity(), 0.4f, 0.0f);
    }

    @Test
    public void testGetProduct() {
        assertEquals(meal.getProduct(), "pizza");
    }

    @Test
    public void testSetProduct() {
        meal.setProduct("burger");
        assertEquals(meal.getProduct(), "burger");
    }

    @Test
    public void testGetQuantity() {
        assertEquals(meal.getQuantity(), 0.4f, 0.0f);
    }

    @Test
    public void testSetQuantity() {
        meal.setQuantity(0.12f);
        assertEquals(meal.getQuantity(), 0.12f, 0.0f);
    }
}