package client.requests;

import client.entities.Transport;
import client.entities.TransportList;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class TransportRequestsTest {


    @Test
    public void TransportSend_successful() throws Exception {
        TransportList transportList = new TransportList();

        String response = TransportRequests.sendTransportList(transportList);
        assertEquals("200 OK" , response);
    }


    @Test(expected = Exception.class)
    public void TransportSend_unsuccessful() throws Exception {

     TransportRequests.sendTransportList(null);


    }
}
