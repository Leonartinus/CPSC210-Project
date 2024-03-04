package model;

import java.awt.event.KeyEvent;
import java.util.Random;

// Game is the object that contains the whole game control the all object in exist in the game
public class Game {
    public static final int WIDTH = 550; // the width of the window
    public static final int HEIGHT = 600; // the height of the window
    public static final Random RND = new Random();
    public static final Stage INITIAL_STAGE = new Stage(100, 50);

    private Ball ball;
    private Stage endStage;
    private Bridge bridge;

    private boolean isOver;
    private boolean isBuild;
    private boolean isDown;

    private Score score;
    private HistoryScore historyScore;

    // EFFECTS: create a game
    public Game(HistoryScore historyScore) {
        ball = new Ball();
        bridge = new Bridge();
        this.historyScore = historyScore;
        score = historyScore.getLastTimes();
        setUp();
    }

    // MODIFIES: this
    // EFFECTS: reset the position of ball and bridge and generate a new random end-stage
    public void setUp() {
        ball.reset();
        bridge.reset();
        isOver = false;
        isBuild = false;
        isDown = false;
        int randomWidth = RND.nextInt((WIDTH - INITIAL_STAGE.getWidth() - 50) - 50 + 1) + 50;
        int randomX = RND.nextInt((WIDTH - (randomWidth / 2)) - (150 + (randomWidth / 2)) + 1)
                + 150 + (randomWidth / 2);
        endStage = new Stage(randomWidth, randomX);
    }

    // MODIFIES: this
    // EFFECTS: update the game status
    public void update() {
        if (isBuild && bridge.getDegree() < 90) {
            bridge.rotate();
        } else if (bridge.getDegree() == 90) {
            isDown = true;
        }
        if (isDown && (ball.getCoordinateX() < (bridge.getLength() + bridge.X_LEFT))) {
            ball.move();
        } else if (ball.getCoordinateX() == (bridge.getLength() + bridge.X_LEFT)) {
            isGameOver();
            if (!isOver) {
                EventLog.getInstance().logEvent(new Event("Pass the bridge, positions are reset"));
                score.add1();
                setUp();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: when the player press the space bar build the bridge
    public void keyPressing(int key) {
        if (key == KeyEvent.VK_SPACE) {
            bridge.build();
            bridge.handleBoundary();
        } else if (key == KeyEvent.VK_D) {
            isBuild = true;
            EventLog.getInstance().logEvent(new Event("The bridge is build"));
        }
    }

    // MODIFIES: this
    // EFFECTS: move the ball till the end of bridge
    public void moveBall() {
        while (ball.getCoordinateX() != (bridge.getLength() + bridge.X_LEFT)) {
            ball.move();
        }
        EventLog.getInstance().logEvent(new Event("The ball reach the end of the bridge"));
    }


    // MODIFIES: this
    // EFFECTS: identify if the game is over
    public void isGameOver() {
        if ((ball.getCoordinateX() > endStage.getRightEdge())
                || (ball.getCoordinateX() < endStage.getLeftEdge())) {
            isOver = true;
            EventLog.getInstance().logEvent(new Event("The game is over"));
        }
    }

    // EFFECTS: get the Ball
    public Ball getBall() {
        return ball;
    }

    //EFFECTS: get the end stage
    public Stage getEndStage() {
        return endStage;
    }

    // EFFECTS: get the bridge
    public Bridge getBridge() {
        return bridge;
    }

    // EFFECTS: get the score value
    public Score getScore() {
        return score;
    }

    // EFFECTS: get the history scores
    public HistoryScore getHistoryScore() {
        return historyScore;
    }

    // EFFECTS: get the isOver
    public boolean getIsOver() {
        return isOver;
    }

    // EFFECTS: get the isBuild
    public boolean isBuild() {
        return isBuild;
    }

    // EFFECTS: get the isDown
    public boolean isDown() {
        return isDown;
    }
}
