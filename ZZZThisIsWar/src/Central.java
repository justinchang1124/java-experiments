import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Simulates a card game of War.
 * The rules that this program is modeled on can be found here:
 * https://en.wikipedia.org/wiki/War_(card_game)
 * 
 * @author Justin C
 * CSC 500 Period 1
 * Ms. Bednarcik
 */
public class Central
{
  public static final int SCREEN_WIDTH = 900;

  public static final int SCREEN_HEIGHT = 900;

  public static final int[] veryLong1 = {2, 13, 8, 7, 5, 9, 4, 6, 7, 4, 2, 14, 11, 6, 10, 9, 11, 2, 5, 4, 12, 10, 13, 11, 11, 6};

  public static final int[] veryLong2 = {14, 5, 4, 8, 8, 12, 9, 9, 3, 13, 12, 6, 2, 13, 3, 12, 14, 7, 14, 10, 8, 7, 3, 10, 3, 5};

  private int xM, yM, clicks, button, eventMouse;

  // Used in the game.
  public static Deck player1, player2;

  public static Card drawn1, drawn2;

  public static int turnCount;

  public static int numOfWar;

  public static long numGames;

  public static boolean fastForward;

  public static boolean terminated;

  public static boolean wonByP1;

  public static boolean isWar;

  // Statistics
  public static Deck origin1, origin2, longest1, longest2;

  public static int longestTurns;

  /**
   * Initializes a game of war.
   */
  public void initialize()
  {
    turnCount = 0;
    terminated = false;

    // Gives player 1 a full deck of 52 cards and player 2 an empty deck.
    player1 = new Deck();
    player1.empty();
    player2 = new Deck();
    player2.empty();

    for (int i = 0; i < 26; i++)
    {
      player1.addCardBack(new Card(veryLong1[i], 0));
      player2.addCardBack(new Card(veryLong2[i], 0));
    }

    // Shuffles player 1's deck.
    // player1.shuffle(52 * 4);

    // Player 1 gives player 2 half of their cards.
    // for (int i = 0; i < 26; i++)
    // {
    // player2.addCardBack(player1.drawCardBack());
    // }

    // Records the original sets of 26 cards.
    origin1 = new Deck(player1);
    origin2 = new Deck(player2);
  }

  /**
   * Renders the screen.
   * 
   * @param g
   * The graphics that this method acts on.
   */
  public void render(Graphics g)
  {
    // Sets the background.
    g.setColor(Color.pink);
    g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

    // Draws the 3 GUI buttons.
    g.setColor(Color.gray);
    g.fillRect(50, 760, 300, 64);
    g.fillRect(550, 760, 300, 64);
    g.fillRect(50, 50, 300, 64);
    showText(g, "Start New Game", 100, 800);
    showText(g, "Print Statistics", 610, 800);

    // Draws the fast-forward button text.
    if (fastForward)
    {
      showText(g, "Fast Forward On", 90, 85);
    }
    else
    {
      showText(g, "Fast Forward Off", 90, 85);
    }

    // Shows the scoreboard.
    showText(g, "P1's Card Count: " + player1.ordering.length, 600, 50);
    showText(g, "P2's Card Count: " + player2.ordering.length, 600, 100);
    showText(g, "Turn Count: " + turnCount, 600, 150);

    // Victory messages.
    if (terminated && !wonByP1)
    {
      showText(g, "Player 2 is the victor!", 320, 700);
    }

    if (terminated && wonByP1)
    {
      showText(g, "Player 1 is the victor!", 320, 700);
    }

    // Shows the drawn cards once the game begins, and a start message otherwise.
    if (turnCount > 0)
    {
      // Draws the cards.
      g.setColor(Color.white);
      g.fillRect(100, 200, 300, 400);
      g.fillRect(500, 200, 300, 400);

      g.setColor(Color.black);
      g.drawRect(100, 200, 300, 400);
      g.drawRect(500, 200, 300, 400);

      showText(g, "Player 1's Card", 150, 240);
      showText(g, "Player 2's Card", 550, 240);
      showText(g, "" + drawn1, 200, 440);
      showText(g, "" + drawn2, 600, 440);

      if (isWar)
      {
        // Shows the war message.
        showWar(g);

        // Shows the results of the war.
        if (wonByP1)
        {
          showText(g, "Player 1 Wins the War!", 300, 650);
          showTextBig(g, "+" + numOfWar + " Cards!", 140, 540);
        }
        else
        {
          showText(g, "Player 2 Wins the War!", 300, 650);
          showTextBig(g, "+" + numOfWar + " Cards!", 540, 540);
        }
      }
      else
      {
        // Shows the battle results.
        if (wonByP1)
        {
          showText(g, "Player 1 Wins the Battle!", 300, 650);
        }
        else
        {
          showText(g, "Player 2 Wins the Battle!", 300, 650);
        }
      }
    }
    else
    {
      showText(g, "Right click to play!", 320, 500);
    }

    // Continuously plays the game if fast forward is enabled.
    if (fastForward)
    {
      playGame();
    }
  }

  public static void playGame()
  {
    // Any previous war terminates.
    if (!terminated)
      isWar = false;

    // Detects if the game has ended.
    if (player1.ordering.length < 1)
    {
      terminated = true;
      wonByP1 = false;
    }

    if (player2.ordering.length < 1)
    {
      terminated = true;
      wonByP1 = true;
    }

    if (!terminated)
    {
      turnCount++;

      // Each player draws a card.
      drawn1 = player1.drawCardBack();
      drawn2 = player2.drawCardBack();

      // If the cards are of unequal value, a battle occurs.
      // Note that cards are picked up by the winning player in this order:
      // card closest to them => card furthest from them
      if (Integer.parseInt(drawn1.getRank()) < Integer.parseInt(drawn2.getRank()))
      {
        player2.addCardFront(drawn2);
        player2.addCardFront(drawn1);
        wonByP1 = false;
      }

      if (Integer.parseInt(drawn1.getRank()) > Integer.parseInt(drawn2.getRank()))
      {
        player1.addCardFront(drawn1);
        player1.addCardFront(drawn2);
        wonByP1 = true;
      }

      // If the cards are of equal value
      if (Integer.parseInt(drawn1.getRank()) == Integer.parseInt(drawn2.getRank()))
      {
        boolean resolved = false;
        isWar = true;

        // The cards to be won by the winner of the war
        Deck prize = new Deck();
        prize.empty();
        prize.addCardBack(drawn1);
        prize.addCardFront(drawn2);

        // While the war continues
        while (!resolved)
        {
          turnCount++;

          // If a player runs out of cards, they lose the war
          if (player1.ordering.length < 2)
          {
            terminated = true;
            wonByP1 = false;
            return;
          }
          if (player2.ordering.length < 2)
          {
            terminated = true;
            wonByP1 = true;
            return;
          }

          // Adds 4 cards to the prize pile, as is customary
          Card unknown1 = player1.drawCardBack();
          Card unknown2 = player2.drawCardBack();
          Card known1 = player1.drawCardBack();
          Card known2 = player2.drawCardBack();

          prize.addCardBack(unknown1);
          prize.addCardFront(unknown2);
          prize.addCardBack(known1);
          prize.addCardFront(known2);

          // When two unequal cards are drawn
          if (Integer.parseInt(known1.getRank()) > Integer.parseInt(known2.getRank()))
          {
            resolved = true;
            numOfWar = prize.ordering.length;

            for (int i = 0; i < numOfWar; i++)
            {
              player1.addCardFront(prize.drawCardBack());
            }
          }

          if (Integer.parseInt(known1.getRank()) < Integer.parseInt(known2.getRank()))
          {
            resolved = true;
            numOfWar = prize.ordering.length;

            for (int i = 0; i < numOfWar; i++)
            {
              player2.addCardFront(prize.drawCardFront());
            }
          }
        }
      }

      // Changes statistics if needed
      if (turnCount > longestTurns)
      {
        longestTurns = turnCount;
        longest1 = new Deck(origin1);
        longest2 = new Deck(origin2);
      }
    }
  }

  /**
   * Manages mouse input.
   * 
   * @param eventDescription
   * -1 if pressed, 1 if released
   * @param xMouse
   * @param yMouse
   * @param buttonNum
   * @param clickCount
   */
  public void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount)
  {
    eventMouse = eventDescription;
    xM = xMouse;
    yM = yMouse;
    button = buttonNum;
    clicks = clickCount;

    // If the user right clicks, gameplay continues.
    if (clicks > 0 && eventMouse == 1 && button == 3 && !fastForward)
    {
      playGame();
    }

    // If the user left clicks
    if (clicks > 0 && eventMouse == 1 && button == 1)
    {
      // Starts a new game
      if (mouseOver(50, 760, 300, 64))
      {
        initialize();
      }

      // Prints out the statistics over all games played since the start of this program
      if (mouseOver(550, 760, 300, 64))
      {
        System.out.println("Longest Game Decks:");
        System.out.println(longest1 + "\n");
        System.out.println(longest2);

        System.out.println("\nLongest Number of Turns in a Game");
        System.out.println(longestTurns);
      }

      // Enables or disables fast forward
      if (mouseOver(50, 50, 300, 64))
      {
        fastForward = !fastForward;
      }
    }
  }

  /**
   * Determines if the mouse is over a specified rectangle.
   * 
   * @param x
   * @param y
   * @param width
   * @param height
   * @return
   */
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
    {
      return true;
    }
    return false;
  }

  /**
   * Shows a string of text.
   * 
   * @param g
   * @param text
   * @param x
   * @param y
   */
  public static void showText(Graphics g, String text, int x, int y)
  {
    g.setFont(new Font("Times New Roman", 3, 30));
    g.setColor(Color.black);
    g.drawString(text, x, y);
  }

  /**
   * Shows an emphatic string of text.
   * 
   * @param g
   * @param text
   * @param x
   * @param y
   */
  public static void showTextBig(Graphics g, String text, int x, int y)
  {
    g.setFont(new Font("Times New Roman", 3, 60));
    g.setColor(Color.red);
    g.drawString(text, x, y);
  }

  /**
   * Shows the war message.
   * 
   * @param g
   */
  public static void showWar(Graphics g)
  {
    g.setFont(new Font("Comic Sans", 3, 120));
    g.setColor(Color.red);
    g.drawString("WAR!", 300, 400);
  }

  /**
   * Starts the program.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, "This is War!");
  }
}
