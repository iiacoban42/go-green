package server.entity;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class MealListTest {
    private Meal meal = new Meal("lamb",300);
    private Meal meal2 = new Meal("chicken",200);
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
    public void testJsonConverter2() {
        MealList list = new MealList();
        list.addMeal(new Meal("lamb",300));
        list.addMeal(new Meal("beans",150));
        MealList mealList = new MealList();
        mealList.jsonConverter(json);
        assertEquals(true, mealList.equals(list));
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
        Meal meal = new Meal("pizza", 200);
        Meal meal2 = new Meal("burger", 100);
        MealList mealList = new MealList();
        mealList.addMeal(meal);
        mealList.addMeal(meal2);
        assertTrue(mealList.get(0).equals(meal));
        assertTrue(mealList.get(1).equals(meal2));
    }

    @Test
    public void testEquals(){
        MealList list1 = new MealList();
        MealList list2 = new MealList();
        list1.addMeal(meal);
        list1.addMeal(meal2);
        list2.addMeal(meal);
        list2.addMeal(meal2);
        assertTrue(list1.equals(list2));
    }

    @Test
    public void testEquals2(){
        MealList list1 = new MealList();
        MealList list2 = new MealList();
        list1.addMeal(meal2);
        list1.addMeal(meal);
        list2.addMeal(meal);
        list2.addMeal(meal2);
        assertFalse(list1.equals(list2));
    }

    @Test
    public void testEquals3(){
        MealList list1 = new MealList();
        list1.addMeal(meal);
        list1.addMeal(meal2);
        assertTrue(list1.equals(list1));
    }

    @Test
    public void testEquals4(){
        MealList list1 = new MealList();
        list1.addMeal(meal);
        assertFalse(list1.equals(meal));
    }

}