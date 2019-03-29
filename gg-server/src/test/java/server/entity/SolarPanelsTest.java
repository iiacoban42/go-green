package server.entity;

import database.entity.User;
import database.manager.ContinuousActionManager;
import database.manager.UserManager;
import org.junit.Test;

public class SolarPanelsTest {

    @Test
    public void zero() {
        User testSP = UserManager.addUser("testSP", "testSP", "email");
//        ContinuousActionManager.createCa("testSP", "solar panels", )
    }
}
