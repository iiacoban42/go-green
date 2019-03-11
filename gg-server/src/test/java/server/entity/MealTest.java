package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MealTest {

    private final Meal meal = new Meal("pizza", 200);

    @Test
    public void testConstructor() {
        assertEquals(meal.getProduct(), "pizza");
        assertEquals(meal.getQuantity(), 200);
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
        assertEquals(meal.getQuantity(), 200);
    }

    @Test
    public void testSetQuantity() {
        meal.setQuantity(100);
        assertEquals(meal.getQuantity(),  100);
    }
}