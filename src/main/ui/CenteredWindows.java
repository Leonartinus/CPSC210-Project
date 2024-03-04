package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;

// The class contained method that can center the windows
public class CenteredWindows extends JFrame {

    public CenteredWindows(String s) {
        super(s);
        addWindowListener(new java.awt.event.WindowAdapter() {
            // EFFECTS: print the events
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                //THEN you can exit the program
                System.exit(0);
            }
        });
    }

    // Reference the method in the class SpaceInvaders in the SpaceInvaderBase
    // URL: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    protected void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }
}
