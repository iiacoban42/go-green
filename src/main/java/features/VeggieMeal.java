package features;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VeggieMeal {
    private ArrayList<Meal> veggieMeal;
    private final double averageCo2 = 1;

    /**
     * Constructor of the menu containing all the options a user can select.
     * https://www.milieucentraal.nl/milieubewust-eten/vlees-vis-of-vega/
     */
    public VeggieMeal() {
        this.veggieMeal = new ArrayList();
        this.veggieMeal.add(new Meal("beans", 0.001, 0, true, false));
        this.veggieMeal.add(new Meal("veggieBurger", 0.0026, 0, true, false));
        this.veggieMeal.add(new Meal("insects", 0.0027, 0, false, false));
        this.veggieMeal.add(new Meal("quorn", 0.0027, 0, true, false));
        this.veggieMeal.add(new Meal("nuts", 0.0032, 0, true, false));
        this.veggieMeal.add(new Meal("tofu", 0.0035, 0, true, false));
        this.veggieMeal.add(new Meal("egg", 0.216, 0, true, false));
        this.veggieMeal.add(new Meal("beefCroquette", 0.0052, 0, false, false));
        this.veggieMeal.add(new Meal("veggieBurgerCheese", 0.0065, 0, true, false));
        this.veggieMeal.add(new Meal("chicken", 0.0068, 0, false, false));
        this.veggieMeal.add(new Meal("pork", 0.0070, 0, false, false));
        this.veggieMeal.add(new Meal("cheese", 0.0100, 0, true, false));
        this.veggieMeal.add(new Meal("mixedMincedMeat", 0.0133, 0, false, false));
        this.veggieMeal.add(new Meal("hamburger", 0.0168, 0, false, false));
        this.veggieMeal.add(new Meal("mincedMeat", 0.0194, 0, false, false));
        this.veggieMeal.add(new Meal("steak", 0.0340, 0, false, false));
        this.veggieMeal.add(new Meal("lamb", 0.0510, 0, false, false));
    }

    public ArrayList<Meal> getVeggieMeal() {
        VeggieMeal newVM = new VeggieMeal();
        return newVM.veggieMeal;
    }


    /**
     * Return a meal at a given index.
     * @param index inside the veggieMeal.
     * @return meal at the given index.
     */

    public Meal get(int index) {
        try {
            if (index > veggieMeal.size()) {
                throw new IOException();
            }
            return veggieMeal.get(index);
        } catch (IOException e) {
            String error = "To be retrieved item cannot have an index int bigger ";
            error += "or equal to the size of the ArrayList VeggieMeal";
            System.out.println(error);
            return null;
        }

    }

    public double jsonConverter(String json) throws IOException {
        VeggieMeal newVM = new VeggieMeal();

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Meal> jsonList= objectMapper.readValue(json, new TypeReference<ArrayList<Meal>>(){});

        for (int i = 0; i < jsonList.size(); i++) {
            String product = jsonList.get(i).getProduct();
            double quantity = jsonList.get(i).getQuantity();
            newVM.setVeggieMeal(product, quantity);
        }

        double calculatedCo2 = newVM.calculator();
        double dutchAverageMeal = dutchAverageMeal();
        double jsonConverter = dutchAverageMeal - calculatedCo2;

        if (jsonConverter > 0) {
            return jsonConverter;
        } else {
            return 0;
        }
    }

    /**
     * Sets a the quantity of a product selected by the user.
     * @param ingredient selected by user.
     * @param quantity selected by user.
     */

    public void setVeggieMeal(String ingredient, double quantity) {
        for (int i = 0; i < this.getVeggieMeal().size(); i++) {
            if (ingredient.equals(this.get(i).getProduct())) {
                this.get(i).setQuantity(quantity);
                this.get(i).setSelected(true);
            }
        }
    }

    /**
     * Calculate total amount of Co2 per meal.
     * @return Co2 consumed by the meal.
     */

    public double calculator() {
        double calculator = 0;
        for (Meal meal : veggieMeal) {
            if (meal.isSelected()) {
                calculator += meal.getCo2() * meal.getQuantity();
            }
        }
        return calculator;
    }

    /**
     * Checks if the user had a vegetarian meal.
     * @return true if the meal selected by the user is vegetarian.
     */

    public boolean isVegetarian() {
        boolean result = true;
        for (Meal meal : veggieMeal) {
            if (meal.isSelected() && !meal.isVegetarian()) {
                result = false;
            }
        }
        return result;
    }

    /**
     * https://www.rivm.nl/bibliotheek/rapporten/2016-0200.pdf
     * https://www.allesopeenrij.nl/cultuur-2/eten-drinken/hoeveel-en-wat-drinkt-de-nederlander-2/
     * http://library.wur.nl/WebQuery/wurpubs/fulltext/464580
     * Calculates the amount/CO2 produced by the average dutch meal.
     * @return CO2
     */
    public static double dutchAverageMeal() {
        VeggieMeal averageDutchMeal = new VeggieMeal();
        double avgPork = (37.2 + 37.4 + 37.6 + 37.8 + 37.7 + 37.7 + 37.7 + 37.3 + 37.1 + 36.7 + 36.6 + 36.5 + 36.5)*1000/(13*365);
        double avgChicken = (20.7 + 20.8 + 21.5 + 21.6 + 22.5 + 22.5 + 22.1 + 22.0 + 22.3 + 22.5 + 22.1 + 22.2 + 22.1)*1000/(13*365);
        double avgCow = (15.9 + 16.1 + 16.1 + 16.1 + 16.3 + 16.2 + 15.9 + 15.7 + 15.6 + 15.5 + 15.4 + 15.4 + 15.4)*1000/(13*365);
        double avgLamb = (1.0 + 1.0 + 1.0 + 1.1 + 1.1 + 1.1 + 1.1 + 1.1 + 1.2 + 1.2 + 1.2 + 1.2 + 1.2)*1000/(13*365);
        double avgCheese = 355 - (60/365);

        averageDutchMeal.setVeggieMeal("beans", 4);
        averageDutchMeal.setVeggieMeal("nuts", 122);
        averageDutchMeal.setVeggieMeal("egg", 12);
        averageDutchMeal.setVeggieMeal("chicken", avgChicken);
        averageDutchMeal.setVeggieMeal("pork", avgPork);
        averageDutchMeal.setVeggieMeal("cheese", avgCheese);
        averageDutchMeal.setVeggieMeal("hamburger", avgCow);
        averageDutchMeal.setVeggieMeal("lamb", avgLamb);

    return averageDutchMeal.calculator();
    }


//    /**
//     * String representation of the meal the user selected.
//     * @return ingredients of the meal and total Co2 consumed.
//     */

//    @Override
//    public String toString() {
//        String string = "meal ingredients: ";
//        for (Meal meal : this.veggieMeal) {
//            if (meal.isSelected()) {
//                string += meal.getProduct() + " ";
//            }
//        }
//        string += "\ntotal Co2: " + calculator() + " g";
//        return string;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        boolean result= true;
//        if (other instanceof VeggieMeal) {
//            VeggieMeal otherVMeal = (VeggieMeal) other;
//            if (this.getVeggieMeal().size() == otherVMeal.getVeggieMeal().size()) {
//                for (int i = 0; i < otherVMeal.getVeggieMeal().size(); i++) {
//                    if (!this.getVeggieMeal().contains(otherVMeal.get(i))) {
//                        result = false;
//                    }
//                }
//
//                return result;
//            }
//        }
//        return false;
//    }

}
