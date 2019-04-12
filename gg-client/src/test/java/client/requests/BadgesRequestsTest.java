package client.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

import static client.requests.LoginRequests.sendLoginCredentials;
import static junit.framework.TestCase.assertEquals;

public class BadgesRequestsTest {

    @Before
    public void setUp() throws JsonProcessingException {

        sendLoginCredentials("userForTests" , "test");

    }

    @Test
    public void testGetAllBadges_successful() {

        BadgeRequests badgeRequests = new BadgeRequests();
        String response =  badgeRequests.getBadges();
        assertEquals(String.class , response.getClass());
        assertEquals("200 OK" , response);

    }
}
