package server.entity;

import database.entity.ContinuousAction;
import database.entity.User;
import database.manager.ContinuousActionManager;
import database.manager.UserManager;
import org.junit.Test;

import java.util.Date;

public class SolarPanelsTest {

    @Test
    public void zero() {
        User testSP = UserManager.addUser("testSP", "testSP", "email");
        ContinuousActionManager.createCa("testSP", "solar panels", 0, 0);
        Date now = new Date();
        ContinuousActionManager.getActiveCaByUser("testSP").setDateLastCashedIn(now);
        int savedCo2 = SolarPanels.savedCO2("testSP");
    }
}
