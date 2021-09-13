
import java.util.Scanner;

public class RoundingDouble
{
  public static void main(String[] args)
  {

    Scanner keyboard = new Scanner(System.in);
    double input;

    while (true)
    {
      
      System.out.println("Enter your number:");

      input = keyboard.nextDouble();

      if (input > 0)
      {
        if ((input * 10) % 10 > 5)
          System.out.println((int) input + 1);
        else
          System.out.println((int) input);

      }
      
      else
      {
        if (((input * 10) % 10) + 10 > 5)
          System.out.println((int) input);
        else
          System.out.println((int) input-1);

      }

    }
    
  }
}
