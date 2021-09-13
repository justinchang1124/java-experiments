import java.awt.Graphics;

public class Block
{
  private int repNum;

  private int xCoord;

  private int yCoord;

  private int height;

  private int widthLarge;

  private int widthSmall;

  private int padding;

  public Block(int repNum, int xCoord, int yCoord, int height, int widthLarge, int widthSmall, int padding)
  {
    this.repNum = repNum;
    this.xCoord = xCoord;
    this.yCoord = yCoord;
    this.height = height;
    this.widthLarge = widthLarge;
    this.widthSmall = widthSmall;
    this.padding = padding;
  }

  public void draw(Graphics g)
  {
    g.fillRect(xCoord, yCoord, widthLarge, height);
    xCoord += ( widthLarge + padding );

    // Fills the small rectangles
    for (int j = 0; j < 2 * ( repNum + 1 ); j++)
    {
      g.fillRect(xCoord, yCoord, widthSmall, height);
      xCoord += ( widthSmall + padding );
    }
  }

  public int getXCoord()
  {
    return xCoord;
  }

  public int getYCoord()
  {
    return yCoord;
  }
}
