package client.entities;

import client.entities.LoginCredentials;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginCredentialsTest {

   private final LoginCredentials test = new LoginCredentials("test" , "test");
    @Test
    public void testSetUsername(){
        test.setUsername("change");
        assertEquals("change" , test.getUsername());
    }

    @Test
    public  void testSetPassword(){
        test.setPassword("change");
        assertEquals("change" , test.getPassword());

    }

    @Test
    public  void testGetUsername(){
        assertEquals("test" , test.getUsername());
    }

    @Test
    public void testGetPassword(){
        assertEquals("test" , test.getPassword());
    }

    @Test
    public void testConstructor(){
        assertEquals(test.getPassword() , "test");
        assertEquals(test.getUsername() , "test");
    }
}
