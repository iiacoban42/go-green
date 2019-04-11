package server.meal;

import org.junit.Test;

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

}