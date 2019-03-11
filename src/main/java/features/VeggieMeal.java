package features;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class VeggieMeal {
    private ArrayList<Meal> veggieMeal;

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

    /**
     *
     * @param json
     * @return
     * @throws IOException
     */
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
     * @return
     */
    public static double dutchAverageMeal() {
        ArrayList<Meal> averageDutchMeal = new ArrayList();
        averageDutchMeal.add(new Meal("beans", 0.001, 4, true, false));
        averageDutchMeal.add(new Meal("veggieBurger", 0.0026, 0, true, false));
        averageDutchMeal.add(new Meal("insects", 0.0027, 0, false, false));
        averageDutchMeal.add(new Meal("quorn", 0.0027, 0, true, false));
        averageDutchMeal.add(new Meal("nuts", 0.0032, 0, true, false));
        averageDutchMeal.add(new Meal("tofu", 0.0035, 0, true, false));
        averageDutchMeal.add(new Meal("egg", 0.0036, 12, true, false));
        averageDutchMeal.add(new Meal("beefCroquette", 0.0052, 0, false, false));
        averageDutchMeal.add(new Meal("veggieBurgerCheese", 0.0065, 0, true, false));
        averageDutchMeal.add(new Meal("chicken", 0.0068, 0, false, false));
        averageDutchMeal.add(new Meal("pork", 0.0070, 0, false, false));
        averageDutchMeal.add(new Meal("cheese", 0.0100, 0, true, false));
        averageDutchMeal.add(new Meal("mixedMincedMeat", 0.0133, 0, false, false));
        averageDutchMeal.add(new Meal("hamburger", 0.0168, 0, false, false));
        averageDutchMeal.add(new Meal("mincedMeat", 0.0194, 0, false, false));
        averageDutchMeal.add(new Meal("steak", 0.0340, 0, false, false));
        averageDutchMeal.add(new Meal("lamb", 0.0510, 0, false, false));
    return 0;
    }


}
