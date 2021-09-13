import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Scanner;

public class Central extends Controller
{
  public static Scanner s = new Scanner(System.in);

  public static final int SCREEN_WIDTH = 1440, SCREEN_HEIGHT = 1080, minHeight = 200;

  public static final int[] disp = { -SCREEN_WIDTH / 3, 0, SCREEN_WIDTH / 3 };

  public static int minutesLeft = Integer.parseInt(s.nextLine());

  @Override
  public void initialize()
  {
    Team.setTeams("Teams.txt", 23);
  }

  @Override
  public void tick(long frameCount)
  {

  }

  @Override
  public void render(Graphics g, long frameCount)
  {
    Graphics2D g2 = (Graphics2D) g;

    // Draws the background.
    g2.setColor(new Color(200, 220, 255));
    g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    Team.sort();

    MakeText.color = Color.blue;
    MakeText.setMainFont("times new roman", 0, 100);
    MakeText.showText(g2, "Gunga Bowl: " + minutesLeft + " Min Left", 110);

    for (int i = 0; i < Team.teams.size(); i++)
    {
      MakeText.setMainFont("times new roman", 0, 28);

      int width = i / 9;
      int height = (i % 9) * 100;

      Team t = Team.teams.get(i);

      g2.setColor(Color.black);
      g2.fillRect(disp[width] + SCREEN_WIDTH / 24 + SCREEN_WIDTH / 3 - 10 - 2, height - 32
          + minHeight - 10 - 2, SCREEN_WIDTH / 4 + 20 + 4, 64 + 20 + 4);
      g2.setColor(Color.white);
      g2.fillRect(disp[width] + SCREEN_WIDTH / 24 + SCREEN_WIDTH / 3 - 10, height - 32 + minHeight
          - 10, SCREEN_WIDTH / 4 + 20, 64 + 20);
      g2.setColor(Color.black);
      g2.fillRect(disp[width] + SCREEN_WIDTH / 24 + SCREEN_WIDTH / 3 - 2, height - 32 + minHeight
          - 2, SCREEN_WIDTH / 4 + 4, 64 + 4);
      g2.setColor(Color.blue);
      g2.fillRect(disp[width] + SCREEN_WIDTH / 24 + SCREEN_WIDTH / 3, height - 32 + minHeight,
          SCREEN_WIDTH / 4, 64);

      MakeText.color = Color.white;
      MakeText.showText(g2, (i + 1) + ":", disp[width] + SCREEN_WIDTH / 24 + SCREEN_WIDTH / 3 + 8,
          height + minHeight + 8);
      MakeText.showText(disp[width], g2, t.getName() + ": " + t.getScore(),
          height + minHeight + 8);
    }
  }

  @Override
  public void keyPressed()
  {

  }

  @Override
  public void keyTyped()
  {

  }

  @Override
  public void keyReleased()
  {

  }

  @Override
  public void wheelInput()
  {

  }

  @Override
  public void mouseInput(int eventDescription)
  {
    // if (clicks > 1 && eventDescription == 1)
    // {
    // System.out.println("Enter team name!");
    // String nextName = s.nextLine();
    // System.out.println("Enter \"current_score\" or enter \"+score_diff\"");
    // String score = s.nextLine();
    // String scoreProcessed = "";
    // for (int i = 0; i <score.length(); i++)
    // {
    // if (Character.isDigit(score.charAt(i)) || ((score.charAt(i) == '+')))
    // {
    // scoreProcessed += score.charAt(i);
    // }
    // }
    // System.out.println("Time remaining? (in minutes)");
    // String time = s.nextLine();
    // String timeProcessed = "";
    // for (int i = 0; i <time.length(); i++)
    // {
    // if (Character.isDigit(time.charAt(i)))
    // {
    // timeProcessed += time.charAt(i);
    // }
    // }
    //
    // minutesLeft = Integer.parseInt("0" + timeProcessed);
    //
    // boolean updated = false;
    //
    // for (int i = 0; i < Team.teams.size(); i++)
    // {
    // Team t = Team.teams.get(i);
    // if (t.getName().equals(nextName))
    // {
    // updated = true;
    //
    // if (score.charAt(0) == '+')
    // {
    // Team.addScore(t.getName(),
    // Integer.parseInt(scoreProcessed.substring(1)));
    // }
    // else
    // {
    // Team.setScore(t.getName(), Integer.parseInt(scoreProcessed));
    // }
    // }
    // }
    //
    // if (!updated)
    // {
    // System.out.println("Error! Team not found!");
    // }
    //
    // clicks = 0;
    // }
  }

  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "MOAA Projector", 60, new Central());
  }
}
