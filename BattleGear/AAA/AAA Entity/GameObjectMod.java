
/**
 * @author Justin Chang
 * 
 *         Class that creates any entity within a game.
 *         Gives the entity a set of properties.
 *         Has getters and setters for each property.
 */

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObjectMod
{
  protected double x;

  protected double y;

  protected double z;

  protected double velX;

  protected double velY;

  protected double velZ;

  protected double[] p = new double[Central.PROPERTY];

  protected ID id;

  public GameObjectMod(double[] p, ID id)
  {
    this.x = p[0];
    this.y = p[1];
    this.z = p[2];
    this.velX = p[3];
    this.velY = p[4];
    this.velZ = p[5];
    for (int i = 6; i < Central.PROPERTY; i++)
    {
      this.p[i] = p[i];
    }
    this.id = id;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public abstract Rectangle getBounds();

  // Getters and setters for all properties.
  public void setX(double x)
  {
    this.x = x;
  }

  public void setY(double y)
  {
    this.y = y;
  }

  public void setZ(double z)
  {
    this.z = z;
  }

  public void setVelX(double velX)
  {
    this.velX = velX;
  }

  public void setVelY(double velY)
  {
    this.velY = velY;
  }

  public void setVelZ(double velZ)
  {
    this.velZ = velZ;
  }

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }

  public double getZ()
  {
    return z;
  }

  public double getVelX()
  {
    return velX;
  }

  public double getVelY()
  {
    return velY;
  }

  public double getVelZ()
  {
    return velZ;
  }

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
