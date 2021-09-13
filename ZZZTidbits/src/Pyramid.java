import java.awt.*;
import javax.swing.*;

public class Pyramid extends JFrame {

  public Pyramid() {
    setSize(640,640);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void paint( Graphics g ) {
    
    g.setColor( Color.BLUE );
    g.fillRect( 80, 80, 200, 200 ); 
    g.setColor( Color.RED );
    g.fillRect( 80, 360, 200, 200 ); 
    g.setColor( Color.YELLOW );
    g.fillRect( 360, 360, 200, 200 ); 
    g.setColor( Color.GREEN );
    g.fillRect( 360, 80, 200, 200 ); 
  }

  public static void main(String args []) {
    Pyramid rs = new Pyramid();
    rs.repaint();
  }
}