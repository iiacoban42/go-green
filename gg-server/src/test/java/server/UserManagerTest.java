package server;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import database.manager.UserManager;
import database.entity.User;


import java.util.List;


public class UserManagerTest {

    @Test
    public void addUserTest(){
        UserManager.addUser("1", "1", "1@1.1");
        assertEquals("1", UserManager.getUser("1").getUsername());
        UserManager.deleteUser("1");
        assertEquals(null, UserManager.getUser("1"));
    }

    @Test
    public void getNonexistentUserTest(){
        User user = UserManager.getUser("N");
        assertNull(null, user);
    }

    @Test
    public void changePasswordTest(){
        UserManager.addUser("1", "1", "1@1.1");
        UserManager.changePassword("1","2");
        assertEquals("2",UserManager.getUser("1").getHashPassword());
        UserManager.deleteUser("1");
    }

    @Test
    public void listUsersTest(){
        UserManager.addUser("1", "1", "1@1.1");
        UserManager.addUser("2", "1", "1@1.1");
        UserManager.addUser("3", "1", "1@1.1");
        List<User> users = UserManager.listUsers();
        assertEquals("1", users.get(0).getUsername());
        assertEquals("2", users.get(1).getUsername());
        assertEquals("3", users.get(2).getUsername());
        UserManager.deleteUser("1");
        UserManager.deleteUser("2");
        UserManager.deleteUser("3");
    }
}

