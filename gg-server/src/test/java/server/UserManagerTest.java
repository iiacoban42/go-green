package server;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import server.controller.UserManager;


public class UserManagerTest {

    @Test
    public void addUserTest(){
        UserManager.addUser("1", "1", "1@1.1");
        assertEquals("1", UserManager.getUsers("1").getUsername());
    }

}
