import java.awt.Color;

/**
 * Creates a 3-d polygon.
 * 
 * @author Justin C
 */
public class Polygon
{
  public Vertex[] corners;
  
  public Color color;
  
  public int sideNum;
  
  public boolean filled;
  
  /**
   * Creates a new polygon with the given x, y, z coordinates.
   * Note that z faces towards the user.
   * Note that y faces to the bottom of the screen.
   * Note that z faces to the right of the screen.
   * 
   * @param xCoords
   * Array of x-coordinates.
   * @param yCoords
   * Array of y-coordinates.
   * @param zCoords
   * Array of z-coordinates.
   * @param color
   * Color of the polygon.
   * @param filled
   * Whether or not the polygon will be filled.
   */
  public Polygon(double[] xCoords, double[] yCoords, double[] zCoords, Color color, boolean filled)
  {
    this.sideNum = Math.min(Math.min(xCoords.length, yCoords.length), zCoords.length);
    this.filled = filled;
    this.color = color;
    
    corners = new Vertex[sideNum];
    
    for (int i = 0; i < sideNum; i++)
    {
      corners[i] = new Vertex(xCoords[i], yCoords[i], zCoords[i]);
    }
  }
}
