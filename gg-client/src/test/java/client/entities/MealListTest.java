package client.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class MealListTest {


    private  Meal  meal = new Meal("beans" , 33);
    private  Meal  meal2 = new Meal("chicken" , 33);

    private MealList mealList = new MealList();


    @Test
    public void testAddMeal(){

        List<Meal> test = new ArrayList<Meal>();
        test.add(meal);
        mealList.addMeal(meal);

       assertEquals(test , mealList.getMeals());
       assertEquals(test.size() , mealList.getMeals().size());

    }

    @Test
    public void testMealListConstructor2(){
        List<Meal> test2 = new ArrayList<Meal>();
        test2.add(meal);
        MealList mealList2 = new MealList(test2);
        mealList.addMeal(meal);

        assertEquals(mealList , mealList2);


    }

    @Test
    public void testRemoveMeal(){

        mealList.addMeal(meal);
        assertEquals(1 , mealList.getMeals().size());

        mealList.removeMeal(meal);
        assertEquals(0 , mealList.getMeals().size());
    }

    @Test
    public void testRemoveMeal_TwoSameMeals(){

        mealList.addMeal(meal);
        mealList.addMeal(meal);

        assertEquals(2 , mealList.getMeals().size());

        mealList.removeMeal(meal);
        assertEquals(1, mealList.getMeals().size());
    }


    @Test
    public void testSetMeals(){

        assertEquals(0 ,  mealList.getMeals().size());

        List<Meal> test3 = new ArrayList<Meal>();
        test3.add(meal);
        test3.add(meal);

        mealList.setMeals(test3);

        assertEquals(2 ,mealList.getMeals().size() );
        assertEquals(test3 , mealList.getMeals());
    }

    @Test
    public void testEquals_successful(){
        mealList.addMeal(meal);
        List<Meal> test = new ArrayList<Meal>();
        test.add(meal);
        MealList mealList2 = new MealList(test);

        assertEquals(mealList2 , mealList);


    }

    @Test
    public void testEquals_withNull(){

        assertFalse(mealList.equals(null));

    }

    @Test
    public void testEquals_withItself(){
        assertTrue(mealList.equals(mealList));
    }

    @Test
    public void testEquals_differentObjects(){

        assertFalse(mealList.equals(meal));
    }

    @Test
    public void testEquals_differentMeals(){
        MealList mealList2  = new MealList();
        mealList2.addMeal(meal2);
        mealList.addMeal(meal);
        assertFalse(mealList.equals(mealList2));
    }
}
