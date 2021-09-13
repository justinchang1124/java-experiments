import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Provides a logical, organized, and sanitized implementation of the AAA grouping.
 * 
 * @author Justin C
 */
public class Central extends TempCentral
{
  // All final variables.
  public static final int SCREEN_WIDTH = 800;

  public static final int SCREEN_HEIGHT = 800;

  public static final int OBJ_P = 7;

  // All key and mouse inputs.
  private int keyCode;

  private int xM;

  private int yM;

  private int clicks;

  private int button;

  private int wheel;

  private int eventMouse;

  private int eventKeyboard;

  // All sounds.
  private final Sound sound = new Sound("/O_S_Music.wav");

  // All backgrounds and sprites.
  public static final String b_g_files[] = {"/black_screen.png"};

  public static final String sprites[] = {};

  // All classes.
  private Saver saver;

  private IMG img;

  private Handler handler;

  private Draw3D draw3D;

  // All constants.
  public static double pX, pY;

  public static double vX, vY;

  private double xPlus, xMinus;

  private double yPlus, yMinus;

  /**
   * Initializes the program.
   */
  public void initialize()
  {
    // DEMO
    double[] unsorted = new double[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2, -3, -4};
    double[] sorted = Sorting.bubbleDouble(unsorted);
    for (int i = 0; i < sorted.length; i++)
    {
      System.out.print(sorted[i] + " ");
    }
    System.out.println();

    saver = new Saver(new int[] {5}, new int[] {5}, new int[] {5}, new int[] {5});
    img = new IMG(SCREEN_WIDTH, SCREEN_HEIGHT, b_g_files, sprites);
    handler = new Handler();
    draw3D = new Draw3D();

    Polygon p1 = new Polygon(new double[] {0, 100, 100, 0}, new double[] {0, 0, 0, 0}, new double[] {0, 0, 100, 100}, Color.green, true);
    Polygon p2 = new Polygon(new double[] {0, 0, 0, 0}, new double[] {0, 100, 100, 0}, new double[] {0, 0, 100, 100}, Color.blue, true);
    Polygon p3 = new Polygon(new double[] {0, 100, 100, 0}, new double[] {0, 0, 100, 100}, new double[] {0, 0, 0, 0}, Color.red, true);

    Draw3D.polyList.add(p1);
    Draw3D.polyList.add(p2);
    Draw3D.polyList.add(p3);

    ForceAction.makeRobot();
  }

  /**
   * Calls all tick methods.
   * 
   * @param frameCount
   */
  public void tick(int frameCount)
  {
    // DEMO
    handler.tick();
    orient();
    pX += vX;
    pY += vY;
    Draw3D.displaceW = pX + SCREEN_WIDTH / 2;
    Draw3D.displaceH = pY + SCREEN_HEIGHT / 2;
  }

  /**
   * Calls all render methods.
   * 
   * @param g
   * @param frameCount
   */
  public void render(Graphics g, int frameCount)
  {
    // DEMO
    handler.render(g);
    img.render(g, 0);
    MakeText.setTextColor(Color.white);
    MakeText.setMainFont("Comic Sans MS", 0, 40);
    MakeText.showText(g, "Hi");
    draw3D.render(g);
  }

  /**
   * Manages all keyboard input.
   * 
   * @param eventDescription
   * pressed = -1, typed = 0, released = 1
   * @param keyboardCode
   */
  public void eventKeyboardOutput(int eventDescription, int keyboardCode)
  {
    keyCode = keyboardCode;
    eventKeyboard = eventDescription;

    // DEMO
    if (eventKeyboard == 1 && keyCode == KeyEvent.VK_T)
    {
      saver.doesThisExist("/Saves", "/Game", 1);
      saver.load("/Saves", "/Game", 1);
      saver.writeString();
      saver.save("/Saves", "/Game", 1);
    }

    double playerSpeed = 5;

    if (eventKeyboard == -1)
    {
      if (keyCode == KeyEvent.VK_W)
      {
        xMinus = playerSpeed;
      }
      if (keyCode == KeyEvent.VK_A)
      {
        yMinus = playerSpeed;
      }
      if (keyCode == KeyEvent.VK_S)
      {
        xPlus = playerSpeed;
      }
      if (keyCode == KeyEvent.VK_D)
      {
        yPlus = playerSpeed;
      }
      vY = xPlus - xMinus;
      vX = yPlus - yMinus;
    }
    if (eventKeyboard == 1)
    {
      if (keyCode == KeyEvent.VK_W)
      {
        xMinus = 0;
      }
      if (keyCode == KeyEvent.VK_A)
      {
        yMinus = 0;
      }
      if (keyCode == KeyEvent.VK_S)
      {
        xPlus = 0;
      }
      if (keyCode == KeyEvent.VK_D)
      {
        yPlus = 0;
      }
      vY = xPlus - xMinus;
      vX = yPlus - yMinus;
    }
  }

  /**
   * Manages all mouse input.
   * 
   * @param eventDescription
   * entered = -3, exited = 3
   * moved = -2, dragged = 2
   * pressed = -1, clicked = 0, released = 1
   * @param xMouse
   * @param yMouse
   * @param buttonNum
   * left = 1, scroll wheel = 2, right = 3
   * @param clickCount
   */
  public void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount)
  {
    eventMouse = eventDescription;
    xM = xMouse;
    yM = yMouse;
    button = buttonNum;
    clicks = clickCount;

    // DEMO
    if (clicks > 0 && eventMouse == 1)
    {
       System.out.println("moose");
       if (button == 1)
       sound.volume(-10);
       if (button == 2)
       sound.play(false);
       if (button == 3)
       sound.volume(5);
    }
  }

  /**
   * Manages the mouse wheel.
   * 
   * @param wRotation
   */
  public void eventWheelOutput(int wRotation)
  {
    wheel = wRotation;

    // DEMO
    if (wheel > 0)
      sound.stop();
    if (wheel < 0)
      sound.loop(false);
  }

  /*
   * DEMO
   */
  public void orient()
  {
    if (mouseOver(0, 0, 200, 800))
    {
      Draw3D.heading -= 0.04;
    }
    if (mouseOver(600, 0, 200, 800))
    {
      Draw3D.heading += 0.04;
    }
    if (mouseOver(0, 0, 800, 200))
    {
      Draw3D.pitch += 0.04;
    }
    if (mouseOver(0, 600, 800, 200))
    {
      Draw3D.pitch -= 0.04;
    }
  }

  /**
   * Detects if the mouse is within the specified rectangle.
   * 
   * @param x
   * @param y
   * @param width
   * @param height
   * @return
   */
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
    {
      return true;
    }
    return false;
  }

  /**
   * Starts the program.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "Central", 60, 1);
  }
}
