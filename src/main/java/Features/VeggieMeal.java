package Features;

import java.util.ArrayList;

public class VeggieMeal {
    private String name;
    private int weight;
    private double co2;


    public VeggieMeal(String name, int weight, float co2) {
        this.name = name;
        this.weight = weight;
        this.co2 = co2;

    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getCo2() {
        return co2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public static ArrayList<Meal> menu() {
        ArrayList<Meal> menu = new ArrayList<Meal>();
        menu.add(new Meal("beans", 0.001));
        menu.add(new Meal("veggie burger", 0.0026));
        menu.add(new Meal("insects", 0.0027));
        menu.add(new Meal("quorn", 0.0027));
        menu.add(new Meal("nuts", 0.0032));
        menu.add(new Meal("tofu", 0.0035));
        menu.add(new Meal("egg", 0.216));
        menu.add(new Meal("beef croquette", 0.0052));
        menu.add(new Meal("veggie burger + cheese", 0.0065));
        menu.add(new Meal("chicken", 0.0068));
        menu.add(new Meal("pork", 0.0070));
        menu.add(new Meal("cheese", 0.0100));
        menu.add(new Meal("mixed minced meat", 0.0133));
        menu.add(new Meal("hamburger", 0.0168));
        menu.add(new Meal("minced meat", 0.0194));
        menu.add(new Meal("steak", 0.0340));
        menu.add(new Meal("lamb", 0.0510));

        return menu;
    }

    public static double calculator(ArrayList<Meal> selection) {
        double calculator = 0;
        for (Meal mealUser: selection) {
            for (Meal mealMenu: menu()) {
                if (mealUser.getProduct().equals(mealMenu.getProduct())) {
                    calculator += mealMenu.getCo2() * mealUser.getCo2();
                }
            }
        }
        return calculator;
    }

}
