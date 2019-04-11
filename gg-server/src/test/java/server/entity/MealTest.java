package server.entity;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class MealTest {

    private Meal meal = new Meal("pizza", 200);
    private Meal meal2 = new Meal();

    @Test
    public void testConstructor() {
        assertEquals(meal.getProduct(), "pizza");
        assertEquals(meal.getQuantity(), 200);
    }

    @Test
    public void testToString() {
        String string = "pizza 200";
        assertEquals(string, meal.toString());

    }

    @Test
    public void testDefaultConstructor() {
        assertNull(meal2.getProduct());
        assertEquals(0, meal2.getQuantity());

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
        assertEquals(meal.getQuantity(), 100);
    }

    @Test
    public void testEquals() {
        Meal meal2 = new Meal("burger", 200);
        assertFalse(meal2.equals(meal));
    }
    @Test
    public void testEquals2() {
        Meal meal2 = new Meal("pizza", 200);
        assertTrue(meal2.equals(meal));
    }

    @Test
    public void testEquals4() {
        Meal meal2 = new Meal("pizza", 4);
        assertFalse(meal2.equals(meal));
    }

    @Test
    public void testEquals5() {
        assertTrue(meal.equals(meal));
    }

    @Test
    public void testEquals6() {
        Transport meal2 = new Transport("pizza", 0.4);
        assertFalse(meal.equals(meal2));
    }

}