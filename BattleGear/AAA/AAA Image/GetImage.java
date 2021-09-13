import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GetImage
{
  private BufferedImage[] background;

  public static BufferedImage[] sprites;

  public GetImage(int width, int height, String[] b_g_files, String[] sprite)
  {
    sprites = new BufferedImage[sprite.length];
    background = new BufferedImage[b_g_files.length];
    
    SetImage setImage = new SetImage();
    Sprite ss;

    for (int i = 0; i < b_g_files.length; i++)
    {
      ss = new Sprite(setImage.load(b_g_files[i]));
      background[i] = ss.grabImage(1, 1, width, height, width, height);
    }

    for (int i = 0; i < sprites.length; i++)
    {
      sprites[i] = setImage.load(sprite[i]);
    }
  }

  public void render(Graphics g, int entry)
  {
    g.drawImage(background[entry], 0, 0, null);
  }
}
