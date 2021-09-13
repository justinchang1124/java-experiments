
public class GlobalCheckingAccount extends CheckingAccount implements Payable
{
  private double rate;

  public GlobalCheckingAccount(int b, String c, double r)
  {
    super(b, c);
    rate = r;
  }

  public void pay(Purchase p)
  {
    // if the name of the purchase contains international,
    // multiply by conversion rate
    int cost = p.getCost();

    if (p.getName().indexOf("(international)") > -1)
      cost = (int) ( rate * p.getCost() );
    p.pay(withdraw(cost));
  }
}
