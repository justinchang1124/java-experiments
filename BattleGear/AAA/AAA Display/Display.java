
/**
 * @author Justin Chang
 * 
 *         The purpose of this class is to create a JFrame, on which the program will be displayed.
 * 
 *         Attribution:
 *         RealTutsGML
 */

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Display extends Canvas
{

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
