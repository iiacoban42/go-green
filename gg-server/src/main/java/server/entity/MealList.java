package server.entity;

import java.util.ArrayList;
import java.util.List;

public class MealList {
    private List<Meal> meals;

    public  MealList() {
        this.meals = new ArrayList<Meal>();
    }

    public MealList(List<Meal> meals) {
        this.meals = new ArrayList<Meal>(meals);
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    public Meal removeMeal(Meal meal) {
        meals.remove(meal);
        return meal;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
