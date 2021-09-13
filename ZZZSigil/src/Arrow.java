import java.awt.Color;
import java.awt.Graphics;

public class Arrow extends FillCommands
{
  private double x, y, tiltGen;
  private Color color;
  
  public Arrow(double x, double y, double tiltGen, Color color)
  {
    this.x = x;
    this.y = y;
    this.tiltGen = tiltGen;
    this.color = color;
  }
  
  public void draw(Graphics g)
  {
    for (int i = 2; i < 20; i++)
    {
      g.setColor(color);
      if (i % 2 == 1)
        g.setColor(Color.white);
     
      fPolygon(g, (int) ( Math.pow(1.1, i) ), x, y, 200 - 10 * i, tiltGen);
    }
  }
}
