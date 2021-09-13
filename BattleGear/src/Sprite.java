
/**
 * Attribution:
 * All of this class is attributed to RealTutsGML.
 */

import java.awt.image.BufferedImage;

public class Sprite
{
  private BufferedImage sprite;

  public Sprite(BufferedImage ss)
  {
    this.sprite = ss;
  }

  public BufferedImage grabImage(int x, int y, int width, int height, int col, int wid)
  {
    BufferedImage img = sprite.getSubimage(x * col - col, y * col - col, width, height);
    return img;
  }
}
