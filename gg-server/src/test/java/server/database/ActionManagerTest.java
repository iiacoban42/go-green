package server.database;

import database.entity.Action;
import database.manager.ActionManager;
import database.manager.UserManager;
import org.junit.*;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ActionManagerTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("1", "1", "1");
        UserManager.addUser("2", "2", "2");
        final long millisInDay = 86400000;
        Action action = ActionManager.getAction(ActionManager.addAction("vegimeal", "2", 0));
        action.setDateTime(new Date(System.currentTimeMillis()));
        ActionManager.updateAction(action);
        action = ActionManager.getAction(ActionManager.addAction("vegimeal", "2", 0));
        action.setDateTime(new Date(System.currentTimeMillis() - 1 * millisInDay - 1));
        ActionManager.updateAction(action);
        action = ActionManager.getAction(ActionManager.addAction("vegimeal", "2", 0));
        action.setDateTime(new Date(System.currentTimeMillis() - 2 * millisInDay - 1));
        ActionManager.updateAction(action);
        UserManager.addUser("3", "2", "2");
        action = ActionManager.getAction(ActionManager.addAction("vegimeal", "3", 0));
        action.setDateTime(new Date(System.currentTimeMillis() - millisInDay - 1));
        ActionManager.updateAction(action);
        action = ActionManager.getAction(ActionManager.addAction("vegimeal", "3", 0));
        action.setDateTime(new Date(System.currentTimeMillis() - 2 * millisInDay - 1));
        ActionManager.updateAction(action);
        action = ActionManager.getAction(ActionManager.addAction("vegimeal", "3", 0));
        action.setDateTime(new Date(System.currentTimeMillis() - 5 * millisInDay - 1));
        ActionManager.updateAction(action);
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
        List<Action> actions = ActionManager.listActionsUser("1");
        assertEquals(3, actions.size());
        for (Action action : actions){
            ActionManager.deleteAction(action.getId());
        }
        assertEquals(0, ActionManager.listActionsUser("1").size());
    }

    @Test
    public void vegimealStrekTest() {

        int streak = ActionManager.vegimealStreak("2");

        assertEquals(3, streak);
    }

    @Test
    public void vegimealStrekTest2() {
        int streak = ActionManager.vegimealStreak("3");
        assertEquals(2, streak);
    }
    @AfterClass
    public static void deleteObjects(){
        List<Action> actions = ActionManager.listActionsUser("2");
        for (Action a : actions){
            ActionManager.deleteAction(a.getId());
        }
        actions = ActionManager.listActionsUser("3");
        for (Action a : actions){
            ActionManager.deleteAction(a.getId());
        }
        UserManager.deleteUser("1");
        UserManager.deleteUser("2");
        UserManager.deleteUser("3");
    }

}

