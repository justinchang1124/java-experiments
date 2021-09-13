import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

/**
 * TakeInput implements the necessary mouse listeners. It provides methods by which
 * the outputs of the aforementioned device can be passed to Central in a sanitized way.
 * 
 * @author Justin C
 */
public class TakeInput implements MouseMotionListener, MouseListener
{
  private Central central;

  public TakeInput(Central central)
  {
    this.central = central;
  }

  public void mouseEntered(MouseEvent e)
  {
    central.eventMouseOutput(-3, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mouseExited(MouseEvent e)
  {
    central.eventMouseOutput(3, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mouseMoved(MouseEvent e)
  {
    central.eventMouseOutput(-2, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mouseDragged(MouseEvent e)
  {
    central.eventMouseOutput(2, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mousePressed(MouseEvent e)
  {
    central.eventMouseOutput(-1, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mouseReleased(MouseEvent e)
  {
    central.eventMouseOutput(1, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }

  public void mouseClicked(MouseEvent e)
  {
    central.eventMouseOutput(0, e.getX(), e.getY(), e.getButton(), e.getClickCount());
  }
}