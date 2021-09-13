import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Simulates a game of tick-tac-toe.
 * CSC 500, Ms. Bednarcik
 * Period 1
 * 
 * @author Justin C
 */
public class Central
{
  public static final int SCREEN_WIDTH = 900, SCREEN_HEIGHT = 900;

  private int xM, yM, clicks, eventMouse, button;

  public static int[] board = new int[9];

  public static boolean yourTurn = true;

  public static int yourScore, theirScore;

  public static boolean terminated;

  /**
   * Initializes the program.
   */
  public void initialize()
  {
    for (int i = 0; i < 9; i++)
    {
      board[i] = 0;
    }

    terminated = false;
  }

  /**
   * Draws text on the screen.
   */
  public static void showText(Graphics g, String text, int x, int y)
  {
    g.setFont(new Font("Times New Roman", 3, 30));
    g.setColor(Color.black);
    g.drawString(text, x, y);
  }

  /**
   * Verifies whether or not three squares are a selected target.
   */
  public static boolean verifyThree(int a, int b, int c, int target)
  {
    if (board[a] == target && board[b] == target && board[c] == target)
      return true;
    return false;
  }
  
  /**
   * Checks if a certain array equals the current board position.
   * 
   * @param other
   * @return
   */
  public static boolean arrayIs(int[] other)
  {
    for (int i = 0; i < 9; i++)
    {
      if (!(board[i] == other[i]))
        return false;
    }
    
    return true;
  }

  /**
   * Checks whether or not a player has won the game and adjusts their score appropriately.
   */
  public static char checkBoard()
  {
    // All Rows, Columns, Diagonals for P1
    if (verifyThree(0, 1, 2, 1) || verifyThree(3, 4, 5, 1) || 
        verifyThree(6, 7, 8, 1) || verifyThree(0, 3, 6, 1) || 
        verifyThree(1, 4, 7, 1) || verifyThree(2, 5, 8, 1) || 
        verifyThree(0, 4, 8, 1) || verifyThree(2, 4, 6, 1))
    {
      yourScore++;
      terminated = true;
      return 'o';
    }

    // All Rows, Columns, Diagonals for P2
    if (verifyThree(0, 1, 2, 2) || verifyThree(3, 4, 5, 2) || 
        verifyThree(6, 7, 8, 2) || verifyThree(0, 3, 6, 2) || 
        verifyThree(1, 4, 7, 2) || verifyThree(2, 5, 8, 2) || 
        verifyThree(0, 4, 8, 2) || verifyThree(2, 4, 6, 2))
    {
      theirScore++;
      terminated = true;
      return 'x';
    }

    return ' ';
  }

  /**
   * Renders the board.
   */
  public void render(Graphics g)
  {
    if (!terminated)
      checkBoard();

    // The computer's actions are determined here.
    if (!yourTurn && !terminated)
    {
      // Perfect play for the first move.
      if (arrayIs(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0})) 
        board[0] = 2;
      else
        if (arrayIs(new int[]{0, 0, 0, 0, 1, 0, 0, 0, 0}))
          board[0] = 2;
        else
          if (arrayIs(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0}) || 
              arrayIs(new int[]{0, 1, 0, 0, 0, 0, 0, 0, 0}) || 
              arrayIs(new int[]{0, 0, 1, 0, 0, 0, 0, 0, 0}) || 
              arrayIs(new int[]{0, 0, 0, 1, 0, 0, 0, 0, 0}) ||
              arrayIs(new int[]{0, 0, 0, 0, 0, 1, 0, 0, 0}) ||
              arrayIs(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0}) ||
              arrayIs(new int[]{0, 0, 0, 0, 0, 0, 0, 1, 0}) ||
              arrayIs(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}))
            board[4] = 2;
          else 
          {
            // Fills the next available space in order to give the user a fighting chance.
            for (int i = 0; i < 9; i++)
            {
              if (board[i] == 0)
              {
                board[i] = 2;
                break;
              }
            }
          }
          
      yourTurn = !yourTurn;
    }

    // Draws the background.
    g.setColor(Color.pink);
    g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    // Draws the reset buttons and GUI.
    g.setColor(Color.gray);
    g.fillRect(50, 760, 300, 64);
    g.fillRect(550, 760, 300, 64);
    showText(g, "Start Game Second", 80, 800);
    showText(g, "Start Game First", 590, 800);
    showText(g, yourScore + "-" + theirScore, 430, 800);

    // Draws the board.
    for (int i = 0; i < 9; i++)
    {
      if (board[i] == 0)
        g.setColor(Color.white);
      if (board[i] == 1)
        g.setColor(Color.blue);
      if (board[i] == 2)
        g.setColor(Color.red);

      g.fillRect(100 + 250 * ( i % 3 ), 20 + 250 * ( i / 3 ), 200, 200);
    }
  }

  /**
   * Manages all mouse input.
   */
  public void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount)
  {
    eventMouse = eventDescription;
    xM = xMouse;
    yM = yMouse;
    button = buttonNum;
    clicks = clickCount;

    // Only responds if the user left clicks.
    if (clicks > 0 && eventMouse == 1 && button == 1)
    {
      if (yourTurn && !terminated)
      {
        for (int i = 0; i < 9; i++)
        {
          if (board[i] == 0 && mouseOver(100 + 250 * ( i % 3 ), 20 + 250 * ( i / 3 ), 200, 200))
          {
            board[i] = 1;
            yourTurn = !yourTurn;
          }
        }
      }

      // Reset button that has the computer play first.
      if (mouseOver(50, 760, 300, 64))
      {
        initialize();
        yourTurn = false;
      }

      // Reset button that has you play first.
      if (mouseOver(550, 760, 300, 64))
      {
        initialize();
        yourTurn = true;
      }
    }
  }

  /**
   * Detects if the mouse is within the specified rectangle.
   */
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
      return true;
    return false;
  }

  /**
   * Starts the program.
   */
  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "TickTacToe");
  }
}