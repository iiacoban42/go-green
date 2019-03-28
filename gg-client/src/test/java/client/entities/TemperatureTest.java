package client.entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TemperatureTest {

    @Test
    public  void testGetters(){
        Temperature temperature = new Temperature(50 , 50 , "oil");
        assertEquals(50.0 , temperature.getEnergy());
        assertEquals(50.0 , temperature.getSurface());
        assertEquals("oil" , temperature.getSystem());
    }

    @Test
    public  void testSetters(){
        Temperature temperature = new Temperature(40 , 40 , "test");

        temperature.setEnergy(50);
        assertEquals(50.0 , temperature.getEnergy());
        temperature.setSurface(50);
        assertEquals(50.0 , temperature.getSurface());
        temperature.setSystem("oil");
        assertEquals("oil" , temperature.getSystem());
    }
}
