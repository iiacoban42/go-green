package client.requests;

import client.entities.Temperature;
import org.junit.Test;

import java.io.IOException;

import static client.requests.LoginRequests.sendLoginCredentials;
import static client.requests.TemperatureRequests.sendTemperature;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TemperatureRequestsTest {

    @Test
    public void testSendTemperature_successful() throws IOException {
        sendLoginCredentials("userForTests" , "test");
        Temperature temperature = new Temperature(50 , 50 , "naturalGas");

        String response = sendTemperature(temperature);
        assertEquals("200 OK"  , response);
    }

}
