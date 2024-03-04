package ui;

import model.Ball;
import model.Bridge;
import model.Game;
import model.Stage;

import javax.swing.*;
import java.awt.*;

// Reference the game panel in the Space InvaderBase
// URL: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
// Represents the panel in which the game is rendered.
public class GamePanel extends JPanel {
    private Game game;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(Game g) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(Color.WHITE);
        this.game = g;

    }

    // MODIFIES: g
    // EFFECTS: draw the game components
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
    }

    // MODIFIES: g
    // EFFECTS: draw the game
    private void drawGame(Graphics g) {
        drawStage(g);
        drawBall(g);
        drawBridge(g);
    }

    // MODIFIES: g
    // EFFECTS: draw the bridge in the game
    private void drawBridge(Graphics g) {
        Bridge bridge = game.getBridge();
        Color savedCol = g.getColor();
        g.setColor(Color.BLACK);
        int bridgeLength = bridge.getLength();
        Graphics2D g2 = (Graphics2D)g;
        g2.rotate(Math.toRadians(bridge.getDegree()), Bridge.X_LEFT,
                Ball.Y + Ball.RADIUS);
        g.fillRect(Bridge.X_LEFT, Ball.Y + Ball.RADIUS - bridgeLength, 5, bridgeLength);
    }

    // MODIFIES: g
    // EFFECTS: draw the ball in the game
    private void drawBall(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(Color.BLUE);
        g.fillOval(game.getBall().getCoordinateX() - Ball.RADIUS, Ball.Y - Ball.RADIUS,
                Ball.RADIUS * 2, Ball.RADIUS * 2);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS: draw the stages in the game
    private void drawStage(Graphics g) {
        Stage stage1 = Game.INITIAL_STAGE;
        Stage stage2 = game.getEndStage();
        Color savedCol = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(0, Stage.Y - Stage.HEIGHT / 2,
                stage1.getWidth(), Stage.HEIGHT);
        g.fillRect(stage2.getPositionX() - stage2.getWidth() / 2, Stage.Y - Stage.HEIGHT / 2,
                stage2.getWidth(), Stage.HEIGHT);
        g.setColor(savedCol);
    }

}
