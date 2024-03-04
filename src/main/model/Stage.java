package model;

// the stage is the object that the ball can stand, if the ball is off the stage after movement, then the game is over
public class Stage {
    public static final int HEIGHT = 100;
    public static final int Y = Game.HEIGHT - HEIGHT / 2;

    private int width;
    private int positionX;

    // EFFECTS: create a new stage with given width and X coordinate
    public Stage(int width, int positionX) {
        this.width = width;
        this.positionX = positionX;
    }

    // EFFECTS: get the value of stage width
    public int getWidth() {
        return width;
    }

    // EFFECTS: get the value of stage x position
    public int getPositionX() {
        return positionX;
    }

    // EFFECTS: get the left edge of the stage
    public int getLeftEdge() {
        return (positionX - width / 2);
    }

    // EFFECTS: get the right edge of the stage
    public int getRightEdge() {
        return (positionX + width / 2);
    }
}
