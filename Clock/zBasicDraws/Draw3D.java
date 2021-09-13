import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A series of static methods for drawing objects in 3D.
 * 
 * @author Justin C
 */
public class Draw3D
{
  /**
   * Stores whether shadows are enabled for all shadow-bound Models.
   */
  public static boolean shadows;

  /**
   * Stores the XY, YZ, ZX radians of rotation for rotation-bound Models.
   */
  public static double r_XY, r_YZ, r_ZX;

  /**
   * Stores the XY, YZ, ZX rotation speeds for rotation-bound Models.
   */
  public static int v_XY, v_YZ, v_ZX;

  /**
   * Rotational speed.
   */
  public static final double V_ROTATE = 0.03;

  /**
   * Don't let anyone instantiate this class.
   */
  private Draw3D()
  {

  };

  /**
   * Generates a BufferedImage with dimension (w,h) from the camera aligned at
   * b with near clipping plane n, far clipping plane f, and zoom factor z.
   */
  public static BufferedImage getImage(int w, int h, Basis b, double n, double f, double z)
  {
    BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    int len = w * h;
    double[] zBuffer = new double[len];

    for (int i = 0; i < len; i++)
      zBuffer[i] = -f;

    for (Triangle p : Triangle.pipeline)
      img = p.draw(img, zBuffer, b, n, z);

    for (Model p : Model.pipeline)
      img = p.draw(img, zBuffer, b, n, z);

    // if (Triangle.highlighted != null)
    // img = Triangle.highlighted.draw(img, zBuffer, b, n, z);
    // Triangle.highlighted = null;

    return img;
  }

  /**
   * Increments all rotational matrices by the appropriate amount.
   */
  public static void tick()
  {
    r_XY += v_XY * V_ROTATE;
    r_YZ += v_YZ * V_ROTATE;
    r_ZX += v_ZX * V_ROTATE;
  }

  /**
   * Resets constants.
   */
  public static void reset()
  {
    r_XY = 0;
    r_YZ = 0;
    r_ZX = 0;
    v_XY = 0;
    v_YZ = 0;
    v_ZX = 0;
    shadows = false;
  }

  /**
   * Clears all pipelines.
   */
  public static void clear()
  {
    Model.pipeline = new ArrayList<Model>();
    Triangle.pipeline = new ArrayList<Triangle>();
  }

  /**
   * Takes in a keyCode from a Virtual Keyboard key that has been released and
   * appropriates adjusts
   * constants.
   */
  public static void releaseKeys(int keyCode)
  {
    if (keyCode == KeyEvent.VK_I)
      shadows = !shadows;
    if (keyCode == KeyEvent.VK_F)
      v_XY += 1;
    if (keyCode == KeyEvent.VK_G)
      v_XY -= 1;
    if (keyCode == KeyEvent.VK_H)
      v_YZ += 1;
    if (keyCode == KeyEvent.VK_J)
      v_YZ -= 1;
    if (keyCode == KeyEvent.VK_K)
      v_ZX += 1;
    if (keyCode == KeyEvent.VK_L)
      v_ZX -= 1;
  }
}
