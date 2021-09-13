/**
 * Represents a Basis in terms of the global Basis.
 * 
 * @author Justin C
 */
public class Basis
{
  public Matrix ijk;
  public Matrix inverse;
  public Vertex origin;

  /**
   * Constructs the global Basis.
   */
  public Basis()
  {
    this(new Matrix(), new Vertex());
  }

  /**
   * Constructs a Basis at "center" from the vectors represented by the columns
   * of "orient".
   */
  public Basis(Matrix orient, Vertex center)
  {
    ijk = orient;
    inverse = ijk.inverse();
    origin = center;
  }

  /**
   * Constructs a Basis at "center" that is first rotated counterclockwise by
   * "theta" radians around the z-axis and then rotated counterclockwise by
   * "phi" radians around the newly positioned y-axis. The direction of the
   * x-axis can be used to represent the viewpoint of a camera with a pitch of
   * "phi" and a heading of "theta".
   */
  public Basis(double phi, double theta, Vertex center)
  {
    double g = Math.cos(theta + Math.PI / 2);
    double h = Math.sin(theta + Math.PI / 2);

    ijk = Matrix.rotate(g, h, 0, phi).times(Matrix.rotate(theta, 0, 0));
    inverse = ijk.inverse();
    origin = center;
  }

  public boolean isOrthogonal()
  {
    Vertex i = new Vertex(ijk, 0);
    Vertex j = new Vertex(ijk, 1);
    Vertex k = new Vertex(ijk, 2);

    Vertex a = new Vertex(i.dot(j));
    Vertex b = new Vertex(j.dot(k));
    Vertex c = new Vertex(k.dot(i));

    return a.length() == 0 && b.length() == 0 && c.length() == 0;
  }

  public boolean isUnit()
  {
    Vertex i = new Vertex(ijk, 0);
    Vertex j = new Vertex(ijk, 1);
    Vertex k = new Vertex(ijk, 2);

    return i.length() == 1 && j.length() == 1 && k.length() == 1;
  }

  public Vertex L_G(Vertex o)
  {
    return new Vertex(ijk.times(o).add(origin));
  }

  public Vertex G_L(Vertex o)
  {
    return new Vertex(inverse.times(o.add(origin.dot(-1))));
  }

  /**
   * Scales a vertex to a specific perspective along the x-axis.
   * 
   * @param factor
   * The factor by which o is scaled.
   */
  public Vertex scale(Vertex o, double factor)
  {
    double yP = o.v[1][0] * factor / Math.abs(o.v[0][0]);
    double zP = o.v[2][0] * factor / Math.abs(o.v[0][0]);

    return new Vertex(o.v[0][0], yP, zP);
  }

  public String toString()
  {
    String s = "*** Begin Basis ***" + "\n";
    s += ijk + "\n" + inverse + "\n" + origin + "\n";
    s += "*** End Basis ***" + "\n";
    return s;
  }
}
