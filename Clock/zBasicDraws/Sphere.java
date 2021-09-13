import java.awt.Color;
import java.util.ArrayList;

/**
 * A Model that represents a spherical configuration.
 * 
 * @author Justin C
 */
public class Sphere extends Model
{
  /**
   * The radius of the sphere.
   */
  public double r;

  /**
   * The number of inflation iterations performed.
   */
  public int inflate;

  /**
   * Constructs a Sphere with the specified localOrigin, radius, and color.
   */
  public Sphere(Vertex localOrigin, double radius, Color color)
  {
    super(localOrigin, false, true);
    
    if (r < 0)
      throw new IllegalArgumentException("This sphere cannot have a negative radius!");
      
    r = radius;

    tris.add(new Triangle(new double[] { r, r, r, -r, -r, r, -r, r, -r }, color));
    tris.add(new Triangle(new double[] { r, r, r, -r, -r, r, r, -r, -r }, color));
    tris.add(new Triangle(new double[] { -r, r, -r, r, -r, -r, r, r, r }, color));
    tris.add(new Triangle(new double[] { -r, r, -r, r, -r, -r, -r, -r, r }, color));
  }

  /**
   * Constructs a Sphere and then inflates it the specified number of times.
   */
  public Sphere(Vertex localOrigin, double radius, Color color, int inflate)
  {
    this(localOrigin, radius, color);
    inflate(inflate);
  }

  /**
   * Inflates the current tris k times.
   */
  public void inflate(int k)
  {
    for (int i = 0; i < k; i++)
      inflate();
  }

  /**
   * Inflates the current tris once.
   */
  public void inflate()
  {
    ArrayList<Triangle> newTris = new ArrayList<Triangle>();

    for (Triangle t : tris)
    {
      Vertex m1 = new Vertex(t.v1.v[0][0] + t.v2.v[0][0], t.v1.v[1][0] + t.v2.v[1][0], t.v1.v[2][0] + t.v2.v[2][0]);
      Vertex m2 = new Vertex(t.v2.v[0][0] + t.v3.v[0][0], t.v2.v[1][0] + t.v3.v[1][0], t.v2.v[2][0] + t.v3.v[2][0]);
      Vertex m3 = new Vertex(t.v1.v[0][0] + t.v3.v[0][0], t.v1.v[1][0] + t.v3.v[1][0], t.v1.v[2][0] + t.v3.v[2][0]);

      m1 = new Vertex(m1.unit().dot(r));
      m2 = new Vertex(m2.unit().dot(r));
      m3 = new Vertex(m3.unit().dot(r));
      t.v1 = new Vertex(t.v1.unit().dot(r));
      t.v2 = new Vertex(t.v2.unit().dot(r));
      t.v3 = new Vertex(t.v3.unit().dot(r));

      newTris.add(new Triangle(t.v1, m1, m3, t.color));
      newTris.add(new Triangle(t.v2, m1, m2, t.color));
      newTris.add(new Triangle(t.v3, m2, m3, t.color));
      newTris.add(new Triangle(m1, m2, m3, t.color));
    }

    tris = newTris;
    inflate++;
  }
}
