package ui;

import model.Game;
import model.Score;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Reference the ScorePanel in the Space InvaderBase
// URL: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// Represents the score panel that is displayed
public class ScorePanel extends JPanel {
    private static final String TIMES_TXT = "Game Times: ";
    private static final String SCORE_TXT = "Score: ";
    private static final int LBL_WIDTH = 150;
    private static final int LBL_HEIGHT = 20;

    private Game game;
    private JLabel timesLbl;
    private JLabel scoreLbl;
    private Score score;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g) {
        game = g;
        score = g.getScore();
        setBackground(new Color(180, 180, 180));
        timesLbl = new JLabel(TIMES_TXT + score.getTimes());
        timesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        scoreLbl = new JLabel(SCORE_TXT + score.getScore());
        scoreLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(timesLbl);
        add(Box.createHorizontalStrut(10));
        add(scoreLbl);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates times and score
    //           remaining to reflect current state of game
    public void update() {
        timesLbl.setText(TIMES_TXT + score.getTimes());
        scoreLbl.setText(SCORE_TXT + score.getScore());
        repaint();
    }
}
