import java.awt.AWTException;
import java.awt.Robot;

/**
 * A set of methods related to the java.awt.robot class and its streamlined implementation.
 * 
 * @author Justin C
 */
public class ForceAction
{
  public static Robot robot;

  /**
   * Creates a robot.
   */
  public static void makeRobot()
  {
    try
    {
      robot = new Robot();
    }
    catch (AWTException e)
    {
      e.printStackTrace();
    }
  }
}
