import java.awt.Color;
import java.awt.Graphics;

public class Campaign extends Screen
{
  public static int highlight1, highlight2, highlight3;

  public void tick()
  {
    // TODO Auto-generated method stub

  }

  public void render(Graphics g)
  {
    Draw.showText(120, 50, 1, Color.blue, "Campaign", g);
    if (Central.level > 1)
    {
      Draw.showText(180, 30, 0, Color.blue, "Load Current Save", g, -300);
      Draw.showText(280, 30, 0, Color.blue, "Current Level: " + Central.level, g, -300);
      Draw.showText(330, 30, 0, Color.blue, "Current Money: " + Central.money, g, -300);
      Draw.showText(380, 30, 0, Color.blue, "Current XP: " + Central.xp, g, -300);
      if (Central.country == 0)
        Draw.showText(430, 30, 0, Color.blue, "Campaign: USA", g, -300);
      if (Central.country == 1)
        Draw.showText(430, 30, 0, Color.blue, "Campaign: Russia", g, -300);
      if (Central.country == 2)
        Draw.showText(430, 30, 0, Color.blue, "Campaign: China", g, -300);
      if (Central.difficulty == 0)
        Draw.showText(480, 30, 0, Color.blue, "Easy Mode", g, -300);
      if (Central.difficulty == 1)
        Draw.showText(480, 30, 0, Color.blue, "Normal Mode", g, -300);
      if (Central.difficulty == 2)
        Draw.showText(480, 30, 0, Color.blue, "Hard Mode", g, -300);
      if (Central.difficulty == 3)
        Draw.showText(480, 30, 0, Color.blue, "Impossible Mode", g, -300);
      if (Central.gameMode == 0)
        Draw.showText(530, 30, 0, Color.blue, "Game Life: Unlimited", g, -300);
      if (Central.gameMode == 1)
        Draw.showText(530, 30, 0, Color.blue, "Game Life: " + Central.gameLife, g, -300);

      Draw.showText(180, 30, 0, Color.blue, "Create New Save", g, 300);
    }
    else
    {
      Draw.button(230, 500, 64, g);
      Draw.showText(270, 30, 0, Color.green, "Create New Save", g);
      Draw.button(200, 330, g);
      Draw.button(400, 330, g);
      Draw.button(600, 330, g);
      Draw.button(100, 430, g);
      Draw.button(300, 430, g);
      Draw.button(500, 430, g);
      Draw.button(700, 430, g);
      Draw.button(250, 530, g);
      Draw.button(550, 530, g);

      Draw.fRect(200 + 200 * highlight1, 330, 200, 64, Color.yellow, g);
      Draw.fRect(100 + 200 * highlight2, 430, 200, 64, Color.yellow, g);
      Draw.fRect(250 + 300 * highlight3, 530, 200, 64, Color.yellow, g);

      Draw.showText(370, 30, 0, Color.blue, "USA", g, -200);
      Draw.showText(370, 30, 0, Color.blue, "Russia", g, 0);
      Draw.showText(370, 30, 0, Color.blue, "China", g, 200);
      Draw.showText(470, 30, 0, Color.blue, "Easy", g, -300);
      Draw.showText(470, 30, 0, Color.blue, "Medium", g, -100);
      Draw.showText(470, 30, 0, Color.blue, "Hard", g, 100);
      Draw.showText(470, 30, 0, Color.blue, "Impossible", g, 300);
      Draw.showText(570, 30, 0, Color.blue, "Unlimited", g, -150);
      Draw.showText(570, 30, 0, Color.blue, "Limited", g, 150);
    }

    Draw.button(850, g);
    Draw.showText(890, 30, 0, Color.blue, "Menu", g);
  }

}
