
public class CheckingAccount extends Account implements Payable
{
  public CheckingAccount(int b, String c)
  {
    super(b, c);
  }
  
  public void progressMonth()
  {
    
  }
  
  public void pay(Purchase p)
  {
    p.pay(withdraw(p.getCost()));
  }
}
