import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * ProcessController specifies a list of abstract methods that allow events to
 * occur in response to initialization, tick, render, and input prompts, which
 * are managed and sent here by Loader.
 * 
 * @author Justin C
 */
public abstract class ProcessController

    implements KeyListener, MouseMotionListener, MouseWheelListener, MouseListener
{
  /**
   * key: Corresponds to the Virtual Keyboard key that was most
   * recently interacted with.
   * 
   * xM and yM: The x and y coordinates of the mouse.
   * 
   * clicks: The number of times that the mouse has clicked.
   * 
   * button: Corresponds to the mouse button that was most recently
   * interacted with. (1 = left mouse button, 2 = scroll wheel button,
   * 3 = right mouse button)
   * 
   * wheel: The direction of the most recent wheel rotation.
   */
  public int keyCode, xM, yM, clicks, button, wheel;

  public abstract void initialize();

  public abstract void tick(long frameCount);

  public abstract void render(Graphics g, long frameCount);

  public abstract void keyPressed();

  public abstract void keyTyped();

  public abstract void keyReleased();

  public abstract void wheelInput();

  /**
   * Manages all mouse input, subdivided into 7 events by the eventDescription.
   * 
   * @param eventDescription
   * Returns -3 when the mouse enters a window. Returns 3 when the mouse exits a
   * window. Returns -2 when the mouse is moved. Returns 2 when the mouse is
   * dragged. Returns -1 when the mouse is pressed down. Returns 1 when the
   * mouse is released. Returns 0 when the mouse is clicked and released.
   */
  public abstract void mouseInput(int eventDescription);

  /**
   * Detects if the mouse is within the rectangle that has a top-left corner of
   * (x, y) and dimensions (width, height).
   */
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
      return true;
    return false;
  }

  /*
   * All subsequent methods take a KeyEvent, MouseWheelEvent, or MouseEvent as a
   * parameter. The event's traits are used to adjust the fields of this class
   * accordingly and call the abstract method associated with the event.
   */

  public void keyPressed(KeyEvent e)
  {
    keyCode = e.getKeyCode();
    keyPressed();
  }

  public void keyTyped(KeyEvent e)
  {
    keyCode = e.getKeyCode();
    keyTyped();
  }

  public void keyReleased(KeyEvent e)
  {
    keyCode = e.getKeyCode();
    keyReleased();
  }

  public void mouseWheelMoved(MouseWheelEvent e)
  {
    wheel = e.getWheelRotation();
    wheelInput();
  }

  public void setMouseValues(MouseEvent e)
  {
    xM = e.getX();
    yM = e.getY();
    button = e.getButton();
    clicks = e.getClickCount();
  }

  public void mouseEntered(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(-3);
  }

  public void mouseExited(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(3);
  }

  public void mouseMoved(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(-2);
  }

  public void mouseDragged(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(2);
  }

  public void mousePressed(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(-1);
  }

  public void mouseReleased(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(1);
  }

  public void mouseClicked(MouseEvent e)
  {
    setMouseValues(e);
    mouseInput(0);
  }
}
