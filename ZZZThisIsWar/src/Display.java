import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Display is a Canvas that creates a JFrame that cannot be resized
 * and with the inputed width, height, and title. The Loader
 * is then added to the JFrame and its Thread is started.
 * 
 * @author Justin C
 */
public class Display extends Canvas
{

  /**
   * Constructs a new display.
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
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Adds a loader to the frame.
    frame.add(loader);
    while (true)
    {
      loader.render();
    }
  }
}