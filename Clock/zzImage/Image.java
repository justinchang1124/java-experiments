import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image is a series of static methods that, once initialized, automatically
 * load backgrounds and allow any sprite from a sprite sheet to be retrieved.
 * 
 * @author Justin C
 */
public class Image
{
  /**
   * An array of all backgrounds as BufferedImages.
   */
  private static BufferedImage[] background;

  /**
   * An array of all sprite sheets as BufferedImages.
   */
  private static BufferedImage[] sprites;

  /**
   * The entry of the current background to be rendered.
   */
  public static int entry;

  /**
   * Sets the BufferedImages to be used.
   * 
   * @param b_g_files
   * Addresses of the background files in the form "/address.extension".
   * @param sprite
   * Addresses of the sprite files in the form "/address.extension".
   */
  public Image(String[] b_g_files, String[] sprite)
  {
    background = new BufferedImage[b_g_files.length];
    for (int i = 0; i < b_g_files.length; i++)
      background[i] = load(b_g_files[i]);

    sprites = new BufferedImage[sprite.length];
    for (int i = 0; i < sprite.length; i++)
      sprites[i] = load(sprite[i]);
  }

  /**
   * Draws background[entry] onto g.
   */
  public static void render(Graphics g)
  {
    g.drawImage(background[entry], 0, 0, null);
  }

  /**
   * Gets a smaller subsection of a sprite sheet.
   * 
   * @param x
   * The row number on the sprite sheet.
   * @param y
   * The col number on the sprite sheet.
   * @param wid
   * The width of a row on the sprite sheet.
   * @param col
   * The height of a col on the sprite sheet.
   */
  public static BufferedImage getSprite(int sprite, int width, int height, int x, int y, int wid,
      int col)
  {
    return getSprite(sprite, (x - 1) * wid, (y - 1) * col, width, height);
  }

  /**
   * Gets a smaller subsection of a sprite sheet.
   * 
   * @param sprite
   * The sprite sheet's number.
   * @param x
   * The x-coordinate of the top-left
   * @param w
   * The width of the needed subimage.
   * @param h
   * The height of the needed subimage.
   */
  public static BufferedImage getSprite(int sprite, int x, int y, int w, int h)
  {
    return sprites[sprite].getSubimage(x, y, w, h);
  }

  /**
   * Sets the transparency of g to alpha.
   * 
   * Attribution: RealTutsGML
   */
  public static void setTransparency(Graphics g, double alpha)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
  }

  /**
   * Retrieves an image from the specified path.
   * 
   * Attribution: RealTutsGML
   */
  public BufferedImage load(String path)
  {
    try
    {
      return ImageIO.read(getClass().getResource(path));
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
