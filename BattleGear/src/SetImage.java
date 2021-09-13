/**
 * Attribution:
 * All of this class is attributed to RealTutsGML.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SetImage
{
  private BufferedImage image;
  
  public BufferedImage load(String path)
  {
    try
    {
      image = ImageIO.read(getClass().getResource(path));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return image;
  }
}
