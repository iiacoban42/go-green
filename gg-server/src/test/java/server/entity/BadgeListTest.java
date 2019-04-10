package server.entity;

import database.entity.Badge;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BadgeListTest {
    Badge badge0 = new Badge("badge0", "TestUser");
    Badge badge1 = new Badge("badge1", "TestUser");

    @Test
    public void testAddBadge() {
        List<Badge> list = new ArrayList<>();
        list.add(badge0);

        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge0);
        assertEquals(badgeList.getBadgeList(), list);
    }

    @Test
    public void testRemoveBadge() {
        List<Badge> list = new ArrayList<>();
        list.add(badge0);
        list.add(badge1);

        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge0);
        badgeList.addBadge(badge1);
        assertEquals(badgeList.getBadgeList(), list);

        list.remove(badge1);
        badgeList.removeBadge(badge1);
        assertEquals(badgeList.getBadgeList(), list);
    }

    @Test
    public void testSetBadgeList() {
        BadgeList badgeList = new BadgeList();
        badgeList.addBadge(badge0);
        badgeList.addBadge(badge1);

        List<Badge> list = new ArrayList<>();
        list.add(badge0);
        badgeList.setBadgeList(list);
        assertEquals(list, badgeList.getBadgeList());
    }
}