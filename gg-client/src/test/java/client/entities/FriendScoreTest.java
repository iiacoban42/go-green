package client.entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendScoreTest {

    @Test
    public void testSetters() {
        FriendScore friendScore = new FriendScore();
        friendScore.setName("friend");
        friendScore.setScore(50);

        assertEquals("friend" , friendScore.getName());
        assertEquals(50 , friendScore.getScore());
    }

    @Test
    public void testGetters() {
        FriendScore friendScore = new FriendScore("friend" , 50);
        assertEquals("friend" , friendScore.getName());
        assertEquals(50 , friendScore.getScore());
    }
}
