package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for the Game Class
public class GameTest {
    private Game testGame;
    private HistoryScore testHistoryScore;

    @BeforeEach
    public void setTestGame() {
        testHistoryScore = new HistoryScore();
        testGame = new Game(testHistoryScore);
    }

    @Test
    public void testSetUp() {
        assertEquals(Ball.X_ORIGIN, testGame.getBall().getCoordinateX());
        assertEquals(0, testGame.getBridge().getLength());
        assertEquals(1, testGame.getBridge().getDirection());
        assertEquals(0, testGame.getBridge().getDegree());
        assertEquals(testHistoryScore, testGame.getHistoryScore());
    }

    @Test
    public void testUpdateToLose() {
        testGame.update();
        testSetUp();
        testGame.keyPressing(KeyEvent.VK_SPACE);
        assertFalse(testGame.isBuild());
        assertEquals(0, testGame.getBridge().getDegree());
        testGame.keyPressing(KeyEvent.VK_D);
        assertFalse(testGame.isDown());
        assertTrue(testGame.isBuild());
        for (int t = 0; t < 18; t++) {
            testGame.update();
            assertFalse(testGame.isDown());
        }
        assertEquals(Ball.X_ORIGIN, testGame.getBall().getCoordinateX());
        testGame.update();
        assertTrue(testGame.isDown());
        assertEquals(Ball.X_ORIGIN + Ball.SPEED, testGame.getBall().getCoordinateX());
        assertFalse(testGame.getIsOver());
        for (int t = 0; t < 6; t++) {
            testGame.update();
        }
        testGame.update();
        assertEquals(Ball.X_ORIGIN + 7 * Ball.SPEED, testGame.getBall().getCoordinateX());
        assertEquals((testGame.getBridge().getLength() + Bridge.X_LEFT), testGame.getBall().getCoordinateX());
        assertEquals(1, testGame.getScore().getTimes());
        assertEquals(0, testGame.getScore().getScore());
        assertTrue(testGame.getIsOver());
    }

    @Test
    public void tesKeyPressing() {
        Ball ball = testGame.getBall();
        Bridge bridge = testGame.getBridge();

        // press non-space and non-escape key - nothing change
        testGame.keyPressing(KeyEvent.VK_E);
        assertEquals(Ball.X_ORIGIN, ball.getCoordinateX());
        assertEquals(0, bridge.getLength());
        assertEquals(1, bridge.getDirection());

        // press space build the bridge - nothing else change
        testGame.keyPressing(KeyEvent.VK_SPACE);
        assertEquals(5, bridge.getLength());
        assertEquals(1, bridge.getDirection());
        assertEquals(Ball.X_ORIGIN, ball.getCoordinateX());

        testGame.keyPressing(KeyEvent.VK_SPACE);
        testGame.keyPressing(KeyEvent.VK_SPACE);
        assertEquals(15, bridge.getLength());
        assertEquals(1, bridge.getDirection());
    }

    @Test
    public void testMoveBall() {
        // move ball 5 times
        for (int t = 0; t < 5; t++) {
            testGame.keyPressing(KeyEvent.VK_SPACE);
        }
        testGame.moveBall();
        Ball movedBall = testGame.getBall();
        assertEquals(105, movedBall.getCoordinateX());
    }

    @Test
    // test the case that the ball is on the stage
    public void testIsNotGameOver() {
        int endLeft = testGame.getEndStage().getLeftEdge();

        for (int t = 0; t < (( - endLeft + testGame.getBridge().X_LEFT) + 5); t++) {
            testGame.keyPressing(KeyEvent.VK_SPACE);
        }
        testGame.moveBall();
        testGame.isGameOver();
        assertTrue(testGame.getIsOver());
    }

    @Test
    // test the case that the ball is out of left edge of the end stage, game is over
    public void testIsGameOver1() {
        int endLeft = testGame.getEndStage().getLeftEdge();

        for (int t = 0; t < ((endLeft - testGame.getBridge().X_LEFT) - 5); t++) {
            testGame.keyPressing(KeyEvent.VK_SPACE);
        }
        testGame.isGameOver();
        assertTrue(testGame.getIsOver());
    }

    @Test
    // test the case that the ball is out of right edge of the end stage, game is over
    public void testIsGameOver2() {
        int endRight = testGame.getEndStage().getRightEdge();

        for (int t = 0; t < ((endRight - testGame.getBridge().X_LEFT) + 20); t++) {
            testGame.keyPressing(KeyEvent.VK_SPACE);
        }
        testGame.isGameOver();
        assertTrue(testGame.getIsOver());
    }

}
