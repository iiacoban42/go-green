package client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static client.requests.LoginRequests.sendLoginCredentials;
import static junit.framework.TestCase.assertEquals;

public class SolarPanelsRequestsTest {

    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }


    @Test
    public  void testPostSolarPanels() throws JsonProcessingException {

        String response = SolarPanelRequests.sendSolarPanels(50);
        assertEquals("200 OK" , response);

        int amount = SolarPanelRequests.getSolarPanels();
        assertEquals(50 , amount);
    }

}
