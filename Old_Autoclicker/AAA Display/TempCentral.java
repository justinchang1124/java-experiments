import java.awt.Graphics;

/**
 * TempCentral ensures that any class that extends it possesses the appropriate 
 * methods to function with the AAA grouping.
 * 
 * @author Justin C
 */
public abstract class TempCentral
{
  public abstract void initialize();

  public abstract void eventKeyboardOutput(int eventDescription, int keyboardCode);

  public abstract void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount);

  public abstract void eventWheelOutput(int wRotation);

  public abstract void tick(int frameCount);

  public abstract void render(Graphics g, int frameCount);
}
