package Features;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MealTest {
    @Test
    public void mealTest() {
    Meal mealTest = new Meal("beans", 0.001, 0, true,false);
    mealTest.setQuantity(200);
    mealTest.setSelected(true);
    assertEquals("beans", mealTest.getProduct());
    assertEquals(0.001, mealTest.getCo2(), 0.0005);
    assertEquals(200, mealTest.getQuantity(), 5);
    assertTrue(mealTest.isVegetarian());
    assertTrue(mealTest.isSelected());
    }

    @Test
    public void veggieMealTest() {
        VeggieMeal.get(1).setQuantity(350);
        VeggieMeal.get(1).setSelected(true);
        VeggieMeal.get(5).setQuantity(100);
        VeggieMeal.get(5).setSelected(true);
        VeggieMeal.get(7).setQuantity(1);
        VeggieMeal.get(7).setSelected(true);
        assertEquals(1.446, VeggieMeal.calculator(), 0.0005);
    }
}
