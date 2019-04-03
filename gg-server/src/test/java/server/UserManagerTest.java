package server;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import database.manager.UserManager;

public class UserManagerTest {

    @Test
    public void addUserTest(){
        UserManager.addUser("1", "1", "1@1.1");
        assertEquals("1", UserManager.getUser("1").getUsername());
        UserManager.deleteUser("1");
        assertNull(UserManager.getUser("1"));
    }

    @Test
    public void getNonexistentUserTest(){
        database.entity.User user = UserManager.getUser("N");
        assertNull(null, user);
    }
}

