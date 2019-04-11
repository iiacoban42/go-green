package server.meal;

import org.junit.Test;
import server.entity.Transport;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class MealTest {

    private  Meal meal = new Meal("pizza", 0.4, false);
    private  Meal meal2 = new Meal("salad", 0.3, true);

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
    public void testGetProduct2() {
        assertNotEquals(meal.getProduct(), "salad");
    }

    @Test
    public void testGetCo2() {
        assertEquals(meal.getCo2(), 0.4, 0.001);
    }
    @Test
    public void test2GetCo2() {
        assertNotEquals(meal2.getCo2(), 0.4, 0.001);
    }

    @Test
    public void testIsVegetarian() {
        assertFalse(meal.isVegetarian());
    }
    @Test
    public void testIsVegetarian2() {
        assertTrue(meal2.isVegetarian());
    }

    @Test
    public void testEquals() {
        Meal meal2 = new Meal("burger", 0.4, false);
        assertFalse(meal2.equals(meal));
    }
    @Test
    public void testEquals2() {
        Meal meal2 = new Meal("pizza", 0.4, false);
        assertTrue(meal2.equals(meal));
    }

    @Test
    public void testEquals3() {
        Meal meal2 = new Meal("pizza", 0.1, false);
        assertFalse(meal2.equals(meal));
    }
    @Test
    public void testEquals4() {
        Meal meal2 = new Meal("pizza", 0.4, true);
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