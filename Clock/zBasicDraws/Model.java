import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A configuration of triangles that can be rendered. All triangles are fixed in
 * relation to each other, but the net model can be rotated and displaced.
 * 
 * @author Justin C
 */
public class Model
{
  /**
   * A local tris that stores all triangles in relation to the localOrigin.
   */
  public ArrayList<Triangle> tris = new ArrayList<Triangle>();

  /**
   * A Vertex representing the displacement of this Model from the global
   * origin.
   */
  public Vertex localOrigin;

  /**
   * Rotational orientations of the entire Model.
   */
  public double r_XY, r_YZ, r_ZX;

  /**
   * Booleans that store whether this Model is rotation-bound or shadow-bound.
   */
  public boolean rBound, sBound;

  /**
   * Master list of all models to be rendered.
   */
  public static ArrayList<Model> pipeline = new ArrayList<Model>();

  /**
   * Constructs an empty Model at the specified localOrigin and with the
   * specified binding constants.
   */
  public Model(Vertex localOrigin, boolean r, boolean s)
  {
    this.localOrigin = new Vertex(localOrigin);
    rBound = r;
    sBound = s;
  }
  
  /**
   * Constructs an empty and unbound Model at the specified localOrigin.
   */
  public Model(Vertex localOrigin)
  {
    this(localOrigin, false, false);
  }

  /**
   * Constructs an empty Model, at the global origin and unbound by default.
   */
  public Model()
  {
    this(new Vertex());
  }

  /**
   * Copy constructor.
   */
  public Model(Model o)
  {
    this(o.localOrigin, o.rBound, o.sBound);

    for (int i = 0; i < o.tris.size(); i++)
      tris.add(new Triangle(o.tris.get(i)));
  }

  /**
   * Orients a local vertex in the global coordinate system.
   */
  private Vertex orient(Vertex r)
  {
    return new Vertex(Matrix.rotate(r_XY, r_YZ, r_ZX).times(r).add(localOrigin));
  }

  /**
   * Orients a local triangle in the global coordinate system.
   */
  public Triangle orient(Triangle r)
  {
    return new Triangle(orient(r.v1), orient(r.v2), orient(r.v3), r.color, r.shadows);
  }

  /**
   * Adds the oriented version of another model to this model.
   */
  public void append(Model other)
  {
    for (int i = 0; i < other.tris.size(); i++)
      tris.add(other.orient(other.tris.get(i)));
  }

  /**
   * Draws all triangles in the local tris.
   */
  public BufferedImage draw(BufferedImage img, double[] zBuffer, Basis b, double v, double z)
  {
    if (rBound)
    {
      r_XY = Draw3D.r_XY;
      r_YZ = Draw3D.r_YZ;
      r_ZX = Draw3D.r_ZX;
    }
    
    if (sBound)
      for (int i = 0; i < tris.size(); i++)
        tris.get(i).shadows = Draw3D.shadows;

    for (Triangle t : tris)
      img = orient(t).draw(img, zBuffer, b, v, z);
    
    return img;
  }
}
