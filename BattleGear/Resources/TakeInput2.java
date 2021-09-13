import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TakeInput2 implements KeyListener, MouseMotionListener, MouseWheelListener, MouseListener
{

  private int keyCode;
  private int xM, yM, clickCount, button;
  private int wheelRotation;
  private int eventMouse, eventKeyboard;

  // Detects whether or not the mouse is over a certain point.
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
      return true;
    return false;
  }

  private void eventKeyboardOutput(int eventDescription, KeyEvent e)
  {
    keyCode = e.getKeyCode();
    eventKeyboard = eventDescription;
  }

  private void eventMouseOutput(int eventDescription, MouseEvent e)
  {
    xM = e.getX();
    yM = e.getY();
    button = e.getButton();
    clickCount = e.getClickCount();
    eventMouse = eventDescription;
    int midX1 = (Loader.WIDTH - 200) / 2;

    if (clickCount > 0 && eventMouse == 3)
    {
//      System.out.println(Loader.country);
//      System.out.println(Loader.difficulty);
//      System.out.println(Loader.gameMode);
      if (Loader.state == STATE.MenuCredit)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.MenuHelp)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.MenuMedal)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.MenuStat)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.MenuTrophy)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.MenuOptions)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.Battle)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.isPaused = !Loader.isPaused;
        }
      }
      if (Loader.state == STATE.OpenMain)
      {
        if (mouseOver(10, 150, 200, 64))
        {
          Loader.state = STATE.Campaign;
        }
        
        if (mouseOver(midX1, 750, 200, 64))
        {
          SaveManager.saveAllConstants();
          System.exit(1);
        }
        
        if (mouseOver(10, 250, 200, 64))
        {
          Loader.state = STATE.Single;
          Loader.gameMode = 2;
        }

        if (mouseOver(10, 350, 200, 64))
        {
          Loader.state = STATE.MenuHelp;
        }

        if (mouseOver(10, 450, 200, 64))
        {
          Loader.state = STATE.MenuOptions;
        }

        if (mouseOver(10, 550, 200, 64))
        {
          Loader.state = STATE.MenuCredit;
        }

        if (mouseOver(780, 350, 200, 64))
        {
          Loader.state = STATE.MenuMedal;
        }

        if (mouseOver(780, 450, 200, 64))
        {
          Loader.state = STATE.MenuStat;
        }

        if (mouseOver(780, 550, 200, 64))
        {
          Loader.state = STATE.MenuTrophy;
        }
      }
      if (Loader.state == STATE.Preview)
      {

      }
      if (Loader.state == STATE.Campaign)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
        if (Loader.level <= 1)
        {
          if (mouseOver(200, 330, 200, 64))
          {
            Campaign.highlight1 = 0;
          }
          if (mouseOver(400, 330, 200, 64))
          {
            Campaign.highlight1 = 1;
          }
          if (mouseOver(600, 330, 200, 64))
          {
            Campaign.highlight1 = 2;
          }
          if (mouseOver(100, 430, 200, 64))
          {
            Campaign.highlight2 = 0;
          }
          if (mouseOver(300, 430, 200, 64))
          {
            Campaign.highlight2 = 1;
          }
          if (mouseOver(500, 430, 200, 64))
          {
            Campaign.highlight2 = 2;
          }
          if (mouseOver(700, 430, 200, 64))
          {
            Campaign.highlight2 = 3;
          }
          if (mouseOver(250, 530, 200, 64))
          {
            Campaign.highlight3 = 0;
          }
          if (mouseOver(550, 530, 200, 64))
          {
            Campaign.highlight3 = 1;
          }
          if (mouseOver(230, 250, 500, 64))
          {
            Loader.country = Campaign.highlight1;
            Loader.difficulty = Campaign.highlight2;
            Loader.gameMode = Campaign.highlight3;
            Loader.state = STATE.Battle;
          }
        }
        else
        {
          
        }
      }
      if (Loader.state == STATE.Single)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          Loader.state = STATE.OpenMain;
        }
      }
      if (Loader.state == STATE.SetupSkill)
      {

      }
      if (Loader.state == STATE.SetupUnit)
      {

      }
    }
  }

  private void eventWheelOutput(MouseWheelEvent e)
  {
    wheelRotation = e.getWheelRotation();
  }

  public void keyPressed(KeyEvent e)
  {
    eventKeyboardOutput(0, e);
  }

  public void keyReleased(KeyEvent e)
  {
    eventKeyboardOutput(1, e);
  }

  public void keyTyped(KeyEvent e)
  {
    eventKeyboardOutput(2, e);
  }

  public void mouseMoved(MouseEvent e)
  {
    eventMouseOutput(0, e);
  }

  public void mouseDragged(MouseEvent e)
  {
    eventMouseOutput(1, e);
  }

  public void mousePressed(MouseEvent e)
  {
    eventMouseOutput(2, e);
  }

  public void mouseReleased(MouseEvent e)
  {
    eventMouseOutput(3, e);
  }

  public void mouseEntered(MouseEvent e)
  {
    eventMouseOutput(4, e);
  }

  public void mouseExited(MouseEvent e)
  {
    eventMouseOutput(5, e);
  }

  public void mouseClicked(MouseEvent e)
  {
    eventMouseOutput(6, e);
  }

  public void mouseWheelMoved(MouseWheelEvent e)
  {
    eventWheelOutput(e);
  }
}