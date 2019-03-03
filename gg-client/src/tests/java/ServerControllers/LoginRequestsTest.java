package ServerControllers;

import client.ServerControllers.LoginRequests;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginRequestsTest {

    @Test
    public void loginTest_successful() throws Exception {

        String response = LoginRequests.sendLoginCredentials("admin" , "pass" );
        assertEquals("200 OK" , response);
    }

    @Test(expected = Exception.class)
    public void loginTest_unsuccessful() throws Exception {
        String response = LoginRequests.sendLoginCredentials("user" , "pass");
    }

}
