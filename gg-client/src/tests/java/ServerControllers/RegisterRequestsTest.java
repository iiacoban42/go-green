package ServerControllers;


import client.ServerControllers.RegisterRequests;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RegisterRequestsTest {


    @Test
    public void loginTest_successful() throws Exception {

        String response = RegisterRequests.sendRegisterCredentials("user" , "pass" , "email");
        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void loginTest_unsuccessful() throws Exception {

        RegisterRequests.sendRegisterCredentials("admin" , "pass" , "email");
    }


}

