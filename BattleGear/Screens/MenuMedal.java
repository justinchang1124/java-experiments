import java.awt.Color;
import java.awt.Graphics;

public class MenuMedal extends Screen
{

  public void tick()
  {
    // TODO Auto-generated method stub
    
  }

  public void render(Graphics g)
  {
    Draw.showText(120, 50, 0, Color.blue, "Medals", g);
    for (int i = 0; i < Loader.MEDAL_NUMBER; i++)
    {
      Draw.showText(180 + 30*i, 20, 0, Color.blue, "Medal " + (i+1) + ": " + Loader.medalUnlock[i], g);
    }
    Draw.button(850, g);
    Draw.showText(890, 30, 0, Color.blue, "Menu", g);
  }

}
