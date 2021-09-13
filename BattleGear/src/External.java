import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;

public class External
{
  /**
   * Attribution: 
   * 
   * "RealTutsGML"
   */
  public static void setTransparency(Graphics g, double alpha)
  {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha));
  }

  /**
   * Attribution: 
   * 
   * MadProgrammer on Stack Exchange for text centering
   */
  public static int centerTextX(Font font, String text, Graphics g)
  {
    TextLayout txt = new TextLayout(text, font, ((Graphics2D) g).getFontRenderContext());
    return (int) txt.getBounds().getWidth();
  } 
}
