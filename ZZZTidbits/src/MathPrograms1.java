import java.util.Scanner;

public class MathPrograms1
{

  private static Scanner keyboard = new Scanner(System.in);
  public static int setting;
  
  public static int fib(int n)
  {
    return 0;
  }
  
  public static int middleDigits(int n)
  {
    return 0;
  }
  
  public static int averager(int n)
  {
    return 0;
  }

  public static void main(String[] args)
  {

    System.out.println("0 = Average");
    System.out.println("1 = Middle 2 Digits");
    System.out.println("0 = Fibonacci");
    setting = keyboard.nextInt();
    
    if (setting == 0)
    {
      averager(1);
    }
    if (setting == 1)
    {
      middleDigits(1);
    }
    if (setting == 2)
    {
      fib(1);
    }

  }

}
