package server.meal;

import server.entity.MealList;

public class LocalProduceCalc {
    public static final Meal[] products = {
        new Meal("fruits", 1.1, true),
        new Meal("vegetables", 2, true),
        new Meal("milk", 1.9, true),
        new Meal("cheese", 10, true),
        new Meal("nuts", 3.2, true),
        new Meal("egg", 216, true),
        new Meal("chicken", 6.8, false),
        new Meal("pork", 7, false),
        new Meal("lamb", 51, false)

    };

    /**
     * Returns total amount of co2 saved for the products bought.
     *
     * @param mealList for which to calculate it
     * @return double
     */
    public static double getAmountCo2(MealList mealList) {
        double co2 = server.meal.MealCalculator.co2(mealList, products);
        return 0.7 * co2;

    }
}
