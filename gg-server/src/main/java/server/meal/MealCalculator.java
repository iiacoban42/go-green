package server.meal;

import server.entity.MealList;

public class MealCalculator {

    public static final Meal[] Meal_Menu = {
        new Meal("beans", 0.001, true),
        new Meal("veggieBurger", 0.0026, true),
        new Meal("veggieBurgerCheese", 0.0065, true),
        new Meal("quorn", 0.0027, true),
        new Meal("nuts", 0.0032, true),
        new Meal("tofu", 0.0035, true),
        new Meal("egg", 0.216, true),
        new Meal("beefCroquette", 0.0052, false),
        new Meal("chicken", 0.0068, false),
        new Meal("pork", 0.0070, false),
        new Meal("steak", 0.0340, false),
        new Meal("lamb", 0.0510, false),
        new Meal("hamburger", 0.0168, false),
        new Meal("mincedMeat", 0.0194, false),
        new Meal("mixedMincedMeat", 0.0133, false),
        new Meal("insects", 0.0027, false),
        new Meal("cheese", 0.0100, true)
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
     *
     * @return CO2
     */
    public static double dutchAverageMeal() {
        double avgBeans = 4;
        double avgNuts = 122;
        double avgEgg = 12;
        double avgCheese = 355 - (60 / 365) * 1000;
        double avgPork = (37.2 + 37.4 + 37.6 + 37.8 + 37.7 + 37.7
                + 37.7 + 37.3 + 37.1 + 36.7 + 36.6 + 36.5 + 36.5) * 1000 / (13 * 365);

        double avgChicken = (20.7 + 20.8 + 21.5 + 21.6 + 22.5
                + 22.5 + 22.1 + 22.0 + 22.3 + 22.5 + 22.1 + 22.2 + 22.1) * 1000 / (13 * 365);

        double avgCow = (15.9 + 16.1 + 16.1 + 16.1 + 16.3
                + 16.2 + 15.9 + 15.7 + 15.6 + 15.5 + 15.4 + 15.4 + 15.4) * 1000 / (13 * 365);

        double avgLamb = (1.0 + 1.0 + 1.0 + 1.1 + 1.1 + 1.1 + 1.1
                + 1.1 + 1.2 + 1.2 + 1.2 + 1.2 + 1.2) * 1000 / (13 * 365);


        return 0.001 * avgBeans + 0.0032 * avgNuts + 0.0036 * avgEgg
                + 0.0068 * avgChicken + 0.007 * avgPork + 0.01 * avgCheese
                + 0.0168 * avgCow + 0.051 * avgLamb;
    }


}