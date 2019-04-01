package client.requests;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;

public class SessionTest {

    private Token token = mock(Token.class);

    @Test
    public void testGetToken() {

        Session.setToken(token);

        assertEquals(token , Session.getToken());

    }



}
