package client.entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BadgeListTest {
    Date date = new Date(600);
    Badge badge = new Badge(1 ,date , "test" ,"tes2t",2);

    BadgeList badgeList = new BadgeList();

    @Test
    public void testAddBadge() {
        List<Badge> list = new ArrayList<>();
        list.add(badge);

        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge);
        assertEquals(badgeList.getBadgeList(), list);
    }

    @Test
    public void testRemoveBadge() {
        List<Badge> list = new ArrayList<>();
        list.add(badge);

        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge);
        assertEquals(badgeList.getBadgeList(), list);

        list.remove(badge);
        badgeList.removeBadge(badge);
        assertEquals(badgeList.getBadgeList(), list);
    }

    @Test
    public void testSetBadgeList() {
        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge);
        badgeList.addBadge(badge);

        List<Badge> list = new ArrayList<>();
        list.add(badge);
        badgeList.setBadgeList(list);
        assertEquals(list, badgeList.getBadgeList());
    }
}