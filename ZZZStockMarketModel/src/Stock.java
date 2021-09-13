/**
 * Models a stock.
 * 
 * @author Justin C
 */
public class Stock
{
  /**
   * The current price per unit of stock, given in dollars.
   */
  private double listing;
  
  /**
   * The original price of the stock.
   */
  private final double originalListing;

  /**
   * The current volatility of the stock. Every day, the stock 
   * changes within a range of [original - volatility, original + volatility).
   */
  private double volatility;

  /**
   * The current number of shares of stock held by the player.
   * -1 implies that the stock is held by the market and not the player.
   */
  private int numShares;

  /**
   * Constructs a standard stock with a random listing within [0, 100) 
   * and a random volatility within [0, 10) that belongs to the market.
   */
  public Stock()
  {
    originalListing = 100 * Math.random();
    listing = originalListing;
    volatility = 10 * Math.random();
    numShares = -1;
  }

  /**
   * Copy constructor that specifies the number of shares.
   * 
   * @param other
   * Stock to be copied.
   * @param numShares
   * Number of shares bought.
   */
  public Stock(Stock other, int numShares)
  {
    originalListing = other.originalListing;
    listing = originalListing;
    volatility = other.volatility;
    this.numShares = numShares;
  }

  /**
   * Returns the listing of this stock.
   * 
   * @return
   * Listing of this stock.
   */
  public double getListing()
  {
    return listing;
  }

  /**
   * Returns the number of shares of this stock held.
   * 
   * @return
   * Number of shares of this stock held.
   */
  public int getNumShares()
  {
    return numShares;
  }

  /**
   * Overrides the default toString().
   */
  public String toString()
  {
    String target = "";
    
    target += "   Stock listing: $" + listing;
    
    if (numShares > -1)
    {
      target += "\n" + "      (" + numShares + " shares held, total worth $" + numShares * listing + ")";
      target += "\n" + "      Original Listing: $" + originalListing;
    }
    
    return target;
  }

  /**
   * Sells all shares.
   */
  public void sell()
  {
    sell(numShares);
  }

  /**
   * Sells a specified number of shares.
   * 
   * @param quantity
   * Number of shares to be sold.
   */
  public void sell(int quantity)
  {
    numShares = Math.max(0, numShares - quantity);
  }

  /**
   * Buys a single share.
   */
  public void buy()
  {
    buy(1);
  }

  /**
   * Buys a specified number of shares.
   * 
   * @param quantity
   * Number of shares to be bought.
   */
  public void buy(long quantity)
  {
    if (numShares < 0)
      numShares = 0;
    numShares += quantity;
  }

  /**
   * Applies the effect of volatility to the listing.
   * Ensures that the price of a stock can never go below zero. 
   */
  public void progressOneDay()
  {
    listing = Math.max(0, listing + volatility * ( 2 * Math.random() - 1 ));
  }
}