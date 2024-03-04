package ui;

import model.Event;
import model.EventLog;
import model.HistoryScore;
import model.Score;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

// the ui that will show after you choose whether to add your score in to the history score
public class ReplayUI extends CenteredWindows {
    private static final String JSON_STORE = "./data/HistoryScores.json";
    private JButton replayButton;
    private JTable historyScoreTable;
    private JPanel replayPanel;
    private JButton quitButton;
    private DefaultTableModel defaultTableModel;
    private HistoryScore historyScore;

    // the action of save and quit button
    private ActionListener saveAndQuitAction = new ActionListener() {
        // MODIFIES: this
        // EFFECTS: save the score to the file and quit
        @Override
        public void actionPerformed(ActionEvent e) {
            saveHistoryScore();
            for (Event event: EventLog.getInstance()) {
                System.out.println(event.toString());
            }
            System.exit(0);
        }
    };

    // action for the replay button
    private ActionListener replayAction = new ActionListener() {
        // MODIFIES: this
        // EFFECTS: save the score to the file and restart the game
        @Override
        public void actionPerformed(ActionEvent e) {
            saveHistoryScore();
            BridgeBuilder bridgeBuilder = new BridgeBuilder(historyScore);
            setVisible(false);
        }
    };
    private JsonWriter jsonWriter;

    // EFFECTS: construct the replay ui
    public ReplayUI(HistoryScore historyScore) {
        super("Replay?");
        setContentPane(replayPanel);
        setSize(550, 650);
        setVisible(true);
        centreOnScreen();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jsonWriter = new JsonWriter(JSON_STORE);
        this.historyScore = historyScore;

        String[] columnNames = new String[]{"Times", "Score"};
        defaultTableModel = new DefaultTableModel(scoresTo2DObjectArray(historyScore.getScores()), columnNames);
        historyScoreTable.setModel(defaultTableModel);
        historyScoreTable.setFillsViewportHeight(true);
        pack();

        replayButton.addActionListener(replayAction);
        quitButton.addActionListener(saveAndQuitAction);
    }

    // EFFECTS: convert the scores to the object form
    private Object[][] scoresTo2DObjectArray(ArrayList<Score> scores) {
        int maxIndex = scores.size();
        Object[][] objects = new Object[scores.size()][2];
        for (int i = 0; i < maxIndex; i++) {
            objects[i][0] = scores.get(i).getTimes();
            objects[i][1] = scores.get(i).getScore();
        }
        return objects;
    }

    // reference JsonSerializationDemo WorkroomApp Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the workroom to file
    private void saveHistoryScore() {
        try {
            jsonWriter.open();
            jsonWriter.write(historyScore);
            jsonWriter.close();
            JOptionPane.showMessageDialog(replayPanel, "Game status is successfully saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(replayButton, "Unable to save the game");
        }
    }
}
