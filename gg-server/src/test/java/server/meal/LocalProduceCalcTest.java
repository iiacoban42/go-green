package server.meal;

import org.junit.Test;
import server.entity.MealList;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LocalProduceCalcTest {
    @Test
    public void testGetAmountCo2() {
        server.entity.Meal[] list = new server.entity.Meal[] {
                new server.entity.Meal(LocalProduceCalc.products[0].getProduct(), 200),
                new server.entity.Meal(LocalProduceCalc.products[1].getProduct(), 100)
        };
        MealList mealList = new MealList(Arrays.asList(list));
        assertEquals(LocalProduceCalc.getAmountCo2(mealList), 1470, 0.01);
    }
}
