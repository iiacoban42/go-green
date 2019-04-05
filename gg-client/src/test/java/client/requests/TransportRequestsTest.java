package client.requests;

import client.entities.TransportList;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static client.requests.LoginRequests.sendLoginCredentials;
import static org.junit.Assert.assertEquals;

public class TransportRequestsTest {

    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }

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
