package client.requests;

import client.requests.RegisterRequests;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RegisterRequestsTest {


    @Test
    public void loginTest_successful() throws Exception {

        String response = RegisterRequests.sendRegisterCredentials("email", "user" , "pass");
        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void loginTest_unsuccessful() throws Exception {

        String response = RegisterRequests.sendRegisterCredentials("email", "admin" , "pass");
        assertEquals("401 UNAUTHORIZED", response);
    }
}

