package server.entity;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MealListTest {
    private String json = "[  \n" +
            "   {  \n" +
            "      \"product\":\"lamb\",\n" +
            "      \"quantity\":300\n" +
            "   },\n" +
            "   {  \n" +
            "      \"product\":\"beans\",\n" +
            "      \"quantity\":150\n" +
            "   }\n" +
            "]";

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
        mealList.removeMeal(meal);
        list.remove(meal);
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

    @Test
    public void testToString() {
        String string = "meal: pizza 200\n";
        Meal meal = new Meal("pizza", 200);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        assertEquals(mealList.toString(), string);

    }

    @Test
    public void testSize() {
        String string = "meal: pizza 200\n";
        Meal meal = new Meal("pizza", 200);
        Meal meal2 = new Meal("burger", 100);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        mealList.addMeal(meal2);
        assertEquals(mealList.size(), 2);
    }

    @Test
    public void testJsonConverter() {
        String string = "meal: lamb 300\nbeans 150\n";
        MealList mealList = new MealList();
        mealList.jsonConverter(json);
        assertEquals(string, mealList.toString());
    }


    @Test
    public void throwsException() {
        try {
            MealList list = new MealList();
            list.jsonConverter("");

        } catch (Exception e) {
            assertEquals("string not json", e.getMessage());
        }
    }

    @Test
    public void throwsException2() {
        try {
            MealList list = new MealList();
            list.jsonConverter("meal: lamb 300\nbeans 150\n");

        } catch (Exception e) {
            assertEquals("string not json", e.getMessage());
        }
    }


    @Test
    public void testGet() {
        String string = "burger 100";
        String string2 = "pizza 200";
        Meal meal = new Meal("pizza", 200);
        Meal meal2 = new Meal("burger", 100);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        mealList.addMeal(meal2);
        assertEquals(mealList.get(0).toString(), string2);
        assertEquals(mealList.get(1).toString(), string);
    }


}