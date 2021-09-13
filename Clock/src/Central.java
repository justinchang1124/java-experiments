import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

public class Central extends ProcessController
{
  public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 800, BORDER_SLOW = 150, BORDER_FAST =
      100;
  public static final double V_CAM_SLOW = 0.02, V_CAM_FAST = 0.03;
  public static int hours, minutes, seconds, milli;
  private double heading, pitch;

  public static double timeDilation = 1;

  private long lastTime = System.currentTimeMillis();

  private AxialStar milliA = new AxialStar(new Vertex(0, 0, 0), 20, Color.white, Color.gray);
  private AxialStar secondsA = new AxialStar(new Vertex(0, 0, 0), 20 * 4, Color.magenta, Color.red);
  private AxialStar minutesA = new AxialStar(new Vertex(0, 0, 0), 20 * 4 * 4, Color.lightGray, Color.darkGray);
  private AxialStar hoursA = new AxialStar(new Vertex(0, 0, 0), 20 * 4 * 4 * 4, Color.cyan, Color.blue);
  
  public void getTime()
  {
    Calendar cal = Calendar.getInstance();

    hours = cal.get(Calendar.HOUR);

    if (cal.get(Calendar.AM_PM) == 1)
      hours += 12;

    minutes = cal.get(Calendar.MINUTE);
    seconds = cal.get(Calendar.SECOND);
  }

  public void initialize()
  {
    getTime();

    MakeText.setMainFont("times new roman", 0, 12);
    MakeText.textColor = Color.white;
    Movement.pY = -4000;
    heading = -Math.PI / 2;

    Model.pipeline.add(milliA);
    Model.pipeline.add(secondsA);
    Model.pipeline.add(minutesA);
    Model.pipeline.add(hoursA);
  }

  public void tick(long frameCount)
  {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastTime >= 1000)
    {
      lastTime += 1000;
      getTime();
    }

    milliA.r_ZX = milli * 2 * Math.PI / 1000;
    secondsA.r_ZX = seconds * 2 * Math.PI / 60;
    minutesA.r_ZX = minutes * 2 * Math.PI / 60;
    hoursA.r_ZX = hours * 2 * Math.PI / 24;

    if (mouseOver(0, 0, BORDER_SLOW, SCREEN_HEIGHT))
      heading += V_CAM_SLOW;
    if (mouseOver(SCREEN_WIDTH - BORDER_SLOW, 0, BORDER_SLOW, SCREEN_HEIGHT))
      heading -= V_CAM_SLOW;

    if (mouseOver(0, 0, SCREEN_WIDTH, BORDER_SLOW) && pitch < Math.PI / 2)
      pitch += V_CAM_SLOW;
    if (mouseOver(0, SCREEN_HEIGHT - BORDER_SLOW, SCREEN_WIDTH, BORDER_SLOW) && pitch > -Math.PI
        / 2)
      pitch -= V_CAM_SLOW;

    if (mouseOver(0, 0, BORDER_FAST, SCREEN_HEIGHT))
      heading += V_CAM_FAST;
    if (mouseOver(SCREEN_WIDTH - BORDER_FAST, 0, BORDER_FAST, SCREEN_HEIGHT))
      heading -= V_CAM_FAST;

    if (mouseOver(0, 0, SCREEN_WIDTH, BORDER_FAST) && pitch < Math.PI / 2)
      pitch += V_CAM_FAST;
    if (mouseOver(0, SCREEN_HEIGHT - BORDER_FAST, SCREEN_WIDTH, BORDER_FAST) && pitch > -Math.PI
        / 2)
      pitch -= V_CAM_FAST;

    Movement.tick(heading);
    Draw3D.tick();
  }

  public void render(Graphics g, long frameCount)
  {
    Calendar cal = Calendar.getInstance();
    milli = cal.get(Calendar.MILLISECOND);
    
    Graphics2D g2 = (Graphics2D) g;
    Vertex pos = new Vertex(Movement.pX, Movement.pY, Movement.pZ);

    // Draw the background.
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    Basis b = new Basis(pitch, heading, pos);
    g2.drawImage(Draw3D.getImage(SCREEN_WIDTH, SCREEN_HEIGHT, b, 400, Double.POSITIVE_INFINITY, 1),
        0, 0, null);

    MakeText.showText(g2, "FPS: " + frameCount, 10, 20);
    MakeText.showText(g2, "Position: " + pos, 10, 40);
    double h = Math.round(1000 * heading) / 1000.0;
    double p = Math.round(1000 * pitch) / 1000.0;
    MakeText.showText(g2, "Heading, Pitch: " + h + ", " + p, 10, 60);
    String s = "Current Time: ";
    if (hours < 10)
      s += "0";
    s += hours + ":";
    if (minutes < 10)
      s += "0";
    s += minutes + ":";
    if (seconds < 10)
      s += "0";
    s += seconds + ":" + milli;
    MakeText.showText(g2, s, 10, 80);
  }

  public void keyPressed()
  {
    Movement.pressKeys(keyCode);
  }

  public void keyTyped()
  {

  }

  public void keyReleased()
  {
    Movement.releaseKeys(keyCode);
    Draw3D.releaseKeys(keyCode);
  }

  public void wheelInput()
  {

  }

  public void mouseInput(int eventMouse)
  {
    if (clicks > 0 && eventMouse == 1 && button == 3)
    {
      heading = 0;
      pitch = 0;
      Movement.reset();
      Movement.pY = -4000;
      heading = -Math.PI / 2;
      Draw3D.reset();
    }

    if (clicks > 0 && eventMouse == 1 && button == 1)
      Draw3D.shadows = !Draw3D.shadows;
  }

  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "Clock", 60);
  }
}
