import java.awt.Color;
import java.awt.Graphics;

public class MenuCredit extends Screen
{

  public void tick()
  {
    // TODO Auto-generated method stub
    
  }

  public void render(Graphics g)
  {
    Draw.button(850, g);
    Draw.showText(890, 30, 0, Color.blue, "Menu", g);
  }

}
