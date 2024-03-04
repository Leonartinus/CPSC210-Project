package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for Score Class
public class ScoreTest {
    private Score testScore;

    @BeforeEach
    public void setTestScore() {
        testScore = new Score(1, 0);
    }

    @Test
    public void testAdd1() {
        assertEquals(1, testScore.getTimes());
        assertEquals(0, testScore.getScore());

        testScore.add1();
        assertEquals(1, testScore.getTimes());
        assertEquals(1, testScore.getScore());
    }

    @Test
    public void testAdd1SeveralTimes() {
        for (int i = 0; i < 4;i++) {
            testScore.add1();
        }
        assertEquals(4, testScore.getScore());
    }
}
