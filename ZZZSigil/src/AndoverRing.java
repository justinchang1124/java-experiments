import java.awt.Color;
import java.awt.Graphics;

public class AndoverRing
{
  private int num;
  private double x;
  private double y;
  private double radius;
  private Color color;
  private double arc;
  private double initArc;
  
  public AndoverRing(int iterationNum, double radius, double x, double y, double arc, double initArc, Color color)
  {
    this.num = iterationNum;
    this.radius = radius;
    this.x = x;
    this.y = y;
    this.color = color;
    this.arc = arc;
    this.initArc = initArc; 
  }
  
  public void draw(Graphics g)
  {
    for (int j = 0; j < num; j++)
    {
      double tiltGen = arc/num;
      Arrow a = new Arrow(x + radius*Math.cos(tiltGen*j + initArc), y + radius*Math.sin(tiltGen*j+initArc), tiltGen*j+initArc, color);
      a.draw(g);
    }
  }
}
