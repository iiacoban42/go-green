package client.entities;

import java.util.ArrayList;
import java.util.List;

public class MealList {


    private List<Meal> meals;

    public MealList() {
        this.meals = new ArrayList<>();
    }

    public MealList(List<Meal> meals) {
        this.meals = new ArrayList<>(meals);
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

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MealList)) {
            return false;
        }

        MealList mealList = (MealList) obj;

        for (int i = 0; i < meals.size(); i++) {
            if (!mealList.getMeals().get(i).equals(this.meals.get(i))) {
                return false;
            }
        }
        return true;
    }

}
