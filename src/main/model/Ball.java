package model;

// the ball is the object that can pass through the bridge to get the score or lose the game
public class Ball {
    public static final int RADIUS = 25; // the radius of the ball
    public static final int SPEED = 5; // the moving speed of the ball after the bridge is set
    public static final int Y = Game.HEIGHT - Stage.HEIGHT - RADIUS; // the y position of the ball
    public static final int X_ORIGIN = 2 * RADIUS; // the initial x value

    private int coordinateX;

    // Constructor of Ball
    // EFFECTS: create a new ball set to the initial x value
    public Ball() {
        coordinateX = X_ORIGIN;
    }

    // MODIFIES: this
    // EFFECTS: move the ball with the constant SPEED horizontally
    public void move() {
        coordinateX += SPEED;
    }

    // MODIFIES: this
    // EFFECTS: reset the x value to origin
    public void reset() {
        coordinateX = X_ORIGIN;
    }

    // EFFECTS: get the x coordinate of the ball
    public int getCoordinateX() {
        return coordinateX;
    }
}
