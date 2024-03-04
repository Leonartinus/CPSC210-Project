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
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            HistoryScore historyScore = new HistoryScore("History Scores", new Score(0, 0));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHistoryScore() {
        try {
            HistoryScore historyScore = new HistoryScore("History Scores", new Score(0, 0));
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHistoryScore.json");
            writer.open();
            writer.write(historyScore);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHistoryScore.json");
            historyScore = reader.read();
            assertEquals(0, historyScore.getScores().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHistoryScore() {
        try {
            HistoryScore historyScore = new HistoryScore("History Scores", new Score(0, 0));
            historyScore.addScore(new Score(1,1));
            historyScore.addScore(new Score(1, 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHistoryScore.json");
            writer.open();
            writer.write(historyScore);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHistoryScore.json");
            historyScore = reader.read();
            List<Score> scores = historyScore.getScores();
            assertEquals(2, scores.size());
            checkScore(1, 1, scores.get(0));
            checkScore(1, 3, scores.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}