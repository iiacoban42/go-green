package server.database;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



import database.manager.UserManager;
import database.entity.User;


import static org.junit.Assert.*;


public class UserManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("cpt1", "1", "1@1.1");
        UserManager.addUser("cpt2", "1", "1@1.1");
        UserManager.addUser("cpt3", "1", "1@1.1");
    }

    @AfterClass
    public static void deleteObjects() {
        UserManager.deleteUser("cpt1");
        UserManager.deleteUser("cpt2");
        UserManager.deleteUser("cpt3");
    }

    @Test
    public void getNonexistentUserTest() {
        User user = UserManager.getUser("N");
        assertNull(null, user);
    }

    @Test
    public void changePasswordTest() {
        UserManager.addUser("cptTest", "1", "1@1.1");
        UserManager.changePassword("cptTest", "2");
        assertEquals("2", UserManager.getUser("cptTest").getHashPassword());
        UserManager.deleteUser("cptTest");
    }

    @Test
    public void tokenTest() {
        UserManager.setToken("cpt1", "bdefg");
        assertEquals("bdefg", UserManager.getUser("cpt1").getToken());
        UserManager.setToken("cpt1", "234");
        assertEquals("234", UserManager.getUser("cpt1").getToken());
    }

    @Test
    public void addScoreTest() {
        UserManager.addScore("cpt1", 500);
        assertEquals(500, UserManager.getUser("cpt1").gettotalScore());
    }

    @Test
    public void addFriend() {
        UserManager.addFriend("cpt1", "cpt2");
        assertEquals("cpt2", UserManager.getUser("cpt1").getFriend());
        assertEquals("cpt1", UserManager.getUser("cpt2").getFriend());
    }

}