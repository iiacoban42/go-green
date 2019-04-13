package server.database;

import database.entity.Badge;
import database.manager.BadgeManager;
import database.manager.UserManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BadgeManagerTest {

    @BeforeClass
    public static void addObjects() {
        UserManager.addUser("bmt1", "1", "1");
        UserManager.addUser("bmt2", "2", "2");
    }


    @Test
    public void addBadgeTest() {
        long id = BadgeManager.addBadge("100", "bmt1");
        assertEquals("100",BadgeManager.getBadge(id).getBadgeName());
        BadgeManager.deleteBadge(id);
        assertEquals(null, BadgeManager.getBadge(id));
    }

    @Test
    public void getBadgesByName() {
        BadgeManager.addBadge("1", "bmt1");
        BadgeManager.addBadge("1", "bmt1");
        BadgeManager.addBadge("1", "bmt1");
        BadgeManager.addBadge("1", "bmt2");
        List<Badge> badges = BadgeManager.listBadgesUser("bmt1");
        assertEquals(3, badges.size());
        badges.addAll(BadgeManager.listBadgesUser("bmt2"));
        for (Badge action : badges){
            BadgeManager.deleteBadge(action.getId());
        }
        assertEquals(0, BadgeManager.listBadgesUser("1").size());
    }

    @AfterClass
    public static void deleteObjects(){
        UserManager.deleteUser("bmt1");
        UserManager.deleteUser("bmt2");
    }
}
