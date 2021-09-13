import java.awt.Color;

/**
 * A model of a rectangle with specified center, color, dimensions, and
 * orientation.
 * 
 * @author Justin C
 */
public class Rectangle extends Model
{
  /**
   * The width and height of this rectangle.
   * 
   * orientation = 0: width is along x-axis, height is along y-axis
   * orientation = 1: width is along y-axis, height is along z-axis
   * orientation = 2: width is along z-axis, height is along x-axis
   */
  public double w, h;

  /**
   * The orientation of this rectangle.
   * 
   * orientation = 0: parallel to the XY plane
   * orientation = 1: parallel to the YZ plane
   * orientation = 2: parallel to the ZX plane
   */
  public int orientation;

  /**
   * Constructs a rectangle.
   */
  public Rectangle(Vertex center, double w, double h, Color color, int orientation)
  {
    super(center);

    if (w < 0 || h < 0 || orientation < 0 || orientation > 2)
      throw new IllegalArgumentException("A dimension of this rectangle is negative!");

    this.w = w;
    this.h = h;
    this.orientation = orientation;

    if (orientation == 0)
    {
      tris.add(new Triangle(new double[] { -w / 2, -h / 2, 0, w / 2, -h / 2, 0, -w / 2, h / 2, 0 }, color));
      tris.add(new Triangle(new double[] { w / 2, h / 2, 0, w / 2, -h / 2, 0, -w / 2, h / 2, 0 }, color));
      return;
    }

    if (orientation == 1)
    {
      tris.add(new Triangle(new double[] { 0, -w / 2, -h / 2, 0, w / 2, -h / 2, 0, -w / 2, h / 2 }, color));
      tris.add(new Triangle(new double[] { 0, w / 2, h / 2, 0, w / 2, -h / 2, 0, -w / 2, h / 2 }, color));
      return;
    }

    if (orientation == 2)
    {
      tris.add(new Triangle(new double[] { -h / 2, 0, -w / 2, h / 2, 0, -w / 2, -h / 2, 0, w / 2 }, color));
      tris.add(new Triangle(new double[] { h / 2, 0, w / 2, h / 2, 0, -w / 2, -h / 2, 0, w / 2 }, color));
      return;
    }
  }

  /**
   * Constructs a square, by default.
   */
  public Rectangle(Vertex center, double d, Color color, int orientation)
  {
    this(center, d, d, color, orientation);
  }
}
