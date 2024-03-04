package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for HistoryScore Class
public class HistoryScoreTest {
    private HistoryScore testHistoryScore;
    private HistoryScore testHistoryScore2;
    private Score score1;
    private Score score2;
    private Score score3;


    @BeforeEach
    public void setTestHistoryScore() {
        testHistoryScore = new HistoryScore();
        score1 = new Score(1, 0);
        score2 = new Score(2, 0);
        score3 = new Score(3, 0);

        testHistoryScore2 = new HistoryScore("History Score", score2);
    }

    @Test
    public void testSetUp() {
        assertEquals("History Scores", testHistoryScore.getName());
        assertEquals(1, testHistoryScore.getLastTimes().getTimes());
        assertEquals(0, testHistoryScore.getLastTimes().getScore());
        assertEquals(0, testHistoryScore.getScores().size());

        assertEquals("History Score", testHistoryScore2.getName());
        assertEquals(score2, testHistoryScore2.getLastTimes());
        assertEquals(0, testHistoryScore2.getScores().size());
    }

    @Test
    public void testSetLastTimesScore() {
        testHistoryScore.setLastTimesScore(score3);
        assertEquals(score3, testHistoryScore.getLastTimes());
    }

    @Test
    public void testAddScore() {
        ArrayList<Score> scores = testHistoryScore.getScores();
        assertEquals(0, scores.size());
        Score lastTimes = testHistoryScore.getLastTimes();
        assertEquals(1, lastTimes.getTimes());
        assertEquals(0, lastTimes.getScore());

        testHistoryScore.addScore(score1);
        assertEquals(1, scores.size());
        assertTrue(scores.contains(score1));
        assertEquals(score1, scores.get(0));

        testHistoryScore.addScore(score2);
        testHistoryScore.addScore(score3);
        assertEquals(3, scores.size());
        assertTrue(scores.contains(score2));
        assertTrue(scores.contains(score3));
        assertEquals(score2, scores.get(1));
        assertEquals(score3, scores.get(2));
        lastTimes = testHistoryScore.getLastTimes();
        assertEquals(1, lastTimes.getTimes());
        assertEquals(0, lastTimes.getScore());
    }

    @Test
    public void testAddTimes() {
        testHistoryScore.nextTimes();
        Score lastTimes = testHistoryScore.getLastTimes();
        assertEquals(2, lastTimes.getTimes());
        assertEquals(0, lastTimes.getScore());

        testHistoryScore.nextTimes();
        testHistoryScore.nextTimes();
        lastTimes = testHistoryScore.getLastTimes();
        assertEquals(4, lastTimes.getTimes());
        assertEquals(0, lastTimes.getScore());
    }

    @Test
    public void testMaxScore() {
        score1.add1();
        for (int i = 0; i < 4; i++) {
            score2.add1();
        }
        score3.add1();
        score3.add1();

        testHistoryScore.addScore(score1);
        testHistoryScore.addScore(score2);
        testHistoryScore.addScore(score3);

        assertEquals(score2, testHistoryScore.maxScore());
    }

    @Test
    public void testMoreThan1Max() {
        // test the case that there are two same score are max
        score2.add1();
        score3.add1();

        testHistoryScore.addScore(score1);
        testHistoryScore.addScore(score2);
        testHistoryScore.addScore(score3);

        // return the first one
        assertEquals(score2, testHistoryScore.maxScore());
    }
}
