package client.entities;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ScoreTest {

    private Score score = new Score(50);
    private Score score2 = new Score();

    @Test
    public void testGetTotalCore(){
       assertEquals(score.getTotalScore() , 50 );
    }

    @Test
    public void testSetTotslScore() {
        score2.setTotalScore(60);
        assertEquals(60 , score2.getTotalScore());
    }
}
