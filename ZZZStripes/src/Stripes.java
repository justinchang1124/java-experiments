import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Stripes extends JFrame
{
  private int height = 300; // height of all rectangles
  private int widthLarge = 35; // width of thick rectangles
  private int widthSmall = 20; // width of thin rectangles
  private int padding = 30; // spacing near edges and between rectangles
  private int repetitions = 3; // how many colored "blocks" of rectangles

  public Stripes()
  {
    int smallBarWidths = ( widthSmall + padding ) * repetitions * ( repetitions + 1 );
    int largeBarWidths = repetitions * ( widthLarge + padding );
    int width = smallBarWidths + largeBarWidths + padding;

    setSize(width, height + padding * 2);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void paint(Graphics g)
  {
    // paints the white background
    g.setColor(Color.white);
    g.fillRect(0, 0, getWidth(), getHeight());

    // Coordinates of next rectangle to be drawn
    int xCoord = padding;
    int yCoord = getHeight() - height - padding;

    // Fills all blocks
    for (int i = 0; i < repetitions; i++)
    {
      // Determines the color
      g.setColor(Color.red);
      if (i % 2 == 0)
        g.setColor(Color.black);
      
      Block block = new Block(i, xCoord, yCoord, height, widthLarge, widthSmall, padding);
      block.draw(g);
      xCoord = block.getXCoord();
    }
  }

  public static void main(String[] args)
  {
    Stripes rs = new Stripes();
    rs.repaint();
  }
}