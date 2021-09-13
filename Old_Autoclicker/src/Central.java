import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Provides a logical, organized, and sanitized implementation of the AAA grouping.
 * 
 * @author Justin C
 */
public class Central extends TempCentral
{
  // All final variables.
  public static final int SCREEN_WIDTH = 1000;

  public static final int SCREEN_HEIGHT = 1200;

  public static final int OBJ_P = 7;

  // All key and mouse inputs.
  private int keyCode;

  private int xM;

  private int yM;

  private int eventKeyboard;

  // All sounds.

  // All backgrounds and sprites.
  public static final String b_g_files[] = {};

  public static final String sprites[] = {};

  // All classes.

  // All constants.
  public static double pX, pY;

  public static double vX, vY;
  
  public static boolean clickEnable;

  /**
   * Initializes the program.
   */
  public void initialize()
  {
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
    if (clickEnable)
    {
    ForceAction.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    ForceAction.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//    ForceAction.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//    ForceAction.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
  }
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
    if (keyCode == KeyEvent.VK_P && eventKeyboard == 1)
    {
      clickEnable = !clickEnable;
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
    xM = xMouse;
    yM = yMouse;
  }

  /**
   * Manages the mouse wheel.
   * 
   * @param wRotation
   */
  public void eventWheelOutput(int wRotation)
  {

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
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "Central", 2, 1);
  }
}
