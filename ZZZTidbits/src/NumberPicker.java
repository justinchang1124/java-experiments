import java.util.Scanner;

public class NumberPicker
{
  public static long getYear()
  {
    return Math.round(Math.random() * 40 + 1975);
  }
  
  public static void askUser(long year, long hintYear)
  {
    Scanner kboard = new Scanner(System.in);
    System.out.println("Here's a year: " + year + "\n" + "True or false: Java was around in that year. ");
    System.out.println("(Hint: Was it around in " + hintYear + "? " + (hintYear > 1994) + ")");
    String input = kboard.nextLine();
    System.out.println("You said: " + input + "\n" + "Answer: " + (year > 1994) + "\n");
  }

  public static void main(String[] args)
  {
    long year, hintYear;
    year = getYear();
    hintYear = getYear();
    System.out.println("Let's generate some random years!");
    askUser(year, hintYear);
    System.out.println("That was fun, how about another?" + "\n");
    year = getYear();
    hintYear = getYear();
    askUser(year, hintYear);
  }
}
