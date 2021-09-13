import java.awt.Color;
import java.awt.Graphics;

public class OpenMain extends Screen
{

  public void tick()
  {
    // TODO Auto-generated method stub
    
  }

  public void render(Graphics g)
  {
    Draw.button(10, 150, g);
    Draw.button(10, 250, g);
    Draw.button(10, 350, g);
    Draw.button(10, 450, g);
    Draw.button(10, 550, g);
    
    Draw.button(780, 350, g);
    Draw.button(780, 450, g);
    Draw.button(780, 550, g);
    Draw.button(750, g);
    
    Draw.showText(120, 50, 1, Color.blue, Loader.PROGRAM_TITLE, g);
    Draw.showText(190, 30, 0, Color.blue, "Campaign", g, -390);
    Draw.showText(290, 30, 0, Color.blue, "Single Mode", g, -390);
    Draw.showText(390, 30, 0, Color.blue, "How to Play", g, -390);
    Draw.showText(490, 30, 0, Color.blue, "Options", g, -390);
    Draw.showText(590, 30, 0, Color.blue, "Credits", g, -390);
    
    Draw.showText(390, 30, 0, Color.blue, "Medals", g, 390);
    Draw.showText(490, 30, 0, Color.blue, "Statistics", g, 390);
    Draw.showText(590, 30, 0, Color.blue, "Trophies", g, 390);
  }

}
