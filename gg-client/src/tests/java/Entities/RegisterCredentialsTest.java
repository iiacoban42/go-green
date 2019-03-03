package Entities;

import client.entities.RegisterCredentials;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RegisterCredentialsTest {

    private final RegisterCredentials test = new RegisterCredentials("test", "test", "test");

    @Test
    public void testSetUsername() {
        test.setUsername("change");
        assertEquals("change", test.getUsername());
    }

    @Test
    public void testSetPassword() {
        test.setPassword("change");
        assertEquals("change", test.getPassword());

    }

    @Test
    public void testSetPassword_failed(){
        test.setPassword("change");
        assertNotEquals("test" , test.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("test", test.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("test", test.getPassword());
    }


    @Test
    public void testGetEmail(){
        assertEquals("test" , test.getEmail());
    }

    @Test
    public void testSetEmail(){
        test.setEmail("change");
        assertEquals("change" ,test.getEmail() );
    }
}