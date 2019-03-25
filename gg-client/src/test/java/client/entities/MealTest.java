package client.entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MealTest {

    private Meal meal = new Meal("beans", 33);


    @Test

    public void testGetProduct() {

        assertEquals("beans", meal.getProduct());
    }

    @Test
    public void testGetQuantity() {

        assertEquals(33, meal.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        meal.setQuantity(55);

        assertEquals(55, meal.getQuantity());

    }

    @Test
    public void testSetProduct() {
        meal.setProduct("chicken");
        assertEquals("chicken", meal.getProduct());
    }

    @Test
    public void testEquals() {
        Score score = new Score();
        score.setTotalScore(10);
        assertNotEquals(score, meal);
        Meal test = new Meal("test", 100);
        assertNotEquals(test, meal);
        test.setProduct("beans");
        assertNotEquals(test, meal);
        test.setQuantity(30);
        test.setProduct("test");
        assertNotEquals(test, meal);
        assertNotEquals(null , meal);
    }



}
