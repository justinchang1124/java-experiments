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
  public Display(int width, int height, String title, Loader loader)
  {
    JFrame frame = new JFrame(title);
    Dimension d = new Dimension(width, height);

    // Sets and locks the size of the frame.
    frame.setPreferredSize(d);
    frame.setMinimumSize(d);
    frame.setMaximumSize(d);
    frame.setResizable(false);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(loader);
    loader.start();
  }

}
