import java.awt.event.KeyEvent;

/**
 * A set of static methods that govern the movement of a tradition WASD-governed
 * player character. Defaults to SPACE and SHIFT for jumping and descending.
 * 
 * @author Justin Chang
 */
public class Movement
{
  /**
   * The player's coordinates and the absolute magnitude of their current
   * velocity.
   */
  public static double pX, pY, pZ, vel = 10;

  /**
   * A set of private integers used to help with an exceptionally smooth
   * keyboard navigation system.
   */
  private static int x1, x2, y1, y2, z1, z2;

  /**
   * Resets the player's position to the origin, wherever it may be.
   */
  public static void reset()
  {
    pX = 0;
    pY = 0;
    pZ = 0;
  }

  /**
   * Standard movement controls. (Example: A 2D side scroller.)
   */
  public static void tick()
  {
    tick(0);
  }

  /**
   * Moves the player in a direction based on the camera. (Ex: A 3D landscape).
   * 
   * @param heading
   * The player's clockwise orientation from the x-axis in radians.
   */
  public static void tick(double heading)
  {
    int netX = x1 - x2;
    int netY = y1 - y2;
    int netZ = z1 - z2;
    pX += (netX * Math.cos(heading) + netY * Math.cos(heading + Math.PI / 2)) * vel;
    pY += (netX * Math.sin(heading) + netY * Math.sin(heading + Math.PI / 2)) * vel;
    pZ += netZ * vel;
  }

  public static void pressKeys(int keyCode)
  {
    if (keyCode == KeyEvent.VK_SHIFT)
      z2 = 1;
    if (keyCode == KeyEvent.VK_W)
      x2 = 1;
    if (keyCode == KeyEvent.VK_A)
      y2 = 1;
    if (keyCode == KeyEvent.VK_S)
      x1 = 1;
    if (keyCode == KeyEvent.VK_D)
      y1 = 1;
    if (keyCode == KeyEvent.VK_SPACE)
      z1 = 1;
  }

  public static void releaseKeys(int keyCode)
  {
    if (keyCode == KeyEvent.VK_SHIFT)
      z2 = 0;
    if (keyCode == KeyEvent.VK_W)
      x2 = 0;
    if (keyCode == KeyEvent.VK_A)
      y2 = 0;
    if (keyCode == KeyEvent.VK_S)
      x1 = 0;
    if (keyCode == KeyEvent.VK_D)
      y1 = 0;
    if (keyCode == KeyEvent.VK_SPACE)
      z1 = 0;
  }
}
