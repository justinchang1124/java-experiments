
public class CertificateOfDeposit extends SavingsAccount
{
  private int months;

  public CertificateOfDeposit(int b, String c, double apy, int months)
  {
    super(b, c, apy);
    this.months = months;
  }

  // if you withdraw early, 25% penalty
  public int withdraw(int amount)
  {
    if (months > 0)
    {
      return (int) ( .75 * super.withdraw(amount) );
    }
    else
    {
      return (int) ( 1.0 * super.withdraw(amount) );
    }
  }

  public void progressMonth()
  {
    super.progressMonth();
    if (months > 0)
      months -= 1;
  }
}
