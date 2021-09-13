public abstract class Account
{
  private final int accountNumber;
  private int balance;  // in pennies
  private final String customerName;

  public Account(int b, String c)
  {
    accountNumber = makeAccountNumber();
    balance = b;
    customerName = c;
  }

  public int deposit(int amount)
  {
    if (amount > 0)
      balance += amount;
    else throw new IllegalArgumentException("Only positive deposits allowed");
    return amount;
  }

  public int withdraw(int amount)
  {
    if (amount > 0)
      balance -= amount;
    else throw new IllegalArgumentException("Only positive withdrawals allowed");
    return amount;
  }

  public int getBalance()
  {
    return balance;
  }
  
  public void setBalance(int amount)
  {
    balance = amount;
  }

  public String getCustomerName()
  {
    return customerName;
  }

  public int getAccountNumber()
  {
    return accountNumber;
  }

  public String toString()
  {
    return String.format("%s's account (#%d): %d",
                         customerName,
                         accountNumber,
                         balance);
  }

  // Generate a random 7-digit number
  private static int makeAccountNumber()
  {
    return (int)(Math.random() * 9000000 + 1000000);
  }
  
  public abstract void progressMonth();

//  public static void main(String[] args) {
//    Account a = new Account(1240, "Dr. Z");
//    for (int i = 0; i < 20; i++)
//    {
//      a.deposit(i * 5 + 1);
//      System.out.println(a);
//    }
//  }
}