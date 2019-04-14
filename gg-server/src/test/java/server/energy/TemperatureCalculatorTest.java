package server.energy;

import org.junit.Test;

import static org.junit.Assert.*;
import static server.energy.TemperatureCalculator.getAmountCo2;

public class TemperatureCalculatorTest {
    @Test
    public void electricityTest() {
        Temperature temp = new Temperature(56, 6000, "electricity");
            assertEquals(getAmountCo2(temp),1982600,0.01);

    }
    //8620
    @Test
    public void gasTest() {
        Temperature temp = new Temperature(56, 6000, "naturalGas");
        assertEquals(getAmountCo2(temp),1706760,0.01);
    }

    @Test
    public void oilTest() {
        Temperature temp = new Temperature(56, 6000, "heatingOil");
        assertEquals(getAmountCo2(temp),2275680,0.01);
    }

    @Test
    public void BigBillOilTest() {
        Temperature temp = new Temperature(56, 60000000, "heatingOil");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void BigBillGasTest() {
        Temperature temp = new Temperature(56, 60000000, "heatingGas");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void BigBillElectricTest() {
        Temperature temp = new Temperature(56, 60000000, "electricity");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void SystemTest() {
        Temperature temp = new Temperature(56, 6000, "");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void SystemTest2() {
        Temperature temp = new Temperature(56, 6000, "electric");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void SystemTest3() {
        Temperature temp = new Temperature(56, 6000, "oil");
        assertEquals(getAmountCo2(temp),0,0);
    }

    @Test
    public void SystemTest4() {
        Temperature temp = new Temperature(56, 6000, "gas");
        assertEquals(getAmountCo2(temp),0,0);
    }

}
