package server.database;

import database.entity.Action;
import database.entity.Badge;
import database.manager.ActionManager;
import database.manager.BadgeManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VegimealStreakTest {
    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("vst0", "2", "2");
        UserManager.addUser("vst1", "1", "1");
        UserManager.addUser("vst2", "2", "2");
        UserManager.addUser("vst3", "2", "2");
        final long millisInDay = 86400000;
        for(int i = 0; i < 7; i++){
            Action action = ActionManager.getAction(ActionManager.addAction("veggieMeal", "vst1", 100000));
            action.setDateTime(new Date(System.currentTimeMillis()- i * millisInDay));
            ActionManager.updateAction(action);
        }
        for(int i = 0; i < 14; i++){
            Action action = ActionManager.getAction(ActionManager.addAction("veggieMeal", "vst2", 100000));
            action.setDateTime(new Date(System.currentTimeMillis()- i * millisInDay));
            ActionManager.updateAction(action);
        }
        for(int i = 0; i < 21; i++){
            Action action = ActionManager.getAction(ActionManager.addAction("veggieMeal", "vst3", 100000));
            action.setDateTime(new Date(System.currentTimeMillis()- i * millisInDay));
            ActionManager.updateAction(action);
        }
    }

    @AfterClass
    public static void deletAll(){
        List<Action> actions = ActionManager.listActionsUser("vst1");
        for (Action a : actions){
            ActionManager.deleteAction(a.getId());
        }
        actions = ActionManager.listActionsUser("vst2");
        for (Action a : actions){
            ActionManager.deleteAction(a.getId());
        }
        actions = ActionManager.listActionsUser("vst3");
        for (Action a : actions){
            ActionManager.deleteAction(a.getId());
        }
        List<Badge> badges = BadgeManager.listBadgesUser("vst1");
        for (Badge b : badges){
            BadgeManager.deleteBadge(b.getId());
        }
        badges = BadgeManager.listBadgesUser("vst2");
        for (Badge b : badges){
            BadgeManager.deleteBadge(b.getId());
        }
        badges = BadgeManager.listBadgesUser("vst3");
        for (Badge b : badges){
            BadgeManager.deleteBadge(b.getId());
        }
        UserManager.deleteUser("vst0");
        UserManager.deleteUser("vst1");
        UserManager.deleteUser("vst2");
        UserManager.deleteUser("vst3");
    }

    @Test
    public void streakTest(){
        BadgeManager.checkBadges("vst0");
        assertEquals(0, BadgeManager.listBadgesUser("vst0").size());
        BadgeManager.checkBadges("vst1");
        assertEquals(1, BadgeManager.listBadgesUser("vst1").get(0).getLevel());
        assertEquals(1, BadgeManager.listBadgesUser("vst1").get(1).getLevel());
        BadgeManager.checkBadges("vst2");
        assertEquals(2, BadgeManager.listBadgesUser("vst2").get(0).getLevel());
        assertEquals(2, BadgeManager.listBadgesUser("vst2").get(1).getLevel());
        BadgeManager.checkBadges("vst3");
        assertEquals(3, BadgeManager.listBadgesUser("vst3").get(0).getLevel());
        assertEquals(3, BadgeManager.listBadgesUser("vst3").get(1).getLevel());
    }
}
