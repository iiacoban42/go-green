package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransportTest {
    private Transport transport = new Transport("plane", 123);
    private Transport transport2 = new Transport();

    @Test
    public void testConstructor() {
        assertEquals(transport.getName(), "plane");
        assertEquals(123, transport.getDistance(),0.1);
    }

    @Test
    public void testToString() {
        String string = "plane 123.0";
        assertEquals(string, transport.toString());

    }

    @Test
    public void testDefaultConstructor() {
        assertNull(transport2.getName());
        assertEquals(0, transport2.getDistance(), 0.1);

    }

    @Test
    public void testGetProduct() {
        assertEquals(transport.getName(), "plane");
    }

    @Test
    public void testGetProduct2() {
        assertNotEquals(transport.getName(), null);
    }

    @Test
    public void testGetDistance() {
        assertEquals(transport.getDistance(), 123,0.1);
    }

    @Test
    public void testGetDistance2() {
        assertNotEquals(transport.getDistance(), 0,0.1);
    }

    @Test
    public void testSetProduct() {
        transport.setName("airbus");
        assertEquals("airbus", transport.getName());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(123, transport.getDistance(), 0.1);
    }

    @Test
    public void testSetQuantity() {
        transport.setDistance(11234);
        assertEquals(11234, transport.getDistance(), 0.1);
    }
}
