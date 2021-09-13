import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stimulates actions that traders can take in the stock market.
 * 
 * @author Justin C
 */
public class StockMarket
{
  /**
   * The single trader in this particular stock market.
   */
  public Trader trader;

  /**
   * The list of offerings in the market.
   */
  public ArrayList<Stock> offerings;

  /**
   * The kboard used for input.
   */
  public static Scanner kboard;

  /**
   * You've heard of the bull market ...
   */
  public static StockMarket moose = new StockMarket();

  public static final int numMarket = 5;

  /**
   * Constructs a market.
   */
  public StockMarket()
  {
    kboard = new Scanner(System.in);
    offerings = new ArrayList<Stock>();
    trader = new Trader();

    for (int i = 0; i < numMarket; i++)
    {
      offerings.add(new Stock());
    }
  }

  /**
   * Overrides the default toString().
   */
  public String toString()
  {
    String target = "";
    target += "Stocks of the Day: ";

    for (int i = 0; i < 5; i++)
    {
      target += "\n" + offerings.get(i);
    }

    return target;
  }

  /**
   * Runs one set of user inputs.
   */
  public void process()
  {
    // Displays all necessary information.
    System.out.println(moose);
    System.out.println();
    System.out.println(trader);
    System.out.println();
    System.out.println("Next action: (type 'h' for help) ");

    // Takes the necessary input.
    String input = kboard.nextLine();

    // Determines whether the command is valid.
    if (input.length() == 0)
    {
      throw new IllegalArgumentException("Not a valid command");
    }
    
    int notDigits = 0;
    
    for (int i = 0; i < input.length(); i++)
    {
      if (!Character.isDigit(input.charAt(i)))
      {
        notDigits++;
      }
    }
    
    if (notDigits > 3)
    {
      throw new IllegalArgumentException("Not a valid command");
    }
    
    char arg = input.charAt(0);

    // If a valid command is given ... 
    if (arg == 's' || arg == 'm' || arg == 'b' || arg == 'p' || arg == 'e' || arg == 'h')
    {
      // Sell command.
      if (arg == 's')
      {
        int space1 = input.indexOf(" ");
        int space2 = input.lastIndexOf(" ");

        int index = Integer.parseInt(input.substring(space1 + 1, space2));
        int quantity = Integer.parseInt(input.substring(space2 + 1, input.length()));
     
        // Sells all stock shares.
        if (quantity == 0)
        {
          trader.sell(index, trader.getPortfolio().get(index).getNumShares());
        }

        // Sells a specified stock share.
        if (quantity > 0)
        {
          trader.sell(index, quantity);
        }
      }

      // Buy more of an already-owned stock.
      if (arg == 'm')
      {
        int space1 = input.indexOf(" ");
        int space2 = input.lastIndexOf(" ");

        int stockI = Integer.parseInt(input.substring(space1 + 1, space2));
        int quantity = Integer.parseInt(input.substring(space2 + 1, input.length()));

        trader.buy(stockI, quantity);
      }

      // Buy a previously unowned stock.
      if (arg == 'b')
      {
        int space1 = input.indexOf(" ");
        int space2 = input.lastIndexOf(" ");

        int stockI = Integer.parseInt(input.substring(space1 + 1, space2));
        int quantity = Integer.parseInt(input.substring(space2 + 1, input.length()));

        trader.getPortfolio().add(new Stock(offerings.get(stockI), 0));
        trader.buy(trader.getPortfolio().size() - 1, quantity);
      }

      // Action that progresses the market to the next day.
      if (arg == 'p')
      {
        System.out.println("Passing one day ...");
        trader.progressOneDay();

        for (int i = 0; i < numMarket; i++)
        {
          offerings.remove(0);
        }

        for (int i = 0; i < numMarket; i++)
        {
          offerings.add(new Stock());
        }
      }
      
      // Exits the program.
      if (arg == 'e')
      {
        System.exit(1);
      }

      // Help command.
      if (arg == 'h')
      {
        System.out.println();
        System.out.println("'s' = Sell stocks (enter the index and quantity)");
        System.out.println("'m' = More shares of an owned stock (enter the index and quantity)");
        System.out.println("'b' = Buys market stock (enter the index and quantity)");
        System.out.println("'p' = Passes one day");
        System.out.println("'e' = Exits the program");
        System.out.println("'h' = Helpful list of commands");
        System.out.println();
      }
    }
    else
    {
      throw new IllegalArgumentException("Not a valid command!");
    }
  }

  /**
   * Main method.
   * 
   * @param args
   * Not used.
   */
  public static void main(String[] args)
  {
    moose = new StockMarket();
    while (true)
      moose.process();
  }
}
