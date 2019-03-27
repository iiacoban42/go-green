package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendScoreTest {

    @Test
    public void getName() {
        FriendScore friendScore = new FriendScore("Billy", 369);
        assertEquals("Billy", friendScore.getName());
    }

    @Test
    public void setName() {
        FriendScore friendScore = new FriendScore("Jacqueline", 177);
        friendScore.setName("Steve");
        assertEquals("Steve", friendScore.getName());
    }

    @Test
    public void getScore() {
        FriendScore friendScore = new FriendScore("Gary", 2);
        assertEquals(2, friendScore.getScore());
    }

    @Test
    public void setScore() {
        FriendScore friendScore = new FriendScore("Brandon", 194);
        friendScore.setScore(286);
        assertEquals(286, friendScore.getScore());
    }
}