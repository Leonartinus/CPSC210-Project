package ui;

import model.HistoryScore;
import model.Score;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartUI extends CenteredWindows {
    private static final String JSON_STORE = "./data/HistoryScores.json";

    private JButton newGameButton;
    private JPanel startPanel;
    private JButton resumeButton;
    private HistoryScore historyScore;
    private Score lastTimes;
    private JsonReader jsonReader;

    // EFFECTS: construct the main manu of the game to load a game or start a new game
    public StartUI() {
        super("Bridge Builder Menu");
        setContentPane(startPanel);
        setVisible(true);
        setSize(550, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreOnScreen();
        jsonReader = new JsonReader(JSON_STORE);
        newGameButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: create a new game and jump to the next window
            @Override
            public void actionPerformed(ActionEvent e) {
                historyScore = new HistoryScore();
                BridgeBuilder gameFrame = new BridgeBuilder(historyScore);
                setVisible(false);
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            // MODIFIES: this
            // EFFECTS: load the saved game and jump to next window
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHistoryScore();
                BridgeBuilder gameFrame = new BridgeBuilder(historyScore);
                setVisible(false);
            }
        });
    }

    // reference JsonSerializationDemo WorkroomApp Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadHistoryScore() {
        try {
            historyScore = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(startPanel, "Unable to load the game");
        }
    }

    public static void main(String[] args) {
        new StartUI();
    }
}
