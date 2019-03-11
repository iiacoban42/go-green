package server.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealList {
    private List<server.entity.Meal> meals;

    public MealList() {
        this.meals = new ArrayList<>();
    }

    public MealList(List<server.entity.Meal> meals) {
        this.meals = new ArrayList<>(meals);
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public void removeMeal(Meal meal) {
        meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public int size() {
        return meals.size();
    }

    public Meal get(int index) {
        return meals.get(index);
    }

    /**
     * Creates a string representation of the object.
     *
     * @return string representation of the object
     */
    public String toString() {
        String result = "meal: ";
        for (Meal meal : meals) {
            result += meal.getProduct() + " " + meal.getQuantity() + "\n";
        }
        return result;
    }


    /**
     * Parses json strings into a MealList.
     *
     * @param json string translated into a list of meals
     * @throws IOException if something goes wrong
     */
    public void jsonConverter(String json) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            meals = objectMapper.readValue(json, new TypeReference<List<server.entity.Meal>>() {
            });

        } catch (IOException e) {
            System.out.println("exception");
        }

    }


}
