package client.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealList {


    private List<Meal> meals;

    public MealList() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealList)) return false;
        MealList mealList = (MealList) o;

        for(int i=0; i<meals.size(); i++){
            if(!mealList.getMeals().get(i).equals(this.meals.get(i)))
                return false;
        }
        return true;
    }

}
