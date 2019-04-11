package server.transportation;

import org.junit.Test;
import server.entity.Meal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class TransportationTest {
    private Transportation transport = new Transportation("plane", 97);

    @Test
    public void testConstructor() {
        assertEquals(transport.getName(), "plane");
        assertEquals(transport.getCo2(), 97, 0.01);
    }

    @Test
    public void testGetProduct2() {
        assertNotEquals(transport.getName(), "airbus");
    }

    @Test
    public void testGetProduct() {
        assertEquals(transport.getName(), "plane");
    }

    @Test
    public void testGetCo2() {
        assertEquals(transport.getCo2(), 97, 0.001);
    }

    @Test
    public void testEquals() {
         Transportation transport2 = new Transportation("plane", 97);
         assertTrue(transport2.equals(transport));
    }

    @Test
    public void testEquals2() {
        Transportation transport2 = new Transportation("plane", 99);
        assertFalse(transport2.equals(transport));
    }

    @Test
    public void testEquals3() {
        Transportation transport2 = new Transportation("car", 97);
        assertFalse(transport.equals(transport2));
    }

    @Test
    public void testEquals4() {
        assertTrue(transport.equals(transport));
    }

    @Test
    public void testEquals5() {
        Meal transport2 = new Meal("car", 97);
        assertFalse(transport.equals(transport2));
    }

}
