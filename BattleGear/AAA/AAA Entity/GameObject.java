
/**
 * @author Justin Chang
 * 
 *         Class that creates any entity within a game.
 *         Gives the entity a set of properties.
 *         Has getters and setters for each property.
 */

import java.awt.Graphics;

public abstract class GameObject
{
  protected double[] p = new double[Central.PROPERTY];

  protected ID id;

  public GameObject(double[] p, ID id)
  {
    for (int i = 0; i < Central.PROPERTY; i++)
    {
      this.p[i] = p[i];
    }
    this.id = id;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  // Getters and setters for all properties.

  public void setP(int entry, double value)
  {
    this.p[entry] = value;
  }

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
