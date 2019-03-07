package Features;

import java.util.ArrayList;

public class VeggieMeal {
    private ArrayList<Meal> meal;

    public VeggieMeal(ArrayList<Meal> meal) {
        meal = new ArrayList<Meal>();
    }

    public static ArrayList<Meal> menu() {
        ArrayList<Meal> menu = new ArrayList<Meal>();
        menu.add(new Meal("beans", 0.001,0,false));
        menu.add(new Meal("veggie burger", 0.0026,0,false));
        menu.add(new Meal("insects", 0.0027,0,false));
        menu.add(new Meal("quorn", 0.0027,0,false));
        menu.add(new Meal("nuts", 0.0032,0,false));
        menu.add(new Meal("tofu", 0.0035,0,false));
        menu.add(new Meal("egg", 0.216,0,false));
        menu.add(new Meal("beef croquette", 0.0052,0,false));
        menu.add(new Meal("veggie burger + cheese", 0.0065,0,false));
        menu.add(new Meal("chicken", 0.0068,0,false));
        menu.add(new Meal("pork", 0.0070,0,false));
        menu.add(new Meal("cheese", 0.0100,0,false));
        menu.add(new Meal("mixed minced meat", 0.0133,0,false));
        menu.add(new Meal("hamburger", 0.0168,0,false));
        menu.add(new Meal("minced meat", 0.0194,0,false));
        menu.add(new Meal("steak", 0.0340,0,false));
        menu.add(new Meal("lamb", 0.0510,0,false));

        return menu;
    }

    public static double calculator(ArrayList<Meal> selection) {
        double calculator = 0;
        for (Meal mealUser: selection) {
                if (mealUser.isSelected()) {
                    calculator += mealUser.getCo2() * mealUser.getQuantity();
                }
            }
        return calculator;
    }

    public static void main(String[] args) {
        ArrayList<Meal> test = new ArrayList<Meal>(menu());
        test.get(16).setSelected(true);
        test.get(16).setQuantity(1000);
        System.out.println(calculator(test));

    }
}
