package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// History Score is storing the history scores and te the last times player played also the name of the list
public class HistoryScore implements Writable {
    private ArrayList<Score> scores;
    private String name;
    private Score lastTimesScore;

    // EFFECTS: the constructor without parameter
    public HistoryScore() {
        name = "History Scores";
        lastTimesScore = new Score(1, 0);
        scores = new ArrayList<Score>();
        EventLog.getInstance().logEvent(new Event("New game start"));
    }

    // EFFECTS: construct the HistoryScore with parameter
    public HistoryScore(String name, Score lastTimes) {
        this.name = name;
        lastTimesScore = lastTimes;
        scores = new ArrayList<Score>();
    }

    // MODIFIES: this
    // EFFECTS: add the score into the scores
    public void addScore(Score score) {
        scores.add(score);
        EventLog.getInstance().logEvent(new Event("The " + score.getTimes()
                + "th times score is added to the history scores"));
    }

    public void clear() {
        scores.clear();
        EventLog.getInstance().logEvent(new Event("The history scores have been cleared"));
    }

    // MODIFIES: this
    // EFFECTS: add 1 to the last times
    public void nextTimes() {
        int lastTime = lastTimesScore.getTimes();
        lastTimesScore = new Score(lastTime + 1, 0);
        EventLog.getInstance().logEvent(new Event("The last time score changed to the next times'"));
    }

    // EFFECTS: get the max score in the history score.
    //          If there are more than 1 max score, return the first one.
    public Score maxScore() {
        Score max = new Score(0, 0);
        for (Score score: scores) {
            if (score.getScore() > max.getScore()) {
                max = score;
            }
        }
        return max;
    }

    // EFFECTS: assign the new given value to the last times score
    public void setLastTimesScore(Score lastTimesScore) {
        this.lastTimesScore = lastTimesScore;
    }

    // EFFECTS: get the history score
    public String getName() {
        return name;
    }

    // EFFECTS: get the history score
    public ArrayList<Score> getScores() {
        return scores;
    }

    // EFFECTS: get the last times number
    public Score getLastTimes() {
        return lastTimesScore;
    }

    // reference JsonSerializationDemo Workroom Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("History Scores", name);
        json.put("Scores", historyScoresToJson());
        json.put("Last Times", lastTimesScore.toJson());
        EventLog.getInstance().logEvent(new Event("History score is convert to the Json format"));
        return json;
    }

    // reference JsonSerializationDemo Workroom Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: returns scores in this history score as a JSON array
    private JSONArray historyScoresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score score : scores) {
            jsonArray.put(score.toJson());
        }

        return jsonArray;
    }
}
