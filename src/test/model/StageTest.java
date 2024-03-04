package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit test for Stage Class
public class StageTest {
    private Stage testStage;

    @BeforeEach
    public void setTestStage() {
        testStage = new Stage(20, 30);
    }

    @Test
    public void testGet() {
        assertEquals(20, testStage.getWidth());
        assertEquals(30, testStage.getPositionX());
    }
}
