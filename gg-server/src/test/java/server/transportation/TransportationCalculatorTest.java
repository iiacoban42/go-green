package server.transportation;

import org.junit.Test;
import server.entity.Transport;
import server.entity.TransportList;

import java.lang.reflect.Array;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static server.transportation.TransportationCalculator.vehicle;

public class TransportationCalculatorTest {
    @Test
    public void testGetAmountCo2() {
        Transport [] list = new Transport[]{
                new Transport(vehicle[0].getName(),5),
                new Transport(vehicle[1].getName(),10),
                new Transport(vehicle[2].getName(),5)
        };

        TransportList transportList= new TransportList(Arrays.asList(list));
        assertEquals(1390,TransportationCalculator.getAmountCo2(transportList),0.1);
    }

    @Test
    public void test2GetAmountCo2() {
        Transport [] list = new Transport[]{
                new Transport(vehicle[3].getName(),5),
                new Transport(vehicle[4].getName(),10),
                new Transport(vehicle[5].getName(),5)
        };

        TransportList transportList= new TransportList(Arrays.asList(list));
        assertEquals(1722.5,TransportationCalculator.getAmountCo2(transportList),0.1);
    }
}
