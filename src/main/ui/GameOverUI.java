package ui;

import model.HistoryScore;
import model.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// the ui that will show when game is over
public class GameOverUI extends CenteredWindows {
    private JPanel overPanel;
    private JButton addScore;
    private JButton cancel;
    private JLabel label1;
    private JLabel label2;
    private JTable historyScoreTable;
    private JScrollPane tablePanel;
    private JButton clearButton;
    private HistoryScore historyScore;
    private DefaultTableModel defaultTableModel;
    // save Action for the save button
    private ActionListener saveAction = new ActionListener() {
        // MODIFIES: this
        // EFFECTS: add the score to the history score and go to the next times of the score and jump to next frame
        @Override
        public void actionPerformed(ActionEvent e) {
            historyScore.addScore(historyScore.getLastTimes());
            historyScore.nextTimes();
            ReplayUI replayUI = new ReplayUI(historyScore);
            setVisible(false);
        }
    };
    // cancel action for the button
    private ActionListener cancelAction = new ActionListener() {
        // MODIFIES: this
        // EFFECTS: go to the next times and jump to the next frame
        @Override
        public void actionPerformed(ActionEvent e) {
            historyScore.nextTimes();
            ReplayUI replayUI = new ReplayUI(historyScore);
            setVisible(false);
        }
    };

    // cancel action for the button
    private ActionListener clearAction = new ActionListener() {
        // MODIFIES: this
        // EFFECTS: clear the history score jump to the next frame
        @Override
        public void actionPerformed(ActionEvent e) {
            historyScore.clear();
            ReplayUI replayUI = new ReplayUI(historyScore);
            setVisible(false);
        }
    };

    // EFFECTS: construct the window of the game over
    public GameOverUI(HistoryScore hs) {
        super("Bridge Builder Game Over");
        setContentPane(overPanel);
        setSize(550, 650);
        setVisible(true);
        centreOnScreen();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        historyScore = hs;

        String[] columnNames = new String[]{"Times", "Score"};
        defaultTableModel = new DefaultTableModel(scoresTo2DObjectArray(historyScore.getScores()), columnNames);
        historyScoreTable.setModel(defaultTableModel);
        historyScoreTable.setFillsViewportHeight(true);
        pack();

        addScore.addActionListener(saveAction);
        cancel.addActionListener(cancelAction);
        clearButton.addActionListener(clearAction);
    }

    // EFFECTS: convert the scores to the object form
    private Object[][] scoresTo2DObjectArray(ArrayList<Score> scores) {
        int maxIndex = scores.size();
        Object[][] objects = new Object[maxIndex][2];
        for (int i = 0; i < maxIndex; i++) {
            objects[i][0] = scores.get(i).getTimes();
            objects[i][1] = scores.get(i).getScore();
        }
        return objects;
    }
}
