/*
 * Attribution:
 * All of this class is attributed to RealTutsGML.
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Image is used by IMG to retrieve a BufferedImage from a file.
 * 
 * @author Justin C
 */
public class Image
{
  private BufferedImage image;

  /**
   * Retrieves an image by opening the path specified and 
   * assigning the data file to "image" before returning it.
   * 
   * @param path
   * @return
   */
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
