package server.transportation;

import org.junit.Test;
import server.entity.Transport;
import server.entity.TransportList;

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
}
