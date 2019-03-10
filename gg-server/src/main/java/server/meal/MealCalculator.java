package server.meal;

import server.entity.MealList;

public class MealCalculator {

    private static final Meal[] Meal_Menu = {
        new Meal("beans", 0.001, 0, true, false),
        new Meal("VeggieBurger", 0.0026, 0, true, false),
        new Meal("Insects", 0.0027, 0, false, false),
        new Meal("Quorn", 0.0027, 0, true, false),
        new Meal("Nuts", 0.0032, 0, true, false),
        new Meal("Tofu", 0.0035, 0, true, false),
        new Meal("Egg", 0.216, 0, true, false),
        new Meal("BeefCroquette", 0.0052, 0, false, false),
        new Meal("VeggieBurgerCheese", 0.0065, 0, true, false),
        new Meal("Chicken", 0.0068, 0, false, false),
        new Meal("Pork", 0.0070, 0, false, false),
        new Meal("Cheese", 0.0100, 0, true, false),
        new Meal("MixedMincedMeat", 0.0133, 0, false, false),
        new Meal("Hamburger", 0.0168, 0, false, false),
        new Meal("MincedMeat", 0.0194, 0, false, false),
        new Meal("Steak", 0.0340, 0, false, false),
        new Meal("Lamb", 0.0510, 0, false, false)
    };

    /**
     * Returns total amount of average co2 produced for making the Meals.
     *
     * @param mealList for which to calculate it
     * @return double
     */
    public static double getAmountCo2(MealList mealList) {
        float result = 0.0f;

        for (server.entity.Meal listMeal : mealList.getMeals()) {
            for (Meal menuMeal : Meal_Menu) {
                if (menuMeal.getProduct().equals(listMeal.getProduct())) {
                    result += menuMeal.getCo2();
                }
            }
        }

        return result;
    }

    /**
     * Checks if the user had a vegetarian meal.
     *
     * @return true if the meal selected by the user is vegetarian.
     */
    public boolean isVegetarian(MealList mealList) {
        for (server.entity.Meal listMeal : mealList.getMeals()) {
            for (Meal menuMeal : Meal_Menu) {
                if (menuMeal.getProduct().equals(listMeal.getProduct())) {
                    if (!menuMeal.isVegetarian()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
