
/**
 * CSC 500, Ms. Bednarcik, Period 1
 * 
 * The meanings and how it applies to my life:
 * 
 * Blue and white were chosen because I am an Andover student and because blue is my favorite color.
 * 
 * The magenta is faded because it has shades of red, and Exeter <<< Andover.
 * 
 * Additionally, I created a lot of thin lines around the edges to demonstrate how
 * I believe that the world operates by straightforward laws that can nonetheless yield
 * tremendous complexity.
 * 
 * The randomness that I used to generate the triangles indicates my belief that, although the
 * universe may operate in a deterministic way, the methods by which it does so can be so
 * incomprehensible as to be practically random in some cases.
 * 
 * @author Justin C
 * @author N. Zufelt
 * @author M. Bednarcik
 * @due 12/11/2017
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Sigil extends JFrame
{
  private int height = 1200;

  private int width = 1200;

  private double rotation = 0;
  
  private boolean redExpand = false;
  
  private int redRadius = 0;

  private long timer = 0;
  
//  private long shadow = 0;
  
 // private long shadow = 0;

  public static final double moose = Math.PI;

  private AndoverRing a;

  public Sigil()
  {
    setLocation(0, 0);
    setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void paint(Graphics g)
  {
    if (redRadius >= 400)
    {
      redExpand = false;
    }
    if (redRadius <= 0)
    {
      redExpand = true;
    }
    if (redExpand)
    {
      redRadius+=20;
    }
    if (!redExpand)
    {
      redRadius-=20;
    }
    // Repaints the background.
    
//    shadow ++;
//    
//    if (shadow % 40 == 0)
//    {
//      shadow = 0;
//      g.setColor(Color.white);
//      g.fillRect(0, 0, getWidth(), getHeight());
//    }

    a = new AndoverRing(10, 400-redRadius, getWidth() / 2, getHeight() / 2, 2*moose, 0 + rotation, Color.blue);
    a.draw(g);
    a = new AndoverRing(10, redRadius, getWidth() / 2, getHeight() / 2, 2*moose, 0 - rotation + moose, Color.red);
    a.draw(g);
//    a = new AndoverRing(20, 100, getWidth() / 2, getHeight() / 2, moose, moose + rotation, Color.red);
//    a.draw(g);
//    a = new AndoverRing(20, 200, getWidth() / 2, getHeight() / 2, moose, moose + rotation, Color.blue);
//    a.draw(g);

    rotation += moose / 60;
  }

  public static void main(String[] args)
  {
    Sigil rs = new Sigil();
    rs.timer = System.currentTimeMillis();
    while (true)
    {
      if (System.currentTimeMillis() - rs.timer >= 30)
      {
        rs.timer = System.currentTimeMillis();
        rs.repaint();
      }
    }
  }
}