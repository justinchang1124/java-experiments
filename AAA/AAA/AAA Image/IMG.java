
/*
 * Attribution:
 * Thanks to RealTutsGML for setTransparency(Graphics, double).
 */

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * IMG loads backgrounds and sprite sheets automatically.
 * Any sprite from a sprite sheet can also be retrieved.
 * 
 * @author Justin C
 */
public class IMG
{
  private BufferedImage[] background;

  public static BufferedImage[] sprites;

  /**
   * Constructs an IMG.
   * 
   * @param width
   * @param height
   * @param b_g_files
   * Addresses of the background files in the form "/address.extension".
   * @param sprite
   * Addresses of the sprite files in the form "/address.extension".
   */
  public IMG(int width, int height, String[] b_g_files, String[] sprite)
  {
    sprites = new BufferedImage[sprite.length];
    background = new BufferedImage[b_g_files.length];

    Image image = new Image();

    // Loads all background files.
    for (int i = 0; i < b_g_files.length; i++)
    {
      background[i] = image.load(b_g_files[i]);
    }

    // Loads all sprites.
    for (int i = 0; i < sprite.length; i++)
    {
      sprites[i] = image.load(sprite[i]);
    }
  }

  /**
   * Draws a background onto g given an entry.
   * 
   * @param g
   * @param entry
   */
  public void render(Graphics g, int entry)
  {
    g.drawImage(background[entry], 0, 0, null);
  }

  /**
   * Gets a smaller subsection of a sprite sheet.
   * 
   * @param sprite
   * @param x
   * @param y
   * @param width
   * @param height
   * @param col
   * @param wid
   * @return
   */
  public static BufferedImage getSprite(int sprite, int x, int y, int width, int height, int col, int wid)
  {
    return sprites[sprite].getSubimage(( x - 1 ) * wid, ( y - 1 ) * col, width, height);
  }

  /**
   * Sets the transparency of g to alpha.
   * 
   * @param g
   * @param alpha
   */
  public static void setTransparency(Graphics g, double alpha)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
  }
}
