package Features;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MealTest {
    @Test
    public void mealTest() {
    Meal mealTest = new Meal(null, 0, 0, false);
    mealTest.setProduct("beans");
    mealTest.setCo2(0.001);
    mealTest.setQuantity(200);
    mealTest.setSelected(true);
    assertEquals("beans", mealTest.getProduct());
    assertEquals(0.001, mealTest.getCo2(), 0.0005);
    assertEquals(200, mealTest.getQuantity(), 5);
    assertEquals(true, mealTest.isSelected());
    }

    @Test
    public void veggieMealTest() {
        VeggieMeal.get(1).setQuantity(350);
        VeggieMeal.get(1).setSelected(true);
        VeggieMeal.get(5).setQuantity(100);
        VeggieMeal.get(5).setSelected(true);
        VeggieMeal.get(6).setQuantity(1);
        VeggieMeal.get(6).setSelected(true);
        assertEquals(VeggieMeal.calculator());
    }
}
