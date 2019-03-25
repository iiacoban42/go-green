package server.database;

import database.entity.ContinuouseAction;
import database.manager.ContinuouseActionManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContinuouseActionManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("camt1", "1", "1");
        UserManager.addUser("camt2", "2", "2");
        long id = ContinuouseActionManager.createCa("camt2", "0", 20, 5);
    }

    @Test
    public void addCactionTest() {
        long id = ContinuouseActionManager.createCa("camt1", "1", 20, 5);
        assertEquals("1", ContinuouseActionManager.getCa(id).getActionName());
        ContinuouseActionManager.deleteCa(id);
        assertNull(ContinuouseActionManager.getCa(id));
    }

    @Test
    public void checkInTest() {
        long id = ContinuouseActionManager.listActiveCaByUser("camt2").get(0).getId();
        ContinuouseActionManager.checkInCa(id);
        assertEquals(40, ContinuouseActionManager.listActiveCaByUser("camt2").get(0).getTotalScore());
        assertEquals(40, UserManager.getUser("camt2").gettotalScore());
    }

    @AfterClass
    public static void delteObjects() {
        List<ContinuouseAction> list = ContinuouseActionManager.listCa();
        for (ContinuouseAction continuouseAction : list){
            ContinuouseActionManager.deleteCa(continuouseAction.getId());
        }
        UserManager.deleteUser("camt1");
        UserManager.deleteUser("camt2");
    }


}
