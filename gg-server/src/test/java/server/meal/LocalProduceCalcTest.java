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

        assertEquals(LocalProduceCalc.getAmountCo2(mealList), 29.4 , 0.01);
    }

    @Test
    public void testCo2() {
        server.entity.Meal[] list = new server.entity.Meal[] {
                new server.entity.Meal(LocalProduceCalc.products[0].getProduct(), 200),
                new server.entity.Meal(LocalProduceCalc.products[1].getProduct(), 100)
        };
        MealList mealList = new MealList(Arrays.asList(list));

        assertEquals(MealCalculator.co2(mealList, LocalProduceCalc.products), 420, 0.01);
    }
}
