/*
 * Attribution:
 * Thanks to RealTutsGML for the buffer strategy found on lines 163-171.
 */

import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Graphics;

/**
 * Loader is a framework upon which a program can be built. While running, the thread will tick at
 * the inputed frequency and render at the maximal rate possible. Additionally, the number of frames
 * - or the number of times the program has rendered in the last time period elapsed - is recorded
 * at the inputed frequency. Loader also constructs a Display by passing the necessary parameters.
 * 
 * @author Justin C
 */
public class Loader extends Canvas implements Runnable
{
  private Thread thread;

  private boolean running;

  // The purpose of frameCount is to enable frame testing and
  // the construction of an FPS counter.
  private int frameCount;

  private double tickNum;

  private double frameNum;

  private Central central;

  /**
   * Constructs a new Loader and initializes the program from the Central class.
   * Adds keyboard, mouse, and mouse wheel input to the Loader.
   * 
   * @param width
   * Passed to a new Display.
   * @param height
   * Passed to a new Display.
   * @param title
   * Passed to a new Display.
   * @param tickNum
   * Number of ticks per second at which the program will run.
   * @param frameNum
   * Number of times the frameCount will update per second.
   */
  public Loader(int width, int height, String title, double tickNum, double frameNum)
  {
    this.tickNum = tickNum;
    this.frameNum = frameNum;

    central = new Central();
    central.initialize();

    this.addKeyListener(new TakeInput(central));
    this.addMouseListener(new TakeInput(central));
    this.addMouseMotionListener(new TakeInput(central));
    this.addMouseWheelListener(new TakeInput(central));
    this.setFocusable(true);
    this.setFocusTraversalKeysEnabled(false);
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
   * ns
   * the number of nanoseconds that elapse per tick.
   * 
   * ms
   * the number of milliseconds that elapse per frame update.
   * 
   * stored
   * the number of ticks that must be called before the program proceeds.
   * 
   * frames
   * The number of times that the program has rendered in this iteration.
   */
  public void run()
  {
    // Requests that this screen be the foremost one on the physical display.
    this.requestFocus();

    double ns = 1000000000 / tickNum;
    double ms = 1000 / frameNum;

    double stored = 0;
    long frames = 0;

    long lastNano = System.nanoTime();
    long lastMill = System.currentTimeMillis();

    while (running)
    {
      // Adds the number of ticks elapsed during the last iteration to "stored".
      long nowNano = System.nanoTime();
      double ticksElapsed = ( nowNano - lastNano ) / ns;
      lastNano = nowNano;
      stored += ticksElapsed;

      while (stored >= 1)
      {
        tick();
        stored--;
      }

      render();
      frames++;

      // Restarts the frame count and updates the calculated FPS every second.
      long nowMill = System.currentTimeMillis();
      double secElapsed = ( nowMill - lastMill ) / ms;
      if (secElapsed >= 1)
      {
        lastMill += 1000;
        frameCount = (int) frames;
        frames = 0;
      }
    }
    // Allows "running = false" to immediately terminate the program.
    stop();
  }

  /**
   * Calls central.tick(int).
   */
  private void tick()
  {
    central.tick(frameCount);
  }

  /**
   * Creates a buffer for the program's graphics and passes it,
   * along with the frameCount, to central.render(Graphics, int).
   * Proceeds to release the system resources used by g and make 
   * the buffer strategy visible.
   */
  private void render()
  {
    BufferStrategy bufferStrategy = this.getBufferStrategy();

    if (bufferStrategy == null)
    {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bufferStrategy.getDrawGraphics();

    central.render(g, frameCount);

    g.dispose();
    bufferStrategy.show();
  }
}