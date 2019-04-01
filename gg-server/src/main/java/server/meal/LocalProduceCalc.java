package server.meal;

import server.entity.MealList;

import static server.meal.MealCalculator.Co2;

public class LocalProduceCalc {
    public static final Meal[] products = {
            new Meal("cheese", 10, true),
            new Meal("beans", 1, true),
            new Meal("nuts", 3.2, true),
            new Meal("tofu", 3.5, true),
            new Meal("egg", 216, true),
            new Meal("chicken", 6.8, false),
            new Meal("pork", 7, false),
            new Meal("lamb", 51, false)

    };

    public static double getAmountCo2(MealList mealList) {
        double co2 = Co2(mealList);
        double result;
        result = 0.7 * co2;
        return result;

    }


}
