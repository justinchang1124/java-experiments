import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

public class Central extends ProcessController
{
  public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 800;
  public static int[][] seconds, minutes, hours, days, time;

  @Override
  public void initialize()
  {
    MakeText.textColor = Color.white;

    time = new int[4][1];

    seconds = new int[][] {
        { 1, 0, 0, 0 },
        { 0, 1, 0, 0 },
        { 0, 0, 1, 1 },
        { 0, 0, 0, 1 }
    };
    minutes = new int[][] {
        { 1, 0, 0, 0 },
        { 0, 1, 0, 1 },
        { 0, 0, 1, -60 },
        { 0, 0, 0, 1 }
    };
    hours = new int[][] {
        { 1, 0, 0, 1 },
        { 0, 1, 0, -60 },
        { 0, 0, 1, 0 },
        { 0, 0, 0, 1 }
    };
    days = new int[][] {
        { 1, 0, 0, -24 },
        { 0, 1, 0, 0 },
        { 0, 0, 1, 0 },
        { 0, 0, 0, 1 }
    };
  }

  public void getTime()
  {
    Calendar cal = Calendar.getInstance();

    time[0][0] = cal.get(Calendar.HOUR);

    if (cal.get(Calendar.AM_PM) == 1)
      time[0][0] += 12;

    time[1][0] = cal.get(Calendar.MINUTE);
    time[2][0] = cal.get(Calendar.SECOND);
    time[3][0] = 1;
  }

  @Override
  public void tick(long frameCount)
  {
    getTime();
  }

  @Override
  public void render(Graphics g, long frameCount)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    MakeText.setMainFont("times new roman", 0, 12);
    MakeText.showText(g2, "FPS: " + frameCount, 10, 20);
    draw4x4Array(g2, seconds, 40, 80);
    drawTime(g2, 80, 200);
  }

  public void draw4x4Array(Graphics2D g2, int[][] a, int j, int k)
  {
    MakeText.setMainFont("times new roman", 0, 24);
    MakeText.showText(g2, "[" + a[0][0] + " " + a[0][1] + " " + a[0][2] + " " + a[0][3] + "]", j,
        k);
    MakeText.showText(g2, "[" + a[1][0] + " " + a[1][1] + " " + a[1][2] + " " + a[1][3] + "]", j, k
        + 30);
    MakeText.showText(g2, "[" + a[2][0] + " " + a[2][1] + " " + a[2][2] + " " + a[2][3] + "]", j, k
        + 60);
    MakeText.showText(g2, "[" + a[3][0] + " " + a[3][1] + " " + a[3][2] + " " + a[3][3] + "]", j, k
        + 90);
  }

  public void drawTime(Graphics2D g2, int j, int k)
  {
    MakeText.setMainFont("times new roman", 0, 24);
    String[] s = new String[4];
    
    for (int i = 0; i < s.length; i++)
    {
      s[i] = "" + time[i][0];
      if (time[i][0] < 10)
        s[i] = "0" + s[i];
      
      MakeText.showText(g2, "[" + s[i] + "]", j, k + 30*i);
    }
  }

  @Override
  public void keyPressed()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyTyped()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void wheelInput()
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseInput(int eventDescription)
  {
    // TODO Auto-generated method stub

  }

  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "Clock", 60);
  }
}
