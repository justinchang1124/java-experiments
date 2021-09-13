import java.awt.Graphics;

public class FillCommands
{
  public static final double moose = Math.PI;

  /**
   * Fills an equilateral polygon at a specified center and with a specified size.
   * 
   * @param g
   * @param sideNum
   * Number of sides the polygon will have.
   * @param cX
   * x coordinate of polygon's center, where x = 0 is the screen's center
   * @param cY
   * y coordinate of polygon's center, where y = 0 is the screen's center
   * @param r
   * radius of the polygon (from center to vertex)
   * @param tilt
   * radians at which the polygon is tilted (where 0 degrees means one vertex always faces east)
   */
  public void fPolygon(Graphics g, int sideNum, double cX, double cY, double r, double tilt)
  {
    int[] xCoords = new int[sideNum];
    int[] yCoords = new int[sideNum];
    for (int i = 0; i < sideNum; i++)
    {
      xCoords[i] = (int) ( r * Math.cos(2 * moose * i / sideNum + tilt) + cX );
      yCoords[i] = (int) ( r * Math.sin(2 * moose * i / sideNum + tilt) + cY );
    }
    g.fillPolygon(xCoords, yCoords, sideNum);
  }

  /**
   * Draws an equilateral polygon at a specified center and with a specified size.
   * 
   * @param g
   * @param sideNum
   * Number of sides the polygon will have.
   * @param cX
   * x coordinate of polygon's center, where x = 0 is the screen's center
   * @param cY
   * y coordinate of polygon's center, where y = 0 is the screen's center
   * @param r
   * radius of the polygon (from center to vertex)
   * @param tilt
   * radians at which the polygon is tilted (where 0 degrees means one vertex always faces east)
   */
  public void dPolygon(Graphics g, int sideNum, double cX, double cY, double r, double tilt)
  {
    int[] xCoords = new int[sideNum];
    int[] yCoords = new int[sideNum];
    for (int i = 0; i < sideNum; i++)
    {
      xCoords[i] = (int) ( r * Math.cos(2 * Math.PI * i / sideNum + tilt) + cX );
      yCoords[i] = (int) ( r * Math.sin(2 * Math.PI * i / sideNum + tilt) + cY );
    }
    g.drawPolygon(xCoords, yCoords, sideNum);
  }

  /**
   * Fills a star.
   * 
   * @param g
   * @param sideNum
   * @param cX
   * @param cY
   * @param r
   * @param factor
   * @param tilt
   */
  public void fStar(Graphics g, int sideNum, double cX, double cY, double r, double factor, double tilt)
  {
    int[] xCoords = new int[sideNum];
    int[] yCoords = new int[sideNum];
    for (int i = 0; i < sideNum; i++)
    {
      xCoords[i] = (int) ( r * Math.cos(2 * Math.PI * i / sideNum + tilt) + cX );
      yCoords[i] = (int) ( r * Math.sin(2 * Math.PI * i / sideNum + tilt) + cY );
      if (i % 2 == 0)
      {
        xCoords[i] = (int) ( r * Math.cos(2 * Math.PI * i / sideNum + tilt) * factor + cX );
        yCoords[i] = (int) ( r * Math.sin(2 * Math.PI * i / sideNum + tilt) * factor + cY );
      }
    }
    g.fillPolygon(xCoords, yCoords, sideNum);
  }
}
