package server.database;

import database.entity.SolPanelAction;
import database.manager.SolPanelActionManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class SolPanelActionManagerTest {
    final int solarPanels = 5;
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("camt1", "1", "1");
        UserManager.addUser("camt2", "2", "2");
        long id = SolPanelActionManager.createSp("camt2", 5);
    }

    @Test
    public void addSpActionTest() {
        long id = SolPanelActionManager.createSp("camt1", 5);
        assertEquals(630 * 1000 / ( 6 * 365 )* 5, SolPanelActionManager.getSp(id).getScorePerDay());
        SolPanelActionManager.deleteSp(id);
        assertNull(SolPanelActionManager.getSp(id));
    }

    @Test
    public void cashInTest() {
        long id = SolPanelActionManager.getActiveSpByUser("camt2").getId();
        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser("camt2");
        solPanelAction.setDateLastCashedIn(new Date(new Date().getTime()-96400000));
        SolPanelActionManager.update(solPanelAction);
        SolPanelActionManager.cashInSp(id);
        assertEquals(630 * 1000 / ( 6 * 365 )*solarPanels*2, SolPanelActionManager.getActiveSpByUser("camt2").getTotalScore());
        assertEquals(630 * 1000 / ( 6 * 365 )*solarPanels*2, UserManager.getUser("camt2").gettotalScore());
    }

    @Test
    public void cashedInLessThan24Hours(){
        long id = SolPanelActionManager.getActiveSpByUser("camt2").getId();
        SolPanelAction solPanelAction = SolPanelActionManager.getActiveSpByUser("camt2");
        SolPanelActionManager.cashInSp(id);
        assertEquals(630 * 1000 / ( 6 * 365 )*solarPanels, SolPanelActionManager.getActiveSpByUser("camt2").getTotalScore());
        assertEquals(630 * 1000 / ( 6 * 365 )*solarPanels, UserManager.getUser("camt2").gettotalScore());

    }
    @AfterClass
    public static void deleteObjects() {
        List<SolPanelAction> list = SolPanelActionManager.listSp();
        for (SolPanelAction solPanelAction : list){
            SolPanelActionManager.deleteSp(solPanelAction.getId());
        }
        UserManager.deleteUser("camt1");
        UserManager.deleteUser("camt2");
    }


}
