
/**
 * @author Justin Chang
 * 
 *         The purpose of this class is to create a framework on which a game can be built.
 *         All tick methods and render methods can be placed in the appropriate spots
 *         and it can be guaranteed that they will tick at the rate supplied and render
 *         consistently.
 * 
 *         Attribution:
 *         Buffer strategy from RealTutsGML: Lines 163-171
 */

import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Graphics;

public class Loader extends Canvas implements Runnable
{
  private Thread thread;

  private boolean running;

  // The purpose of frameCount is to allow for simplified maintenance and testing.
  // Additionally, an FPS counter can easily be constructed.
  private long frameCount;

  private double tickNum;

  private double frameNum;

  private Central central;

  public Loader(int screenWidth, int screenHeight, double tickNum, double frameNum, String title)
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
    new Display(screenWidth, screenHeight, title, this);
  }

  // Creates a new thread and immediately evokes the run() method.
  public synchronized void start()
  {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  // This causes the thread and the run method that it uses to be terminated.
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

  public void run()
  {
    // Requests that this screen be the foremost one on the physical display.
    this.requestFocus();

    // ns is the amount of nanoseconds per tick.
    // ms is the amount of milliseconds per frame update.
    double ns = 1000000000 / tickNum;
    double ms = 1000 / frameNum;

    // ticksStored represents the number of ticks that the program must execute
    // before checking the time again.
    double ticksStored = 0;
    // Frames represents the number of times the while loop has occurred.
    long frames = 0;

    long lastNano = System.nanoTime();
    long lastMill = System.currentTimeMillis();

    while (running)
    {
      // Calculates how many ticks have elapsed since the last check
      // and adds the amount to ticksStored.
      long nowNano = System.nanoTime();
      double ticksElapsed = ( nowNano - lastNano ) / ns;
      lastNano = nowNano;
      ticksStored += ticksElapsed;

      // Ticks the game an appropriate number of times.
      while (ticksStored >= 1)
      {
        tick();
        ticksStored--;
      }

      // Renders the screen.
      render();

      // Increase the frames counted by 1.
      frames++;

      // Calculates how many seconds have elapsed since the last check
      // and restarts the frame count & updates the FPS if a second has passed.
      long nowMill = System.currentTimeMillis();
      double secElapsed = ( nowMill - lastMill ) / ms;
      if (secElapsed >= 1)
      {
        lastMill += 1000;
        frameCount = frames;
        frames = 0;
      }
    }
    // Allows setting running = false to immediately terminate the program.
    stop();
  }

  private void tick()
  {
    central.tick(frameCount);
  }

  private void render()
  {
    // Creates a buffer for the program's graphics.
    BufferStrategy bufferStrategy = this.getBufferStrategy();

    if (bufferStrategy == null)
    {
      this.createBufferStrategy(3);
      return;
    }

    Graphics g = bufferStrategy.getDrawGraphics();

    central.render(g, frameCount);

    // Disposes of the current set of graphics and makes the buffer visible.
    g.dispose();
    bufferStrategy.show();
  }
}