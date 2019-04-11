package server.entity;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TransportListTest {
    private Transport transport = new Transport("train", 10);
    private Transport transport2 = new Transport("bus", 5);

    private String json = "[  \n" +
            "   {  \n" +
            "      \"name\":\"train\",\n" +
            "      \"distance\":10\n" +
            "   },\n" +
            "   {  \n" +
            "      \"name\":\"bus\",\n" +
            "      \"distance\":5\n" +
            "   }\n" +
            "]";

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
    public void testSetTransport() {
        List<Transport> list = new ArrayList<>();
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
        TransportList transportList = new TransportList();
        transportList.jsonConverter(json);
        assertEquals(string, transportList.toString());
    }

    @Test
    public void testJsonConverter1(){
        List<Transport> list = new ArrayList<>();
        list.add(transport);
        list.add(transport2);
        TransportList list1= new TransportList(list);
        TransportList transportList = new TransportList();
        transportList.jsonConverter(json);
        assertTrue(transportList.equals(list1));
    }

    @Test
    public void throwsException() {
        try {
            TransportList list = new TransportList();
            list.jsonConverter("");

        } catch (Exception e) {
            assertEquals("string not json", e.getMessage());
        }
    }

    @Test
    public void throwsException2() {
        try {
            TransportList list = new TransportList();
            list.jsonConverter("trip: train 10.0\nbus 5.0\n");

        } catch (Exception e) {
            assertEquals("string not json", e.getMessage());
        }
    }

    @Test
    public void testGet() {
        TransportList list = new TransportList();
        list.addTransport(transport);
        list.addTransport(transport2);
        assertTrue(list.get(0).equals(transport));
        assertTrue(list.get(1).equals(transport2));
    }

    @Test
    public void testEquals(){
        TransportList list1 = new TransportList();
        TransportList list2 = new TransportList();
        list1.addTransport(transport);
        list1.addTransport(transport2);
        list2.addTransport(transport);
        list2.addTransport(transport2);
        assertTrue(list1.equals(list2));
    }

    @Test
    public void testEquals2(){
        TransportList list1 = new TransportList();
        TransportList list2 = new TransportList();
        list1.addTransport(transport2);
        list1.addTransport(transport);
        list2.addTransport(transport);
        list2.addTransport(transport2);
        assertFalse(list1.equals(list2));
    }

    @Test
    public void testEquals3(){
        TransportList list1 = new TransportList();
        list1.addTransport(transport2);
        list1.addTransport(transport);
        assertTrue(list1.equals(list1));
    }

    @Test
    public void testEquals4(){
        TransportList list1 = new TransportList();
        Meal list2 = new Meal("pizza",2);
        list1.addTransport(transport2);
        list1.addTransport(transport);
        assertFalse(list1.equals(list2));
    }

}
