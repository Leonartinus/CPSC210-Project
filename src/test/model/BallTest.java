package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for the Ball Class
class BallTest {
    private Ball testBall;

    @BeforeEach
    public void setTestBall() {
        testBall = new Ball();
    }

    @Test
    public void testMove() {
        assertEquals(testBall.X_ORIGIN, testBall.getCoordinateX());

        testBall.move();
        assertEquals(testBall.X_ORIGIN + testBall.SPEED, testBall.getCoordinateX());

        for (int i = 0; i < 5; i++) {
            testBall.move();
        }

        assertEquals(testBall.X_ORIGIN + 6 * testBall.SPEED, testBall.getCoordinateX());
    }

    @Test
    public void testReset() {
        testBall.move();
        assertEquals(testBall.X_ORIGIN + testBall.SPEED, testBall.getCoordinateX());
        testBall.reset();
        assertEquals(testBall.X_ORIGIN, testBall.getCoordinateX());

        for (int i = 0; i < 5; i++) {
            testBall.move();
        }
        assertEquals(testBall.X_ORIGIN + 5 * testBall.SPEED, testBall.getCoordinateX());
        testBall.reset();
        assertEquals(testBall.X_ORIGIN, testBall.getCoordinateX());
    }
}