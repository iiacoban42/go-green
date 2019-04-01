package server.database;

import database.entity.Action;
import database.manager.ActionManager;
import database.manager.UserManager;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class ActionManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("1", "1", "1");
        UserManager.addUser("2", "2", "2");
    }


    @Test
    public void addActionTest(){
        long id = ActionManager.addAction("1", "1", 100);
        assertEquals("1", ActionManager.getAction(id).getActionName());
        assertEquals(100, UserManager.getUser("1").gettotalScore());
        ActionManager.deleteAction(id);
        assertEquals(0, UserManager.getUser("1").gettotalScore());
        assertEquals(null, ActionManager.getAction(id));
    }

    @Test
    public void getActionsByName() {
        ActionManager.addAction("1", "1", 100);
        ActionManager.addAction("1", "1", 100);
        ActionManager.addAction("1", "1", 100);
        ActionManager.addAction("1", "2", 100);
        List<Action> actions = ActionManager.listActionsUser("1");
        assertEquals(3, actions.size());
        for (Action action : actions){
            ActionManager.deleteAction(action.getId());
        }
        actions = ActionManager.listActionsUser("2");
        for (Action action : actions){
            ActionManager.deleteAction(action.getId());
        }
        assertEquals(0, ActionManager.listActionsUser("1").size());
    }

    @AfterClass
    public static void deleteObjects(){
        UserManager.deleteUser("1");
        UserManager.deleteUser("2");
    }

}

