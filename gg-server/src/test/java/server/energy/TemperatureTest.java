package server.energy;

import org.junit.Test;
import server.entity.Meal;

import static org.junit.Assert.*;

public class TemperatureTest {
    private Temperature temp = new Temperature(55, 500, "electricity");
    private String json = "{\n" +
            "   \"surface\":55,\n" +
            "   \"energy\":500,\n" +
            "   \"system\":\"electricity\"\n" +
            "}";

    @Test
    public void ConstructorTest() {
        assertEquals(temp.getEnergy(), 500, 0.01);
        assertEquals(temp.getSurface(), 55, 0.01);
        assertTrue(temp.getSystem().equals("electricity"));
    }

    @Test
    public void ContructorTest2() {
        assertNotEquals(temp.getEnergy(), 55, 0.01);
    }

    @Test
    public void ContructorTest3() {
        assertNotEquals(temp.getSurface(), 500, 0.01);
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
    public void getSystemTest2() {
        assertFalse(temp.getSystem().equals("naturalGas"));
    }

    @Test
    public void getSystemTest3() {
        assertFalse(temp.getSystem().equals("heatingOil"));
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
    public void setSystemTest2() {
        temp.setSystem("naturalGas");
        assertFalse(temp.getSystem().equals("heatingOil"));
    }

    @Test
    public void setSystemTest3() {
        temp.setSystem("naturalGas");
        assertFalse(temp.getSystem().equals("electricity"));
    }

    @Test
    public void toStringTest() {
        String string = "surface: 55.0 energy: 500.0 heating system: electricity" + "\n";
        assertTrue(string.equals(temp.toString()));
    }

    @Test
    public void testJsonConverter() {
        String string = "surface: 55.0 energy: 500.0 heating system: electricity" + "\n";
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertEquals(string, temp.toString());
    }

    @Test
    public void testJsonConverter2() {
        Temperature temp2 = new Temperature();
        temp2.jsonConverter(json);
        assertTrue(temp2.equals(temp));
    }


    @Test
    public void throwsException() {
        try {
            Temperature temp = new Temperature();
            temp.jsonConverter("");

        } catch (Exception e) {
            assertEquals("string not json", e.getMessage());
        }
    }

    @Test
    public void throwsException2() {
        try {
            Temperature temp = new Temperature();
            temp.jsonConverter("surface: 55.0 energy: 500.0 heating system: electricity");

        } catch (Exception e) {
            assertEquals("exception", e.getMessage());
        }
    }

    @Test
    public void testEquals() {
        Temperature temp2 = new Temperature(55, 500, "electricity");
        assertTrue(temp2.equals(temp));
    }

    @Test
    public void test2Equals() {
        Temperature temp2 = new Temperature(56, 500, "electricity");
        assertFalse(temp.equals(temp2));
    }

    @Test
    public void test3Equals() {
        Temperature temp2 = new Temperature(55, 505, "electricity");
        assertFalse(temp.equals(temp2));
    }

    @Test
    public void test4Equals() {
        Temperature temp2 = new Temperature(55, 500, "naturalGas");
        assertFalse(temp.equals(temp2));
    }

    @Test
    public void test5Equals() {
        Meal temp2 = new Meal("naturalGas",55);
        assertFalse(temp.equals(temp2));
    }

    @Test
    public void test6Equals() {
        assertTrue(temp.equals(temp));
    }

}
