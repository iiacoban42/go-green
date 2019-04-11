package server.transportation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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


}
