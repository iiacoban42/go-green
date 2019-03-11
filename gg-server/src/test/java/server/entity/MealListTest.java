package server.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MealListTest {

    @Test
    public void testAddMeal() {
        Meal meal = new Meal("pizza", 200);
        List<Meal> list = new ArrayList<>();
        list.add(meal);

        MealList mealList = new MealList(list);
        assertEquals(mealList.getMeals(), list);
    }

    @Test
    public void testRemoveMeal() {
        Meal meal = new Meal("pizza", 200);
        List<Meal> list = new ArrayList<>();
        list.add(meal);
        list.add(new Meal("burger", 100));

        MealList mealList = new MealList(list);
        mealList.removeMeal(new Meal("burger", 100));
        assertEquals(mealList.getMeals(), list);
    }

    @Test
    public void testGetMeals() {
        Meal meal = new Meal("pizza", 200);
        List<Meal> list = new ArrayList<>();
        list.add(meal);

        MealList mealList = new MealList(list);
        assertEquals(mealList.getMeals(), list);
    }

    @Test
    public void testSetMeals() {
        Meal meal = new Meal("pizza", 200);
        List<Meal> list = new ArrayList<>();
        list.add(meal);

        MealList mealList = new MealList();
        mealList.setMeals(list);
        assertEquals(mealList.getMeals(), list);
    }
}