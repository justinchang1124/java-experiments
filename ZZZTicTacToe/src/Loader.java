
/*
 * Attribution:
 * Thanks to RealTutsGML for the buffer strategy found on lines 42-53.
 */

import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 * Loader is a framework upon which a program can be built by
 * constructing a Display by passing the necessary parameters.
 * 
 * @author Justin C
 */
public class Loader extends Canvas 
{
  private Central central;

  /**
   * Constructs a new Loader and adds the mouse listeners.
   */
  public Loader(int width, int height, String title)
  {
    central = new Central();
    central.initialize();

    this.addMouseListener(new TakeInput(central));
    this.addMouseMotionListener(new TakeInput(central));
    new Display(width, height, title, this);
  }
  
  /**
   * Creates a buffer for the program's graphics and passes it,
   * along with the frameCount, to central.render(Graphics, int).
   * Proceeds to release the system resources used by g and make
   * the buffer strategy visible.
   */
  public void render()
  {
    BufferStrategy bufferStrategy = this.getBufferStrategy();

    if (bufferStrategy == null)
    {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bufferStrategy.getDrawGraphics();
    central.render(g);
    g.dispose();
    bufferStrategy.show();
  }
}