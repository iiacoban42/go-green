package server.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    @Test
    public void getTotalScore() {
        Score score = new Score(200);
        assertEquals(200, score.getTotalScore());
    }

    @Test
    public void setTotalScore() {
        Score score = new Score(200);
        score.setTotalScore(100);
        assertEquals(100, score.getTotalScore());
    }
}