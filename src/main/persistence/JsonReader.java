package persistence;

import model.Event;
import model.EventLog;
import model.Score;
import model.HistoryScore;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// reference JsonSerializationDemo persistence package
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads history scores from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads history scores from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HistoryScore read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("The file is loaded"));
        return parseHistoricalScore(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses history scores from JSON object and returns it
    private HistoryScore parseHistoricalScore(JSONObject jsonObject) {
        String name = jsonObject.getString("History Scores");
        JSONObject lastTimes = jsonObject.getJSONObject("Last Times");
        Score lastTimesScore = new Score(lastTimes.getInt("Times"), lastTimes.getInt("Score"));
        HistoryScore historyScore = new HistoryScore(name, lastTimesScore);
        addScores(historyScore, jsonObject);
        return historyScore;
    }

    // MODIFIES: historyScore
    // EFFECTS: parses scores from JSON object and adds them to history scores
    private void addScores(HistoryScore historyScore, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(historyScore, nextScore);
        }
    }

    // MODIFIES: historyScore
    // EFFECTS: parses score from JSON object and adds it to history scores
    private void addScore(HistoryScore historyScore, JSONObject jsonObject) {
        int times = jsonObject.getInt("Times");
        int numScore = jsonObject.getInt("Score");
        Score score = new Score(times, numScore);
        historyScore.addScore(score);
    }
}
