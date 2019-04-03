package client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static client.requests.LoginRequests.sendLoginCredentials;
import static junit.framework.TestCase.assertEquals;

public class LoginRequestsTest {

    @Test
    public void testSendLoginCredentials_succesfull() throws JsonProcessingException {

      String response =  sendLoginCredentials("userForTests" , "test");
      assertEquals("200 OK" , response );
    }

    @Test(expected = Exception.class)
    public void loginTest_unsuccessful() throws Exception {
        String response = LoginRequests.sendLoginCredentials("userForTests" , "pass");
        assertEquals("401 UNAUTHORIZED", response);
    }


}
