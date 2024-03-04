package model;

import org.json.JSONObject;
import persistence.Writable;

// the score is the number of times that the ball pass the bridge in a game
public class Score implements Writable {
    private int times;
    private int score;

    // EFFECTS: initialized the score to the 0
    public Score(int times, int score) {
        this.times = times;
        this.score = score;
    }

    // MODIFIES: this
    // EFFECTS: score increase 1;
    public void add1() {
        score++;
        EventLog.getInstance().logEvent(new Event("The current score is updated to " + score));
    }

    // EFFECTS: get score
    public int getScore() {
        return score;
    }

    // EFFECTS: get times
    public int getTimes() {
        return times;
    }

    // reference JsonSerializationDemo Thingy Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Times", times);
        json.put("Score", score);
        EventLog.getInstance().logEvent(new Event("Turn the " + times + "th score to the json"));
        return json;
    }
}
