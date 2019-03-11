package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterCredentialsTest {

    private final RegisterCredentials test = new RegisterCredentials("mail", "user", "pass");

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
    public void testSetPassword_failed() {
        test.setPassword("change");
        assertNotEquals("pass", test.getPassword());
    }

    @Test
    public void testGetUsername() {
        assertEquals("user", test.getUsername());
    }

    @Test
    public void testGetPassword() {
        assertEquals("pass", test.getPassword());
    }


    @Test
    public void testGetEmail() {
        assertEquals("mail", test.getEmail());
    }

    @Test
    public void testSetEmail() {
        test.setEmail("change");
        assertEquals("change", test.getEmail());
    }
}