package server;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import database.manager.UserManager;
import database.entity.User;


import java.util.List;


public class UserManagerTest {
    @BeforeClass
    public static void addObjects(){
        UserManager.addUser("cpt1", "1", "1@1.1");
        UserManager.addUser("cpt2", "1", "1@1.1");
        UserManager.addUser("cpt3", "1", "1@1.1");
    }

    @AfterClass
    public static void deleteObjects(){
        UserManager.deleteUser("cpt1");
        UserManager.deleteUser("cpt2");
        UserManager.deleteUser("cpt3");
    }


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
        UserManager.addUser("cptTest", "1", "1@1.1");
        UserManager.changePassword("cptTest","2");
        assertEquals("2",UserManager.getUser("cptTest").getHashPassword());
        UserManager.deleteUser("cptTest");
    }

    @Test
    public void listUsersTest(){
        List<User> users = UserManager.listUsers();
        assertEquals("cpt1", users.get(0).getUsername());
        assertEquals("cpt2", users.get(1).getUsername());
        assertEquals("cpt3", users.get(2).getUsername());
    }

    @Test
    public void tokenTest(){
        UserManager.setToken("cpt1", "bdefg");
        assertEquals("bdefg", UserManager.getUser("cpt1").getToken());
        UserManager.setToken("cpt1", "234");
        assertEquals("234", UserManager.getUser("cpt1").getToken());
    }



}

