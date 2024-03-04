package ui;

import model.Game;
import model.Score;
import model.HistoryScore;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Reference the SpaceInvaders Class in B02-SpaceInvadersBase
// URL: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// The game UI with Score panel game panel and save button
public class BridgeBuilder extends CenteredWindows {
    private static final String JSON_STORE = "./data/HistoryScores.json";
    private static final int INTERVAL = 30;
    private Game game;
    private Score score;
    private HistoryScore historyScore;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private JButton saveButton;
    private JsonWriter jsonWriter;
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            historyScore.setLastTimesScore(score);
            saveHistoryScore();
        }
    };

    public BridgeBuilder(HistoryScore historyScore) {
        super("Bridge Builder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            this.setIconImage(ImageIO.read(new File("./data/logo.jpg")));
        } catch (IOException ex) {
            //do smth
        }

        setUndecorated(false);
        this.historyScore = historyScore;
        game = new Game(historyScore);
        saver();
        saveButton = new JButton("SAVE");
        score = historyScore.getLastTimes();
        add(gamePanel);
        add(scorePanel, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        add(saveButton, BorderLayout.SOUTH);
        saveButton.setFocusable(false);
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();

        saveButton.addActionListener(actionListener);

    }

    // MODIFIES: this
    // EFFECTS： create game panel, score panel and the jsonWriter to save the lines for the constructor
    public void saver() {
        gamePanel = new GamePanel(game);
        scorePanel = new ScorePanel(game);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gamePanel.repaint();
                scorePanel.update();

                if (game.getIsOver()) {
                    GameOverUI gameOverUI = new GameOverUI(historyScore);
                    setVisible(false);
                    ((Timer) ae.getSource()).stop();
                }
            }
        });

        t.start();
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressing(e.getKeyCode());
        }
    }

    // reference JsonSerializationDemo WorkroomApp Class
    // URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the workroom to file
    private void saveHistoryScore() {
        try {
            jsonWriter.open();
            jsonWriter.write(historyScore);
            jsonWriter.close();
            JOptionPane.showMessageDialog(gamePanel, "Game status is successfully saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(gamePanel, "Unable to save the game");
        }
    }
}
