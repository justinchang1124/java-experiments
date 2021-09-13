
/*
 * Attribution:
 * Thanks to RealTutsGML for explaining the features of JFrame.
 */

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Display is a Canvas that creates a JFrame that cannot be resized
 * and with the inputed width, height, and title. This JFrame is
 * centered, set visible, and set to close when exited. The Loader
 * is then added to the JFrame and its Thread is started.
 * 
 * @author Justin C
 */
public class Display extends Canvas
{

  /**
   * Constructs a new display.
   * 
   * @param width
   * The width of the screen.
   * @param height
   * The height of the screen.
   * @param title
   * The title of the JFrame.
   * @param loader
   * The loader that will be added to the JFrame.
   */
  public Display(int width, int height, String title, Loader loader)
  {
    // Creates a new JFrame with the given title.
    JFrame frame = new JFrame(title);

    // Sets and locks the size of the frame.
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setResizable(false);

    // Centers the frame.
    frame.setLocationRelativeTo(null);

    // Makes the frame visible and usable.
    frame.setVisible(true);

    // Adds the ability for the frame to be closed..
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Adds a loader to the frame.
    frame.add(loader);

    // Activates the "start" method of the loader.
    loader.start();
  }

}
