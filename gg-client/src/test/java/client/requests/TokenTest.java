package client.requests;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class TokenTest {

    private Token token = new Token();
    private Token tokenTest = new Token("test");

    @Test
    public  void testGetToken_successful(){
        assertEquals(tokenTest.getToken() , "test") ;
        assertNull(token.getToken());
    }
    @Test
    public  void testGetToken_unsuccessful(){
        assertNotEquals(tokenTest.getToken() , "wrong") ;
    }


}
