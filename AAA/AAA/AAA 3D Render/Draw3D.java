import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * A basic 3-d rendering engine.
 * 
 * @author Justin C
 */
public class Draw3D
{
  private TripleMatrix headingTransform;

  private TripleMatrix pitchTransform;

  private TripleMatrix proj;

  public static double heading;

  public static double pitch;
  
  public static double displaceW;
  
  public static double displaceH;

  public static List<Polygon> polyList = new ArrayList<>();

  /**
   * Renders all polygons based on the given heading and pitch.
   * 
   * @param g
   */
  public void render(Graphics g)
  {
    double cH = Math.cos(heading);
    double sH = Math.sin(heading);
    double cP = Math.cos(pitch);
    double sP = Math.sin(pitch);

    headingTransform = new TripleMatrix(new double[] {cH, 0, -sH, 0, 1, 0, sH, 0, cH});
    pitchTransform = new TripleMatrix(new double[] {1, 0, 0, 0, cP, sP, 0, -sP, cP});
    proj = headingTransform.multiply(pitchTransform);
    
    for (Polygon t : polyList)
    {
      makePolygon3D(g, t);
    }
  }

  /**
   * Projects a 3-d polygon onto the screen.
   * 
   * @param g
   * @param p
   */
  public void makePolygon3D(Graphics g, Polygon p)
  {
    g.setColor(p.color);

    Vertex[] transform = new Vertex[p.sideNum];
    int[] xCoords = new int[p.sideNum];
    int[] yCoords = new int[p.sideNum];

    for (int i = 0; i < p.sideNum; i++)
    {
      transform[i] = focusV(proj.transform(p.corners[i]));
      xCoords[i] = (int) transform[i].x;
      yCoords[i] = (int) transform[i].y;
    }

    if (p.filled)
      g.fillPolygon(xCoords, yCoords, p.sideNum);
    if (!p.filled)
      g.drawPolygon(xCoords, yCoords, p.sideNum);
  }

  /**
   * Displaces the given vertex based on the screen settings.
   * 
   * @param v
   * @return
   */
  public Vertex focusV(Vertex v)
  {
    v.x += displaceW;
    v.y += displaceH;
    return v;
  }
}
