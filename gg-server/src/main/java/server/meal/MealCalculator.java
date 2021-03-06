package server.meal;

import server.entity.MealList;

public class MealCalculator {

    public static final Meal[] Meal_Menu = {
        new Meal("beans", 1, true),
        new Meal("veggieBurger", 2.6, true),
        new Meal("veggieBurgerCheese", 6.5, true),
        new Meal("quorn", 2.7, true),
        new Meal("nuts", 3.2, true),
        new Meal("tofu", 3.5, true),
        new Meal("egg", 216, true),
        new Meal("beefCroquette", 5.2, false),
        new Meal("chicken", 6.8, false),
        new Meal("pork", 7, false),
        new Meal("steak", 34, false),
        new Meal("lamb", 51, false),
        new Meal("hamburger", 16.8, false),
        new Meal("mincedMeat", 19.4, false),
        new Meal("mixedMincedMeat", 13.3, false),
        new Meal("insects", 2.7, false),
        new Meal("cheese", 10, true)
    };

    /**
     * Returns total amount of average co2 produced for making the Meals.
     *
     * @param mealList for which to calculate it
     * @return double
     */
    public static double getAmountCo2(MealList mealList) {
        double co2 = co2(mealList,Meal_Menu);
        double averageDutchMeal = dutchAverageMeal();
        double result;
        result = averageDutchMeal - co2;
        if (result < 0) {
            return 0;
        }
        return result;
    }

    /**
     * Calculates the co2 of a meal.
     * @param meal for co2 calculation
     * @return co2 consumed by the meal
     */
    public static double co2(MealList meal, Meal[] menu) {
        double co2 = 0;
        for (server.entity.Meal listMeal : meal.getMeals()) {
            for (Meal menuMeal : menu) {
                if (menuMeal.getProduct().equals(listMeal.getProduct())) {
                    co2 += menuMeal.getCo2() * listMeal.getQuantity();
                }
            }
        }
        return co2;
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


        return avgBeans + 3.2 * avgNuts + 3.6 * avgEgg
                + 6.8 * avgChicken + 7 * avgPork + 10 * avgCheese
                + 16.8 * avgCow + 51 * avgLamb;
    }


}
