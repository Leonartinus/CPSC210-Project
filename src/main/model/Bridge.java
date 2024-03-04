package model;

// the bridge is the object that the player can control length that pass the ball to the destination
public class Bridge {
    public static final int X_LEFT = Ball.X_ORIGIN + Ball.RADIUS + 5; // left edge x position of the bridge
    public static final int Y_LEFT = Ball.Y - Ball.RADIUS; // left edge y position
    public static final int MAX_LENGTH = Game.HEIGHT - Stage.HEIGHT; // max length of the bridge
    public static final int CHANGING_RATE = 5; // the changing rate of the bridge
    public static final int CHANGING_DEGREE = 5; // the changing rate of the bridge degrees

    private int length;
    private int direction;
    private int degree;

    // Constructor of Bridge
    // EFFECTS: create a new Bridge with 0 length and + direction
    public Bridge() {
        length = 0;
        direction = 1;
        degree = 0;
    }

    // MODIFIES: this
    // EFFECTS: increase the length of the bridge with a constant rate
    public void build() {
        length += direction * CHANGING_RATE;
    }

    // MODIFIES: this
    // EFFECTS: increase the degree of the bridge
    public void rotate() {
        degree += CHANGING_DEGREE;
    }

    // MODIFIES: this
    // EFFECTS: change to opposite direction if hit the boundary
    public void handleBoundary() {
        if ((length >= MAX_LENGTH) || (length <= 0)) {
            direction = -direction;
        }
    }

    // MODIFIES: this
    // EFFECTS: reset the bridge to initial state
    public void reset() {
        length = 0;
        direction = 1;
        degree = 0;
    }

    // EFFECTS: get the length of the bridge
    public int getLength() {
        return length;
    }

    // EFFECTS: get the rate change of the bridge
    public int getDirection() {
        return direction;
    }

    // EFFECTS: get the degree of the bridge
    public int getDegree() {
        return degree;
    }
}
