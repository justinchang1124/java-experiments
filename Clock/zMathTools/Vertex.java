/**
 * Represents a point on Re^3, normally a vertex of a polygon. New vertices are
 * always provided.
 * 
 * @author Justin C
 */
public class Vertex extends Matrix
{
  /**
   * Constructs a Vertex at (0, 0, 0).
   */
  public Vertex()
  {
    super(3, 1);
  }

  /**
   * Constructs a Vertex at (x, y, z).
   */
  public Vertex(double x, double y, double z)
  {
    super(new double[] { x, y, z });
  }

  /**
   * Constructs a Vertex from the "col" column of Matrix o.
   */
  public Vertex(Matrix o, int col)
  {
    this(o.v[0][col], o.v[1][col], o.v[2][col]);
  }

  /**
   * Constructs a Vertex from a Matrix.
   */
  public Vertex(Matrix o)
  {
    this(o, 0);
  }

  /**
   * Returns the length of the line segment from (0, 0, 0) to this Vertex.
   */
  public double length()
  {
    return Math.sqrt(v[0][0] * v[0][0] + v[1][0] * v[1][0] + v[2][0] * v[2][0]);
  }

  /**
   * Returns a unit Vertex parallel to the given Vertex.
   */
  public Vertex unit()
  {
    return new Vertex(dot(1 / length()));
  }

  /**
   * Returns the cross product "this x o".
   */
  public Vertex cross(Vertex o)
  {
    return new Vertex(

        v[1][0] * o.v[2][0] - v[2][0] * o.v[1][0],

        v[2][0] * o.v[0][0] - v[0][0] * o.v[2][0],

        v[0][0] * o.v[1][0] - v[1][0] * o.v[0][0]

    );
  }

  /**
   * Returns the vector from "this" to "o".
   */
  public Vertex vector(Vertex o)
  {
    return new Vertex(o.add(this.dot(-1)));
  }

  public String toString()
  {
    return "(" +

        Math.round(v[0][0] * 100) / 100.0 + ", " +

        Math.round(v[1][0] * 100) / 100.0 + ", " +

        Math.round(v[2][0] * 100) / 100.0 + ")";
  }
}
