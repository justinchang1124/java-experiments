import java.util.ArrayList;

/**
 * Models a trader in the stock market.
 * 
 * @author Justin C
 */
public class Trader
{
  /**
   * The amount of free cash that the trader possesses.
   */
  private double cash;

  /**
   * The trader's portfolio of stocks.
   */
  private ArrayList<Stock> portfolio;

  /** 
   * Creates a trader possessing $1,000,000.00 by default.
   */
  public Trader()
  {
    this(1000000);
  }
  
  /**
   * Creates a trader initially possessing a specified amount. 
   * 
   * @param cash
   * The amount of cash that the trader starts with.
   */
  public Trader(double cash)
  {
    this.cash = cash;
    portfolio = new ArrayList<Stock>();
  }

  /**
   * Overrides the default toString().
   */
  public String toString()
  {
    String target = "";
    
    target += "Cash possessed: $" + cash + "\n";
    target += "Stocks that you possess: ";

    if (portfolio.size() == 0)
      target += "\n" + "No stocks possessed.";

    for (int i = 0; i < portfolio.size(); i++)
    {
      target += "\n" + portfolio.get(i);
    }

    return target;
  }
  
  /**
   * Returns the portfolio of the trader. 
   * 
   * @return
   * Portfolio of the trader.
   */
  public ArrayList<Stock> getPortfolio()
  {
    return portfolio;
  }
  
  /**
   * Buys a specified number of shares of a possessed stock.
   * 
   * @param stockIndex
   * The index of the stock.
   * @param quantity
   * The quantity transferred.
   */
  public void buy(int stockIndex, int quantity)
  {
    if (cash >= quantity * portfolio.get(stockIndex).getListing())
    {
      portfolio.get(stockIndex).buy(quantity);
      cash -= quantity * portfolio.get(stockIndex).getListing();
    }
    else
    {
      throw new IllegalArgumentException("You do not possess the necessary funds!");
    }
  }
  
  /**
   * Sells a specified number of shares of a possessed stock.
   * 
   * @param stockIndex
   * The index of the stock.
   * @param quantity
   * The quantity transferred.
   */
  public void sell(int stockIndex, int quantity)
  {
    if (quantity <= portfolio.get(stockIndex).getNumShares())
    {
      portfolio.get(stockIndex).sell(quantity);
      cash += quantity * portfolio.get(stockIndex).getListing();
      if (portfolio.get(stockIndex).getNumShares() <= 0)
      {
        portfolio.remove(stockIndex);
      }
    }
    else
    {
      throw new IllegalArgumentException("You do not possess this many shares!");
    }
  }
  
  public void progressOneDay()
  {
    for (int i = 0; i < portfolio.size(); i++)
    {
      portfolio.get(i).progressOneDay();
    }
  }
}
