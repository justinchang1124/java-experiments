import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Draw
{
  public static final Color border_color = Color.black, button_color = Color.white;
  public static final String fontChoice = "arial";

  // Creates a sized font where 0, 1, 2, 3 gives normal, bold, italics, bold + italics.
  public static Font setFontType(int size, int mod)
  {
    Font font = null;

    if (mod == 0)
    {
      font = new Font(fontChoice, Font.PLAIN, size);
    }
    if (mod == 1)
    {
      font = new Font(fontChoice, Font.BOLD, size);
    }
    if (mod == 2)
    {
      font = new Font(fontChoice, Font.ITALIC, size);
    }
    if (mod == 3)
    {
      font = new Font(fontChoice, Font.BOLD + Font.ITALIC, size);
    }
    return font;
  }

  // Draws a String centered horizontally by default.
  public static void showText(int y, int size, int mod, Color color, String text, Graphics g)
  {
    Font font = setFontType(size, mod);
    int xDist = External.centerTextX(font, text, g);
    g.setFont(font);
    g.setColor(color);
    g.drawString(text, (Loader.WIDTH - xDist) / 2, y);
  }
  
  public static void showText(int y, int size, int mod, Color color, String text, Graphics g, int xDisplace)
  {
    Font font = setFontType(size, mod);
    int xDist = External.centerTextX(font, text, g);
    g.setFont(font);
    g.setColor(color);
    g.drawString(text, (Loader.WIDTH - xDist) / 2 + xDisplace, y);
  }

  public static void showText(int x, int y, int size, int mod, Color color, String text, Graphics g)
  {
    Font font = setFontType(size, mod);
    g.setFont(font);
    g.setColor(color);
    g.drawString(text, x, y);
  }

  // Draws or fills a rectangle, default centered.
  public static void dRect(int y, int width, int height, Color color, Graphics g)
  {
    g.setColor(color);
    g.drawRect((Loader.WIDTH - width) / 2, y, width, height);
  }

  public static void fRect(int y, int width, int height, Color color, Graphics g)
  {
    g.setColor(color);
    g.fillRect((Loader.WIDTH - width) / 2, y, width, height);
  }

  public static void dRect(int x, int y, int width, int height, Color color, Graphics g)
  {
    g.setColor(color);
    g.drawRect(x, y, width, height);
  }

  public static void fRect(int x, int y, int width, int height, Color color, Graphics g)
  {
    g.setColor(color);
    g.fillRect(x, y, width, height);
  }

  // Draws a button, default centered and default with dimensions 200, 64.
  public static void button(int y, Graphics g)
  {
    dRect(y, 200, 64, border_color, g);
    fRect(y + 1, 200 - 2, 64 - 2, button_color, g);
  }

  public static void button(int x, int y, Graphics g)
  {
    dRect(x, y, 200, 64, border_color, g);
    fRect(x + 1, y + 1, 200 - 2, 64 - 2, button_color, g);
  }
  
  public static void button(int y, int width, int height, Graphics g)
  {
    dRect(y, width, height, border_color, g);
    fRect(y + 1, width - 2, height - 2, button_color, g);
  }

  public static void button(int x, int y, int width, int height, Graphics g)
  {
    dRect(x, y, width, height, border_color, g);
    fRect(x + 1, y + 1, width - 2, height - 2, button_color, g);
  }
  
  public static void soundOff(Graphics g)
  {
    Draw.button(700, 50, 80, 30, g);
    Draw.showText(72, 16, 0, Color.black, "Sound Off", g, 340);
  }
  
  public static void fps(int frameCount, Graphics g)
  {
    Draw.button(900, 10, 80, 30, g);
    Draw.showText(32, 16, 0, Color.black, "FPS: " + frameCount, g, 440);
  }

  // Bounding operation.
  public static double bounder(double var, double min, double max)
  {
    if (var >= max)
    {
      return var = max;
    }
    if (var <= min)
    {
      return var = min;
    }
    else
    {
      return var;
    }
  }
}
