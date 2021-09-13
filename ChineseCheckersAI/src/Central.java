import java.awt.Graphics;

/**
 * The goal of this project is to explore 3D rendering in Java.
 * 
 * @author Justin C
 */
public class Central extends Controller
{
  public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 800;

  public void initialize()
  {
    Board b = new Board();
    System.out.println(b);
    System.out.println(b.getScore());
  }

  public void tick(long frameCount)
  {
    
  }

  public void render(Graphics g, long frameCount)
  {
    
  }

  public void keyPressed()
  {
    
  }

  public void keyTyped()
  {

  }

  public void keyReleased()
  {
    
  }

  public void wheelInput()
  {
    
  }

  public void mouseInput(int eventMouse)
  {
    
  }

  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "3D Rendering in Java", 60, new Central());
  }
}