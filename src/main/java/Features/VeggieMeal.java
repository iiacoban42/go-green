package Features;

import java.util.ArrayList;

public class VeggieMeal {
    private ArrayList<Meal> veggieMeal;

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

    public Meal get(int i) {
        return veggieMeal.get(i);
    }

    public void setVeggieMeal(String ingredient, double quantity) {
        for (Meal meal : veggieMeal) {
            if (meal.getProduct().equals(ingredient)) {
                meal.setQuantity(quantity);
                meal.setSelected(true);

            }
        }
    }

    public double calculator() {
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
