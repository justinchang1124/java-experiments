import java.awt.Color;

/**
 * The original AxialStar model.
 * 
 * @author Justin C
 */
public class AxialStar extends Model
{
  public AxialStar(Vertex center, double d, Color co1, Color co2)
  {
    super(center, false, true);
    
    // length of a star point, from center to tip
    double l = 4*d;
    // width of a star point, from center to edge
    double w = d;
    // gap between parts of a star point
    double g = d/10;
    
    Color c1 = co1;
    Color c2 = co2;
   // Color c3 = Color.gray;
   // Color c4 = Color.gray;
    Color c5 = co2;
    Color c6 = co2;

    tris.add(new Triangle(new double[] {0, 0, l, d, g, w, g, d, w}, c1));
    tris.add(new Triangle(new double[] {0, 0, -l, d, g, -w, g, d, -w}, c2));
   // tris.add(new Triangle(new double[] {0, l, 0, d, w, g, g, w, d}, c3));
    //tris.add(new Triangle(new double[] {0, -l, 0, d, -w, g, g, -w, d}, c4));
    tris.add(new Triangle(new double[] {l, 0, 0, w, d, g, w, g, d}, c5));
    tris.add(new Triangle(new double[] {-l, 0, 0, -w, d, g, -w, g, d}, c6));

    tris.add(new Triangle(new double[] {0, 0, l, -d, -g, w, -g, -d, w}, c1));
    tris.add(new Triangle(new double[] {0, 0, -l, -d, -g, -w, -g, -d, -w}, c2));
   // tris.add(new Triangle(new double[] {0, l, 0, -d, w, -g, -g, w, -d}, c3));
   // tris.add(new Triangle(new double[] {0, -l, 0, -d, -w, -g, -g, -w, -d}, c4));
    tris.add(new Triangle(new double[] {l, 0, 0, w, -d, -g, w, -g, -d}, c5));
    tris.add(new Triangle(new double[] {-l, 0, 0, -w, -d, -g, -w, -g, -d}, c6));

    tris.add(new Triangle(new double[] {0, 0, l, d, -g, w, g, -d, w}, c1));
    tris.add(new Triangle(new double[] {0, 0, -l, d, -g, -w, g, -d, -w}, c2));
  //  tris.add(new Triangle(new double[] {0, l, 0, d, w, -g, g, w, -d}, c3));
  //  tris.add(new Triangle(new double[] {0, -l, 0, d, -w, -g, g, -w, -d}, c4));
    tris.add(new Triangle(new double[] {l, 0, 0, w, d, -g, w, g, -d}, c5));
    tris.add(new Triangle(new double[] {-l, 0, 0, -w, d, -g, -w, g, -d}, c6));

    tris.add(new Triangle(new double[] {0, 0, l, -d, g, w, -g, d, w}, c1));
    tris.add(new Triangle(new double[] {0, 0, -l, -d, g, -w, -g, d, -w}, c2));
   // tris.add(new Triangle(new double[] {0, l, 0, -d, w, g, -g, w, d}, c3));
   // tris.add(new Triangle(new double[] {0, -l, 0, -d, -w, g, -g, -w, d}, c4));
    tris.add(new Triangle(new double[] {l, 0, 0, w, -d, g, w, -g, d}, c5));
    tris.add(new Triangle(new double[] {-l, 0, 0, -w, -d, g, -w, -g, d}, c6));
  }
}
