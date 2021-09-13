import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * TakeInput implements keyboard, mouse, and mouse wheel listeners. It provides methods by which
 * the outputs of the aforementioned devices can be passed to Central in a sanitized way.
 * 
 * @author Justin C
 */
public class TakeInput implements KeyListener, MouseMotionListener, MouseWheelListener, MouseListener
{
  private Central central;

  public TakeInput(Central central)
  {
    this.central = central;
  }

  public void keyPressed(KeyEvent e)
  {
    central.eventKeyboardOutput(-1, e.getKeyCode());
  }

  public void keyTyped(KeyEvent e)
  {
    central.eventKeyboardOutput(0, e.getKeyCode());
  }

  public void keyReleased(KeyEvent e)
  {
    central.eventKeyboardOutput(1, e.getKeyCode());
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

  public void mouseWheelMoved(MouseWheelEvent e)
  {
    central.eventWheelOutput(e.getWheelRotation());
  }
}