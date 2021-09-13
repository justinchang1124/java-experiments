import java.awt.Graphics;

/**
 * GameObject creates an array of protected doubles, each of which
 * represents a property that any GameObject possesses, that each
 * have corresponding getters and setters. Additionally, it requires
 * all classes that extend it to implement a tick and render method.
 * 
 * @author Justin C
 */
public abstract class GameObject
{
  protected double[] p = new double[Central.OBJ_P];

  protected ID id;

  /**
   * Constructs a new GameObject.
   * 
   * @param p
   * Array of doubles that initialize the GameObject's properties.
   * @param id
   * ID for the object.
   */
  public GameObject(double[] p, ID id)
  {
    for (int i = 0; i < this.p.length; i++)
    {
      this.p[i] = p[i];
    }
    this.id = id;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  /**
   * Sets an entry of p to a given value.
   * 
   * @param entry
   * @param value
   */
  public void setP(int entry, double value)
  {
    this.p[entry] = value;
  }

  /**
   * Gets the value of an entry of p.
   * 
   * @param entry
   * @return
   */
  public double getP(int entry)
  {
    return p[entry];
  }

  public void setID(ID id)
  {
    this.id = id;
  }

  public ID getID()
  {
    return id;
  }
}
