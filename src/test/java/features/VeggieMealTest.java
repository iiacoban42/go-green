package features;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VeggieMealTest {
    @Test
    public void veggieMealConstructor() {
        VeggieMeal newVM = new VeggieMeal();
        ArrayList<Meal> veggieMeal = new ArrayList();
        veggieMeal.add(new Meal("beans", 0.001, 0, true, false));
        veggieMeal.add(new Meal("veggieBurger", 0.0026, 0, true, false));
        veggieMeal.add(new Meal("insects", 0.0027, 0, false, false));
        veggieMeal.add(new Meal("quorn", 0.0027, 0, true, false));
        veggieMeal.add(new Meal("nuts", 0.0032, 0, true, false));
        veggieMeal.add(new Meal("tofu", 0.0035, 0, true, false));
        veggieMeal.add(new Meal("egg", 0.216, 0, true, false));
        veggieMeal.add(new Meal("beefCroquette", 0.0052, 0, false, false));
        veggieMeal.add(new Meal("veggieBurgerCheese", 0.0065, 0, true, false));
        veggieMeal.add(new Meal("chicken", 0.0068, 0, false, false));
        veggieMeal.add(new Meal("pork", 0.0070, 0, false, false));
        veggieMeal.add(new Meal("cheese", 0.0100, 0, true, false));
        veggieMeal.add(new Meal("mixedMincedMeat", 0.0133, 0, false, false));
        veggieMeal.add(new Meal("hamburger", 0.0168, 0, false, false));
        veggieMeal.add(new Meal("mincedMeat", 0.0194, 0, false, false));
        veggieMeal.add(new Meal("steak", 0.0340, 0, false, false));
        veggieMeal.add(new Meal("lamb", 0.0510, 0, false, false));

        boolean equals = false;
        if (newVM.getVeggieMeal().size() == veggieMeal.size()) {
            for (int i = 0; i < veggieMeal.size(); i++) {
                if (!veggieMeal.contains(newVM.get(i))) {
                    assertFalse(equals);
                }
            }
            equals = true;
        }
        assertTrue(equals);

    }
//    @Test
//    public void equalsTest() {
//        VeggieMeal newVM = new VeggieMeal();
//        VeggieMeal vmTest = new VeggieMeal();
//        assertTrue(newVM.equals(vmTest));
//    }

    @Test
    public void getOutOfIndex() {
        VeggieMeal newVM = new VeggieMeal();
        assertNull(newVM.get(20));
    }

    @Test
    public void setVeggieMealTest() {
        VeggieMeal newVM = new VeggieMeal();

        newVM.setVeggieMeal("veggieBurger", 350.0);
        assertEquals(350.0, newVM.get(1).getQuantity(), 1);
        assertTrue(newVM.get(1).isSelected());
    }

    @Test
    public void zeroCalculatorTest() {
        VeggieMeal newVM = new VeggieMeal();
        assertEquals(0, newVM.calculator(),0.01);
    }

    @Test
    public void calculatorTest() {
        VeggieMeal newVM = new VeggieMeal();
        newVM.setVeggieMeal("veggieBurger", 350.0);
        assertEquals(350*0.0026, newVM.calculator(),0.01);
    }

    @Test
    public void veggieMealTest() {
        VeggieMeal newVM = new VeggieMeal();
        newVM.setVeggieMeal("veggieBurger", 350);
        newVM.setVeggieMeal("tofu", 100);
        newVM.setVeggieMeal("egg", 1);
        assertEquals((350 * 0.0026 + 100 * 0.0035 + 0.216), newVM.calculator(), 0.0005);
    }

    @Test
    public void isVegetarianTest() {
        VeggieMeal newVM = new VeggieMeal();
        newVM.setVeggieMeal("veggieBurger", 350);
        newVM.setVeggieMeal("tofu", 100);
        newVM.setVeggieMeal("egg", 1);
        assertTrue(newVM.isVegetarian());
    }

    @Test
    public void isNotVegetarianTest() {
        VeggieMeal newVM = new VeggieMeal();
        newVM.setVeggieMeal("veggieBurger", 350);
        newVM.setVeggieMeal("tofu", 100);
        newVM.setVeggieMeal("lamb", 250);
        assertFalse(newVM.isVegetarian());
    }

    @Test
    public void dutchAverageMeal() {
        assertEquals(8.54, VeggieMeal.dutchAverageMeal(), 0.01);
    }
}

