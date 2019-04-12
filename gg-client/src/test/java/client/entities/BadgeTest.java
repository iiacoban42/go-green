package client.entities;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class BadgeTest {
    Date date = new Date(600);

    Badge badge = new Badge(1 ,date , "test" ,"test2",2);

    @Test
    public void testGetters(){
        assertEquals(1 ,badge.getId());
        assertEquals("test" , badge.getBadgeName());
        assertEquals("test2" , badge.getUser());
        assertEquals(2 , badge.getLevel());
    }

}
