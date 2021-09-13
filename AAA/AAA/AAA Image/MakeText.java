
/*
 * Attribution:
 * MadProgrammer on Stack Exchange for centerTextX()
 * 
 * Attribution:
 * Alvin Alexander for listFonts()
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.font.TextLayout;

/**
 * @author Justin C
 */
public class MakeText
{
  private static Font mainFont;

  private static Color textColor;

  /**
   * Prints out all fonts available to Eclipse.
   */
  public static void listFonts()
  {
    String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    for (int i = 0; i < fonts.length; i++)
    {
      System.out.println(fonts[i]);
    }
  }

  /**
   * Centered by default.
   * 
   * @param g
   * @param text
   * @param y
   */
  public static void showText(Graphics g, String text)
  {
    g.setFont(mainFont);
    g.setColor(textColor);
    g.drawString(text, centerTextX(g, text), Central.SCREEN_HEIGHT / 2);
  }

  /**
   * Centered by default, but y changes.
   * 
   * @param g
   * @param text
   * @param y
   */
  public static void showText(Graphics g, String text, int y)
  {
    g.setFont(mainFont);
    g.setColor(textColor);
    g.drawString(text, centerTextX(g, text), y);
  }

  /**
   * Text at given x, y.
   * 
   * @param x
   * @param y
   * @param text
   * @param g
   */
  public static void showText(Graphics g, String text, int x, int y)
  {
    g.setFont(mainFont);
    g.setColor(textColor);
    g.drawString(text, x, y);
  }

  /**
   * @param g
   * @param text
   * @param x
   * @param y
   * @param xDisplace
   */
  public static void showText(Graphics g, String text, int x, int y, int xDisplace)
  {
    g.setFont(mainFont);
    g.setColor(textColor);
    g.drawString(text, centerTextX(g, text) + xDisplace, y);
  }

  /**
   * @param g
   * @param text
   * @return
   */
  public static int centerTextX(Graphics g, String text)
  {
    Graphics2D g2d = (Graphics2D) g;
    TextLayout txt = new TextLayout(text, mainFont, g2d.getFontRenderContext());
    double xDist = txt.getBounds().getWidth();
    return (int) (( Central.SCREEN_WIDTH - xDist ) / 2);
  }

  public static void setMainFont(String fontChoice, int mod, int size)
  {
    mainFont = new Font(fontChoice, mod, size);
  }

  public static void setTextColor(Color color)
  {
    textColor = color;
  }

  public static Font getMainFont()
  {
    return mainFont;
  }

  public static Color getTextColor()
  {
    return textColor;
  }
}
