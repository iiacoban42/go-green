package server.database;

import database.entity.ContinuousAction;
import database.manager.ContinuousActionManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContinuousActionManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("camt1", "1", "1");
        UserManager.addUser("camt2", "2", "2");
        long id = ContinuousActionManager.createCa("camt2", "0", 20, 5);
    }

    @Test
    public void addCactionTest() {
        long id = ContinuousActionManager.createCa("camt1", "1", 20, 5);
        assertEquals("1", ContinuousActionManager.getCa(id).getActionName());
        ContinuousActionManager.deleteCa(id);
        assertNull(ContinuousActionManager.getCa(id));
    }

    @Test
    public void cashInTest() {
        long id = ContinuousActionManager.getActiveCaByUser("camt2").getId();
        ContinuousActionManager.chashInCa(id);
        assertEquals(40, ContinuousActionManager.getActiveCaByUser("camt2").getTotalScore());
        assertEquals(40, UserManager.getUser("camt2").gettotalScore());
    }

    @AfterClass
    public static void delteObjects() {
        List<ContinuousAction> list = ContinuousActionManager.listCa();
        for (ContinuousAction continuousAction : list){
            ContinuousActionManager.deleteCa(continuousAction.getId());
        }
        UserManager.deleteUser("camt1");
        UserManager.deleteUser("camt2");
    }


}
