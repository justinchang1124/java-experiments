
public class CreditCard extends SavingsAccount implements Payable
{ 
  public CreditCard(int b, String c, double apy)
  {
    super(b, c, apy);
  }
  
  public void progressMonth()
  {
    if (super.getBalance() < 0)
      super.progressMonth();
  }
  
  public void pay(Purchase p)
  {
    p.pay(withdraw(p.getCost()));
  }
  
  public void payFrom(Account other)
  {
    this.deposit(other.withdraw(getBalance()));
  }
}
