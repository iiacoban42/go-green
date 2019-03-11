package features;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MealTest {
    @Test
    public void mealTest() {
        Meal mealTest = new Meal("beans", 0.001, 0, true, false);
        mealTest.setQuantity(200);
        mealTest.setSelected(true);
        assertEquals("beans", mealTest.getProduct());
        assertEquals(0.001, mealTest.getCo2(), 0.0005);
        assertEquals(200, mealTest.getQuantity(), 5);
        assertTrue(mealTest.isVegetarian());
        assertTrue(mealTest.isSelected());
    }

//    @Test
//    public void mealToString() {
//        Meal mealTest = new Meal("beans", 0.001, 0, true, false);
//        String testString = "beans-0.001-0.0-vegetarian-not-selected";
//        System.out.println(testString);
//        System.out.println(mealTest);
//        assertEquals(testString, mealTest);
//    }
//
//    @Test
//    public void mealToString2() {
//        Meal mealTest = new Meal("beans", 0.001, 0, false, true);
//        String testString = "beans-0.001-0.0-not-vegetarian-is-selected";
//        System.out.println(testString);
//        System.out.println(mealTest);
//        assertEquals(testString, mealTest);
//    }

//    @Test
//    public void mealEquals() {
//        Meal meal1 = new Meal("beans", 0.001, 0, true, false);
//        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
//        assertEquals(meal1, meal2);
//    }

    @Test
    public void notMeal() {
        Meal meal1 = new Meal("beans", 0.001, 0, true, false);
        String testString = "whatever";
        assertNotEquals(testString, meal1);
    }

    @Test
    public void productNotEqual() {
        Meal meal1 = new Meal("tofu", 0.001, 0, true, false);
        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void co2NotEqual() {
        Meal meal1 = new Meal("beans", 0.002, 0, true, false);
        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void quantityNotEqual() {
        Meal meal1 = new Meal("beans", 0.001, 3, true, false);
        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void vegetarianNotEqual() {
        Meal meal1 = new Meal("beans", 0.001, 0, false, false);
        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
        assertNotEquals(meal1, meal2);
    }

    @Test
    public void selectedNotEqual() {
        Meal meal1 = new Meal("beans", 0.001, 3, true, true);
        Meal meal2 = new Meal("beans", 0.001, 0, true, false);
        assertNotEquals(meal1, meal2);
    }
}
