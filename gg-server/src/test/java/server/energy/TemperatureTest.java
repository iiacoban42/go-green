package server.energy;

import org.junit.Test;

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
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertEquals(temp.getSurface(),55,0.1);
    }

    @Test
    public void testJsonConverter3() {
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertEquals(temp.getEnergy(),500,0.1);
    }

    @Test
    public void testJsonConverter4() {
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertNotEquals(temp.getEnergy(),55,0.1);
    }

    @Test
    public void testJsonConverter5() {
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertNotEquals(temp.getSurface(),500,0.1);
    }

    @Test
    public void testJsonConverter6() {
        Temperature temp = new Temperature();
        temp.jsonConverter(json);
        assertEquals(temp.getSystem(),"electricity");
    }


    @Test
    public void throwsException() {
        try {
            Temperature temp = new Temperature();
            temp.jsonConverter("");

        } catch (Exception e) {
            assertEquals("exception", e.getMessage());
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


}
