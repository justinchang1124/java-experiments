import java.awt.Color;
import java.awt.Graphics;

public class MenuStat extends Screen
{

  public void tick()
  {
    // TODO Auto-generated method stub
    
  }

  public void render(Graphics g)
  {
    Draw.showText(120, 50, 0, Color.blue, "Statistics", g);
    for (int i = 0; i < 14; i++)
    {
      Draw.showText(180 + 30*i, 20, 0, Color.blue, "Statistic " + (i+1) + ": " + Central.stats[i], g);
    }
    Draw.button(850, g);
    Draw.showText(890, 30, 0, Color.blue, "Menu", g);
  }

}
