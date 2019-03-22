package server.energy;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureTest {
    Temperature temp = new Temperature(55, 500, "electricity");

    @Test
    public void ConstructorTest() {
        assertEquals(temp.getEnergy(), 500, 0.01);
        assertEquals(temp.getSurface(), 55, 0.01);
        assertTrue(temp.getSystem().equals("electricity"));
    }

    @Test
    public void getSurfaceTest() {
        assertEquals(temp.getSurface(), 55, 0.01);
    }

    @Test
    public void getEnergyTest() {
        assertEquals(temp.getEnergy(), 500, 0.01);
    }

    @Test
    public void getSystemTest() {
        assertTrue(temp.getSystem().equals("electricity"));
    }

    @Test
    public void setEnergyTest() {
        temp.setEnergy(213);
        assertEquals(temp.getEnergy(), 213, 0.01);
    }

    @Test
    public void setSurfaceTest() {
        temp.setSurface(82);
        assertEquals(temp.getSurface(), 82, 0.01);
    }

    @Test
    public void setSystemTest() {
        temp.setSystem("naturalGas");
        assertTrue(temp.getSystem().equals("naturalGas"));
    }

    @Test
    public void toStringTest() {
        String string = "surface: 55.0 energy: 500.0 heating system: electricity" + "\n";
        assertTrue(string.equals(temp.toString()));
    }


}
