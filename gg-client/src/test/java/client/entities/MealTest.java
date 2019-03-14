package client.entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MealTest {

    private Meal meal = new Meal("beans" , 33);


    @Test

    public void testGetProduct(){

        assertEquals("beans" , meal.getProduct());
    }

    @Test
    public void testGetQuantity(){

        assertEquals(33 , meal.getQuantity());
    }

    @Test
    public  void testSetQuantity(){
        meal.setQuantity(55);

        assertEquals(55, meal.getQuantity());

    }

    @Test
    public void testSetProduct(){
        meal.setProduct("chicken");
        assertEquals("chicken" , meal.getProduct());
    }
}
