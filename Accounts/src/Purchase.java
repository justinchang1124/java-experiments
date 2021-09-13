/**
 * This class just exists to take money from an account from a retailer
 *
 * Part of the Accounts exercise
 * Author: Nicholas Zufelt
 * Course: AP CS A
 * Date: 2018-02-09
 */

public class Purchase
{
  private int cost;

  private String name;

  public Purchase(int c, String n)
  {
    cost = c;
    name = n;
  }

  public int getCost()
  {
    return cost;
  }

  public String getName()
  {
    return name;
  }

  public void pay(int amount)
  {
    cost -= amount;
  }

  public boolean paid()
  {
    return cost == 0;
  }
}
