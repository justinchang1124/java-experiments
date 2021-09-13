import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TargetBoard extends JPanel
{
  
  public void paintComponent(Graphics g)
  {
    
    int k = 30;
    super.paintComponent(g); // Call JPanel's paintComponent method
                            // to paint the background
    int xCenter = getWidth() / 4;
    int yCenter = getHeight() / 4;
    int x;

    for (x = 0; x < k; x += 1)
    {

      g.setColor(Color.RED);
      g.fillOval(xCenter + 20 * x, yCenter + 20 * x, 400 - 40 * x, 400 - 40 * x);
      g.setColor(Color.WHITE);
      g.fillOval(xCenter + 20 * x + 10, yCenter + 20 * x + 10, 380 - 40 * x, 380 - 40 * x);

    }

  }

  public static void main(String[] args)
  {
    JFrame window = new JFrame("TargetBoard");
    window.setBounds(200, 200, 800, 800);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    TargetBoard panel = new TargetBoard();
    panel.setBackground(Color.WHITE);
    Container c = window.getContentPane();
    c.add(panel);
    window.setVisible(true);

  }

}
