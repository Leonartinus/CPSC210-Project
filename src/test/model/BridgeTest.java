package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for the Bridge Class
public class BridgeTest {
    private Bridge testBridge;

    @BeforeEach
    public void setTestBridge() {
        testBridge = new Bridge();
    }

    @Test
    public void testBuild() {
        assertEquals(0, testBridge.getLength());
        assertEquals(1, testBridge.getDirection());
        assertEquals(0, testBridge.getDegree());

        testBridge.build();
        assertEquals(testBridge.CHANGING_RATE, testBridge.getLength());

        for (int i = 0; i < 5; i++) {
            testBridge.build();
        }
        assertEquals(6 * testBridge.CHANGING_RATE, testBridge.getLength());
    }

    @Test
    public void testHandleBoundary() {
        assertEquals(0, testBridge.getLength());
        assertEquals(1, testBridge.getDirection());

        // upper boundary handle
        for (int time = 0; time * testBridge.CHANGING_RATE < testBridge.MAX_LENGTH - 5; time++) {
            testBridge.build();
            testBridge.handleBoundary();
        }
        assertEquals(1, testBridge.getDirection());
        assertEquals(testBridge.MAX_LENGTH - 5, testBridge.getLength());

        testBridge.build();
        testBridge.handleBoundary();
        assertEquals(-1, testBridge.getDirection());
        assertEquals(testBridge.MAX_LENGTH, testBridge.getLength());

        // lower boundary handle
        for (int time = 0; (testBridge.MAX_LENGTH - (time * testBridge.CHANGING_RATE)) > 5; time++) {
            testBridge.build();
            testBridge.handleBoundary();
        }
        assertEquals(-1, testBridge.getDirection());
        assertEquals(5, testBridge.getLength());

        testBridge.build();
        testBridge.handleBoundary();
        assertEquals(1, testBridge.getDirection());
        assertEquals(0, testBridge.getLength());
    }

    @Test
    public void testReset() {
        // initial
        assertEquals(0, testBridge.getLength());
        assertEquals(1, testBridge.getDirection());

        // change
        for (int i = 0; i < 4; i++) {
            testBridge.build();
        }
        assertEquals(testBridge.CHANGING_RATE * 4, testBridge.getLength());
        assertEquals(1, testBridge.getDirection());

        // test reset
        testBridge.reset();
        assertEquals(0, testBridge.getLength());
        assertEquals(1, testBridge.getDirection());
    }

    @Test
    public void testRotate() {
        testBridge.rotate();
        assertEquals(Bridge.CHANGING_DEGREE, testBridge.getDegree());
    }

}
