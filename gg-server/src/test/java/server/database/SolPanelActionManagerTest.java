package server.database;

import database.entity.SolPanelAction;
import database.manager.SolPanelActionManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolPanelActionManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("camt1", "1", "1");
        UserManager.addUser("camt2", "2", "2");
        long id = SolPanelActionManager.createSp("camt2", 20, 5);
    }

    @Test
    public void addSpctionTest() {
        long id = SolPanelActionManager.createSp("camt1", 20, 5);
        assertEquals(20, SolPanelActionManager.getSp(id).getScorePerDay());
        SolPanelActionManager.deleteSp(id);
        assertNull(SolPanelActionManager.getSp(id));
    }

    @Test
    public void cashInTest() {
        long id = SolPanelActionManager.getActiveSpByUser("camt2").getId();
        SolPanelActionManager.cashInSp(id);
        assertEquals(40, SolPanelActionManager.getActiveSpByUser("camt2").getTotalScore());
        assertEquals(40, UserManager.getUser("camt2").gettotalScore());
    }

    @AfterClass
    public static void delteObjects() {
        List<SolPanelAction> list = SolPanelActionManager.listSp();
        for (SolPanelAction solPanelAction : list){
            SolPanelActionManager.deleteSp(solPanelAction.getId());
        }
        UserManager.deleteUser("camt1");
        UserManager.deleteUser("camt2");
    }


}
