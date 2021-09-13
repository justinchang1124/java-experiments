
public class SavingsAccount extends Account
{
  private final double interestRate;

  public SavingsAccount(int b, String c, double apy)
  {
    super(b, c);
    interestRate = apy / 12;
  }
  
  public void progressMonth()
  {
    // deposit((int) ( interestRate    * getBalance()));
    setBalance((int) ((interestRate+1) * getBalance()));
  }
  
  public static void main(String[] args)
  {
    Account s = new SavingsAccount(100000, "Dr. Z", .24);
    
    for (int i = 0; i < 12; i++)
    {
      ( (SavingsAccount) s ).progressMonth();
      System.out.println(s);
    }
  }
}
