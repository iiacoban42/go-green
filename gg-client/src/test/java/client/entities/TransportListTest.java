package client.entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransportListTest {
    Transport transport = new Transport("train", 10);
    Transport transport2 = new Transport("bus", 5);

    @Test
    public void testAddTransport() {

        List<Transport> list = new ArrayList<>();
        list.add(transport);
        TransportList transportList = new TransportList(list);
        assertEquals(transportList.getTransport(), list);
    }

    @Test
    public void testRemoveTransport() {
        List<Transport> list = new ArrayList<>();
        list.add(transport);
        list.add(new Transport("bus", 20));

        TransportList transportList = new TransportList(list);
        transportList.removeTransport(new Transport("bus", 20));
        assertEquals(transportList.getTransport(), list);
    }

    @Test
    public void testGetTransport() {
        List<Transport> list = new ArrayList<>();
        list.add(transport);

        TransportList transportList = new TransportList(list);
        assertEquals(transportList.getTransport(), list);
    }

    @Test
    public void testSetMeals() {

        List<Transport> list = new ArrayList<>();
        list.add(transport);

        TransportList transportList = new TransportList(list);
        transportList.setTransport(list);
        assertEquals(transportList.getTransport(), list);
    }

    @Test
    public void testToString() {
        String string = "trip: train 10.0\n";
        TransportList list = new TransportList();
        list.addTransport(transport);
        assertEquals(list.toString(), string);

    }

    @Test
    public void testSize() {
        TransportList list = new TransportList();
        list.addTransport(transport);
        list.addTransport(transport2);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testJsonConverter() {
        String string = "trip: train 10.0\nbus 5.0\n";

        String json = "[  \n" +
                "   {  \n" +
                "      \"name\":\"train\",\n" +
                "      \"distance\":10\n" +
                "   },\n" +
                "   {  \n" +
                "      \"name\":\"bus\",\n" +
                "      \"distance\":5\n" +
                "   }\n" +
                "]";
        TransportList transportList = new TransportList();
        transportList.jsonConverter(json);
        assertEquals(string, transportList.toString());
    }


    @Test
    public void throwsException() {
        try {
            TransportList list = new TransportList();
            list.jsonConverter("");

        } catch (Exception e) {
            assertEquals("exception", e.getMessage());
        }
    }


    @Test
    public void testGet() {
        String string = "train 10.0";
        String string2 = "bus 5.0";
        TransportList list = new TransportList();
        list.addTransport(transport);
        list.addTransport(transport2);
        assertEquals(list.get(0).toString(), string);
        assertEquals(list.get(1).toString(), string2);
    }
}
