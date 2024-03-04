package persistence;

import model.Score;
import model.HistoryScore;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// reference JsonSerializationDemo persistence package
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Unit test for JsonReader Class
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HistoryScore wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHistoryScore() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHistoryScore.json");
        try {
            HistoryScore historyScore = reader.read();
            assertEquals("History Scores", historyScore.getName());
            assertEquals(0, historyScore.getScores().size());
            Score lastTimes = historyScore.getLastTimes();
            assertEquals(1, lastTimes.getTimes());
            assertEquals(0, lastTimes.getScore());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHistoryScore() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHistoryScore.json");
        try {
            HistoryScore historyScore = reader.read();
            assertEquals("History Scores", historyScore.getName());
            assertEquals(2, historyScore.getScores().size());
            Score lastTimes = historyScore.getLastTimes();
            assertEquals(3, lastTimes.getTimes());
            assertEquals(0, lastTimes.getScore());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}