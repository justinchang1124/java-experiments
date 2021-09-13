import java.awt.Graphics;

public abstract class TempCentral
{
  public abstract void initialize();

  public abstract void eventKeyboardOutput(int eventDescription, int keyboardCode);

  public abstract void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount);

  public abstract void eventWheelOutput(int wRotation);

  public abstract void tick(long frameCount);

  public abstract void render(Graphics g, long frameCount);
}
