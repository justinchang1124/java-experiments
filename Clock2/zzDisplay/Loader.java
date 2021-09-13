import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 * Loader is a framework upon which a program can be built. While running, the
 * thread will tick at the inputed frequency and render at the maximal rate
 * possible. Additionally, the number of frames - or the number of times the
 * program has rendered in the last time period elapsed - is recorded at the
 * inputed frequency. Loader also constructs a Display by passing the necessary
 * parameters.
 * 
 * @author Justin C
 */
public class Loader extends Canvas implements Runnable
{
  private Thread thread;
  private boolean running;
  private long fps;
  private double nanoTick;
  private Central central;

  public Loader(int width, int height, String title, double ticksPerSecond)
  {
    nanoTick = 1000000000 / ticksPerSecond;

    central = new Central();
    central.initialize();

    addKeyListener(central);
    addMouseListener(central);
    addMouseMotionListener(central);
    addMouseWheelListener(central);

    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    new Display(width, height, title, this);
  }

  /**
   * Creates a new thread and invokes run().
   */
  public synchronized void start()
  {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  /**
   * Terminates the current thread and preemptively stops all actions in run().
   */
  public synchronized void stop()
  {
    try
    {
      thread.join();
      running = false;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Automatically executes on the created Thread once it is created.
   * 
   * ticks:
   * the number of ticks that must be called before the program proceeds.
   * 
   * frames:
   * The number of times that the program has rendered in this iteration.
   */
  public void run()
  {
    // Requests that this screen be the foremost one on the physical display.
    this.requestFocus();

    double ticks = 0;
    long frames = 0;

    long lastNano = System.nanoTime();
    long lastMill = System.currentTimeMillis();

    while (running)
    {
      // Adds the number of ticks elapsed during the last iteration to "stored".
      long nowNano = System.nanoTime();
      double ticksElapsed = (nowNano - lastNano) / nanoTick;
      lastNano = nowNano;
      ticks += ticksElapsed;

      while (ticks >= 1)
      {
        central.tick(fps);
        ticks--;
      }

      render();
      frames++;

      // Restarts the frame count and updates the calculated FPS every second.
      long nowMill = System.currentTimeMillis();
      if (nowMill - lastMill >= 1000)
      {
        lastMill += 1000;
        fps = frames;
        frames = 0;
      }
    }
    // Allows "running = false" to immediately terminate the program.
    stop();
  }

  /**
   * Creates a buffer for the program's graphics and passes it, along with the
   * frameCount, to central.render. Proceeds to release the system resources
   * used by g and make the buffer strategy visible.
   * 
   * Attribution: RealTutsGML
   */
  private void render()
  {
    BufferStrategy bs = getBufferStrategy();

    if (bs == null)
    {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    central.render(g, fps);

    g.dispose();
    bs.show();
  }
}