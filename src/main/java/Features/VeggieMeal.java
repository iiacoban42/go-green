package Features;

import java.io.IOException;
import java.util.ArrayList;

public class VeggieMeal {
    private static ArrayList<Meal> veggieMeal;

    public VeggieMeal() {
        veggieMeal = new ArrayList<Meal>();
        veggieMeal.add(new Meal("beans", 0.001, 0, true, false));
        veggieMeal.add(new Meal("veggieBurger", 0.0026, 0, true, false));
        veggieMeal.add(new Meal("insects", 0.0027, 0, false, false));
        veggieMeal.add(new Meal("quorn", 0.0027, 0, true, false));
        veggieMeal.add(new Meal("nuts", 0.0032, 0, true, false));
        veggieMeal.add(new Meal("tofu", 0.0035, 0, true, false));
        veggieMeal.add(new Meal("egg", 0.216, 0, true, false));
        veggieMeal.add(new Meal("beefCroquette", 0.0052, 0, false, false));
        veggieMeal.add(new Meal("veggieBurgerCheese", 0.0065, 0, true, false));
        veggieMeal.add(new Meal("chicken", 0.0068, 0, false, false));
        veggieMeal.add(new Meal("pork", 0.0070, 0, false, false));
        veggieMeal.add(new Meal("cheese", 0.0100, 0, true, false));
        veggieMeal.add(new Meal("mixedMincedMeat", 0.0133, 0, false, false));
        veggieMeal.add(new Meal("hamburger", 0.0168, 0, false, false));
        veggieMeal.add(new Meal("mincedMeat", 0.0194, 0, false, false));
        veggieMeal.add(new Meal("steak", 0.0340, 0, false, false));
        veggieMeal.add(new Meal("lamb", 0.0510, 0, false, false));
    }

    public static Meal get(int i) {
        try {
            if (i > veggieMeal.size()) {
                throw new IOException();
            }
            return veggieMeal.get(i);
        } catch (IOException e) {
            System.out.println("To be retrieved item cannot have an index int bigger or equal to the size of the ArrayList Veggiemeal");
            return null;
        }

    }

<<<<<<< HEAD
    public static double calculator() {
=======
    public void setVeggieMeal(String ingredient, double quantity) {
        for (Meal meal : veggieMeal) {
            if (meal.getProduct().equals(ingredient)) {
                meal.setQuantity(quantity);
                meal.setSelected(true);

            }
        }
    }

    public double calculator() {
>>>>>>> 76608381316267bf0ae4be3d1b862c2093d379d1
        double calculator = 0;
        for (Meal meal : veggieMeal) {
            if (meal.isSelected()) {
                calculator += meal.getCo2() * meal.getQuantity();
            }
        }
        return calculator;
    }

    public boolean isVegetarian() {
        boolean result = true;
        for (Meal meal : veggieMeal) {
            if (meal.isSelected() && !meal.isVegetarian()) {
                result = false;
            }
        }
        return result;
    }


    public String toString() {
        String string = "meal ingredients: ";
        for (Meal meal : veggieMeal) {
            if (meal.isSelected()) {
                string += meal.getProduct() + " ";
            }
        }

        return string += "\ntotal Co2: " + calculator();
    }
}
