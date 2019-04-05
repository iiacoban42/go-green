package server.entity;

import database.entity.SolPanelAction;
import database.entity.User;
import database.manager.SolPanelActionManager;
import database.manager.UserManager;
import org.junit.Test;

import java.util.Date;

public class SolarPanelsTest {

    @Test
    public void zero() {
        User testSP = UserManager.addUser("testSP", "testSP", "email");
        long id = SolPanelActionManager.createSp("testSP", 0, 0);
        Date now = new Date();
        SolPanelActionManager.getActiveSpByUser("testSP").setDateLastCashedIn(now);
        int savedCo2 = SolarPanels.savedCO2("testSP");
    }
}
