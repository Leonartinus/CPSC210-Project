package persistence;

import static org.junit.jupiter.api.Assertions.*;
import model.Score;

// reference JsonSerializationDemo persistence package
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Helper Class to test JsonWriter Class
public class JsonTest {
    protected void checkScore(int times, int numScore, Score score) {
        assertEquals(times, score.getTimes());
        assertEquals(numScore, score.getScore());
    }
}
