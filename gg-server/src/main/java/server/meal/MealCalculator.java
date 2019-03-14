package server.meal;

import server.entity.MealList;

public class MealCalculator {

    public static final Meal[] Meal_Menu = {
        new Meal("beans", 0.001, 0, true),
        new Meal("VeggieBurger", 0.0026, 0, true),
        new Meal("Insects", 0.0027, 0, false),
        new Meal("Quorn", 0.0027, 0, true),
        new Meal("Nuts", 0.0032, 0, true),
        new Meal("Tofu", 0.0035, 0, true),
        new Meal("Egg", 0.216, 0, true),
        new Meal("BeefCroquette", 0.0052, 0, false),
        new Meal("VeggieBurgerCheese", 0.0065, 0, true),
        new Meal("Chicken", 0.0068, 0, false),
        new Meal("Pork", 0.0070, 0, false),
        new Meal("Cheese", 0.0100, 0, true),
        new Meal("MixedMincedMeat", 0.0133, 0, false),
        new Meal("Hamburger", 0.0168, 0, false),
        new Meal("MincedMeat", 0.0194, 0, false),
        new Meal("Steak", 0.0340, 0, false),
        new Meal("Lamb", 0.0510, 0, false)
    };

    /**
     * Returns total amount of average co2 produced for making the Meals.
     *
     * @param mealList for which to calculate it
     * @return double
     */
    public static double getAmountCo2(MealList mealList) {
        double co2 = 0;
        double averageDutchMeal = dutchAverageMeal();
        double result;

        for (server.entity.Meal listMeal : mealList.getMeals()) {
            for (Meal menuMeal : Meal_Menu) {
                if (menuMeal.getProduct().equals(listMeal.getProduct())) {
                    co2 += menuMeal.getCo2() * listMeal.getQuantity();
                }
            }
        }
        result = averageDutchMeal - co2;
        if (result < 0) {
            return 0;
        }
        return result;

    }

    /**
     * Checks if the user had a vegetarian meal.
     *
     * @return true if the meal selected by the user is vegetarian.
     */
    public static boolean isVegetarian(MealList mealList) {
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

    /**
     * https://www.rivm.nl/bibliotheek/rapporten/2016-0200.pdf
     * https://www.allesopeenrij.nl/cultuur-2/eten-drinken/hoeveel-en-wat-drinkt-de-nederlander-2/
     * http://library.wur.nl/WebQuery/wurpubs/fulltext/464580
     * Calculates the amount/CO2 produced by the average dutch meal.
     * @return CO2
     */
    public static double dutchAverageMeal() {
        double avgBeans = 4;
        double avgNuts = 122;
        double avgEgg = 12;
        double avgPork = (37.2 + 37.4 + 37.6 + 37.8 + 37.7 + 37.7 + 37.7 + 37.3 + 37.1 + 36.7 + 36.6 + 36.5 + 36.5)*1000/(13*365);
        double avgChicken = (20.7 + 20.8 + 21.5 + 21.6 + 22.5 + 22.5 + 22.1 + 22.0 + 22.3 + 22.5 + 22.1 + 22.2 + 22.1)*1000/(13*365);
        double avgCow = (15.9 + 16.1 + 16.1 + 16.1 + 16.3 + 16.2 + 15.9 + 15.7 + 15.6 + 15.5 + 15.4 + 15.4 + 15.4)*1000/(13*365);
        double avgLamb = (1.0 + 1.0 + 1.0 + 1.1 + 1.1 + 1.1 + 1.1 + 1.1 + 1.2 + 1.2 + 1.2 + 1.2 + 1.2)*1000/(13*365);
        double avgCheese = 355 - (60/365)*1000;

        return 0.001 * avgBeans + 0.0032 * avgNuts + 0.0036 * avgEgg + 0.0068 * avgChicken + 0.007 * avgPork + 0.01 * avgCheese + 0.0168 * avgCow + 0.051 * avgLamb;
    }


}
