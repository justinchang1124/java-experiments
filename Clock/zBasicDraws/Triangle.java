import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A triangle that can be drawn on a BufferedImage.
 * 
 * @author Justin C
 */
public class Triangle
{
  /**
   * The three vertices of the triangle.
   */
  public Vertex v1, v2, v3;

  /**
   * The color of the triangle.
   */
  public Color color;

  /**
   * Whether this triangle will be rendered with shadows.
   */
  public boolean shadows;

  /**
   * All triangles that do not belong to a Model may be rendered through this.
   */
  public static ArrayList<Triangle> pipeline = new ArrayList<Triangle>();

  /**
   * The currently highlighted triangle.
   */
  public static Triangle highlighted;

  /**
   * Constructs a Triangle from 3 given vertices, a color, and a shadows
   * constant.
   */
  public Triangle(Vertex o1, Vertex o2, Vertex o3, Color color, boolean shadows)
  {
    v1 = o1;
    v2 = o2;
    v3 = o3;
    this.color = color;
    this.shadows = shadows;
  }

  /**
   * Constructs a Triangle from an array of 9 doubles, a color, and a shadows
   * constant.
   */
  public Triangle(double[] xyz, Color color, boolean shadows)
  {
    if (xyz.length != 9)
      throw new IllegalArgumentException(

          "Triangle can only be initialized with an array of length 9!");

    v1 = new Vertex(xyz[0], xyz[1], xyz[2]);
    v2 = new Vertex(xyz[3], xyz[4], xyz[5]);
    v3 = new Vertex(xyz[6], xyz[7], xyz[8]);
    this.color = color;
    this.shadows = shadows;
  }

  /**
   * Default: shadows = false
   */
  public Triangle(Vertex o1, Vertex o2, Vertex o3, Color color)
  {
    this(o1, o2, o3, color, false);
  }

  /**
   * Default: shadows = false
   */
  public Triangle(double[] xyz, Color color)
  {
    this(xyz, color, false);
  }

  /**
   * Copy constructor.
   */
  public Triangle(Triangle o)
  {
    this(o.v1, o.v2, o.v3, o.color, o.shadows);
  }

  /**
   * Draws the Triangle on a given BufferedImage.
   * 
   * Consider a Triangle on R^2 with vertices v1, v2, v3. Then barycentric
   * geometry implies that every point v within the triangle can be expressed as
   * v = v1 * b1 + v2 * b2 + v3 * b3, where b1, b2, b3 are positive real numbers
   * that sum to 1. (b1, b2, b3) are called the barycentric coordinates of v and
   * are unique.
   * 
   * Interpolation for a vertex attribute "w" corresponding to point v satisfies
   * w = w1 * b1 + w2 * b2 + w3 * b3, where w1, w2, w3 are the weights that
   * correspond to v1, v2, v3. Barycentric geometry implies that (b1, b2, b3) =
   * ([v2 v3 v]/[v1 v2 v3], [v3 v1 v]/[v1 v2 v3], [v1 v2 v]/[v1 v2 v3]), where
   * [ABC] is the area of triangle ABC.
   * 
   * @param zBuffer
   * An array of doubles that correspond to the previously filled depths of each
   * pixel.
   * 
   * @param near
   * The distance of the near clipping plane from the camera.
   * 
   * @param zoom
   * The zoom factor (ex: (width, height) * 1.1)
   */
  public BufferedImage draw(

      BufferedImage img, double[] zBuffer, Basis b, double near, double zoom)
  {
    // Use constants to minimize function calls.
    int w = img.getWidth();
    int h = img.getHeight();
    Vertex center = new Vertex(0, w / 2, h / 2);

    // Rotate and scale the vertices of the triangle to this basis, then make
    // the center of the screen the origin.
    Vertex v1 = new Vertex(b.scale(b.G_L(this.v1), near * zoom).add(center));
    Vertex v2 = new Vertex(b.scale(b.G_L(this.v2), near * zoom).add(center));
    Vertex v3 = new Vertex(b.scale(b.G_L(this.v3), near * zoom).add(center));

    // Minimize the amount of rendering that needs to be done by finding the
    // bounds of the smallest rectangle that this triangle can be inscribed
    // inside. (left, right, bottom, top)
    double yL = Math.min(v1.v[1][0], Math.min(v2.v[1][0], v3.v[1][0]));
    double yR = Math.max(v1.v[1][0], Math.max(v2.v[1][0], v3.v[1][0]));
    double zB = Math.min(v1.v[2][0], Math.min(v2.v[2][0], v3.v[2][0]));
    double zT = Math.max(v1.v[2][0], Math.max(v2.v[2][0], v3.v[2][0]));

    // If the triangle is out-of-frame, return the original img.
    if (yL > w - 1 || yR < 0 || zB > h - 1 || zT < 0)
      return img;

    // Convert the rectangle bounds into integer pixel coordinates.
    int minY = (int) Math.max(0, Math.ceil(yL));
    int maxY = (int) Math.min(w - 1, Math.floor(yR));
    int minZ = (int) Math.max(0, Math.ceil(zB));
    int maxZ = (int) Math.min(h - 1, Math.floor(zT));

    // Top-left corner of the bounding rectangle, where rasterization begins.
    Vertex start = new Vertex(0, minY, minZ);

    // Twice the area of the triangle.
    double area = edge(v1, v2, v3);

    // Constants used to increment start's y, z coordinates.
    double y1_s = (v3.v[2][0] - v2.v[2][0]) / area;
    double y2_s = (v1.v[2][0] - v3.v[2][0]) / area;
    double y3_s = (v2.v[2][0] - v1.v[2][0]) / area;
    double z1_s = (v2.v[1][0] - v3.v[1][0]) / area;
    double z2_s = (v3.v[1][0] - v1.v[1][0]) / area;
    double z3_s = (v1.v[1][0] - v2.v[1][0]) / area;
    double b1_s = edge(v2, v3, start) / area;
    double b2_s = edge(v3, v1, start) / area;
    double b3_s = edge(v1, v2, start) / area;

    // Compute the inverses of the x-coordinates.
    double x1_i = 1 / v1.v[0][0];
    double x2_i = 1 / v2.v[0][0];
    double x3_i = 1 / v3.v[0][0];

    // Creates a color for the triangle that accounts for shadows.
    int normalColor = color.getRGB();
    if (shadows)
    {
      Vertex n = v1.vector(v2).cross(v1.vector(v3));
      normalColor = getShade(color, Math.abs(n.v[0][0] / n.length())).getRGB();
    }

    // Creates a color for the triangle that also accounts for the highlight
    // feature. The order of color assignment vs highlighted assignment means
    // that no triangle will be colored white on the first pass; only when the
    // highlighted triangle is actually drawn.
    int finalColor = normalColor;
    if (highlighted == this)
      finalColor = Color.white.getRGB();

    // If the triangle contains the center of the screen and is in front of all
    // previous triangles, make it the highlighted triangle.
    double b1_c = edge(v2, v3, center) / area;
    double b2_c = edge(v3, v1, center) / area;
    double b3_c = edge(v1, v2, center) / area;
    if (b1_c >= 0 && b2_c >= 0 && b3_c >= 0)
    {
      double depth_c = 1 / (b1_c * x1_i + b2_c * x2_i + b3_c * x3_i);
      if (depth_c < -near && zBuffer[(h + 1) * w / 2] < depth_c)
        highlighted = this;
    }
    // If the triangle does not contain the center, reset its color.
    else
      finalColor = normalColor;

    // For every pixel on the screen ...
    for (int y = minY; y <= maxY; y++)
    {
      double b1 = b1_s;
      double b2 = b2_s;
      double b3 = b3_s;

      for (int z = minZ; z <= maxZ; z++)
      {
        // If the triangle contains the pixel ...
        if (b1 >= 0 && b2 >= 0 && b3 >= 0)
        {
          // Use interpolation to find the depth; the vertex attribute is the
          // depth inverse because all points have been scaled by 1/v[0][0].
          double depth = 1 / (b1 * x1_i + b2 * x2_i + b3 * x3_i);
          int zIndex = z * w + y;

          // The highlighted triangle temporarily skips the depth check, But
          // still sets the proper zBuffer depth.
          if (highlighted == this)
            depth++;

          // If the pixel isn't behind the camera and meets the depth check ...
          if (depth < -near && zBuffer[zIndex] < depth)
          {
            if (highlighted == this)
              depth--;

            img.setRGB(y, h - 1 - z, finalColor);
            zBuffer[zIndex] = depth;
          }
        }
        b1 += z1_s;
        b2 += z2_s;
        b3 += z3_s;
      }
      b1_s += y1_s;
      b2_s += y2_s;
      b3_s += y3_s;
    }
    return img;
  }

  /**
   * Returns twice the signed area of the triangle formed by the vertices v1,
   * v2, v3. Orienting vertices in counterclockwise order returns a positive
   * value.
   */
  public static double edge(Vertex v1, Vertex v2, Vertex v3)
  {
    return (v3.v[1][0] - v1.v[1][0]) * (v2.v[2][0] - v1.v[2][0])

        - (v3.v[2][0] - v1.v[2][0]) * (v2.v[1][0] - v1.v[1][0]);
  }

  /**
   * Shades a color with rough logarithmic scaling.
   * 
   * Attribution:
   * https://gist.github.com/Rogach/f3dfd457d7ddb5fcfd99/
   * 4f2aaf20a468867dc195cdc08a02e5705c2cc95c
   * 
   * @param shade
   * The factor by which the color should be darkened.
   * Must satisfy 0 < shade < 1.
   */
  public static Color getShade(Color color, double shade)
  {
    shade = Math.sqrt(shade);
    double redLinear = Math.pow(color.getRed(), 2.4) * shade;
    double greenLinear = Math.pow(color.getGreen(), 2.4) * shade;
    double blueLinear = Math.pow(color.getBlue(), 2.4) * shade;

    int red = (int) Math.pow(redLinear, 1 / 2.4);
    int green = (int) Math.pow(greenLinear, 1 / 2.4);
    int blue = (int) Math.pow(blueLinear, 1 / 2.4);

    return new Color(red, green, blue);
  }

  /**
   * Returns a string representation of the triangle.
   */
  public String toString()
  {
    String s = "Vertices: " + v1 + v2 + v3 + "\n" + "Color: " + color;

    if (shadows)
      return s + " with shadows";
    return s + " without shadows";
  }
}