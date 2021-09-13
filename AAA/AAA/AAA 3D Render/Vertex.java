/**
 * Represents a vertex by way of saving its three coordinates.
 * 
 * Note that z faces towards the user.
 * Note that y faces to the bottom of the screen.
 * Note that z faces to the right of the screen.
 * 
 * @author Justin C
 */
public class Vertex
{
  public double x, y, z;

  /**
   * Keeps a collection of three coordinates.
   * 
   * @param x
   * @param y
   * @param z
   */
  Vertex(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Gets the distance of this Vertex from the origin.
   * 
   * @return
   */
  public double getLength()
  {
    return Math.sqrt(x * x + y * y + z * z);
  }

  /**
   * Turns this Vertex into a unit vector.
   * 
   * @return
   */
  public Vertex unitV()
  {
    return new Vertex(x / getLength(), y / getLength(), z / getLength());
  }

  /**
   * Gets the cross produce of this vertex with another vertex.
   * 
   * @param v
   * @return
   */
  public Vertex crossP(Vertex v)
  {
    return new Vertex(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
  }

  /**
   * Gets the vector from this vertex to another vertex.
   * 
   * @param v
   * @return
   */
  public Vertex vector(Vertex v)
  {
    return new Vertex(v.x - this.x, v.y - this.y, v.z - this.z);
  }
}
