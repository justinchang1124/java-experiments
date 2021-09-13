/**
 * Justin Chang
 * Phillips Academy Andover
 * Senior-3
 * 3ed9b554-cde5-461f-bc18-65a45980efb3
 */

//F123AB CABFAB 897654 ABEACE 048ACE 159BDF 04C06E, ABCDEF
//8 8 L 4
//2 7 R 5
//6 3 B 10
//4 4 L 15
//1 1 A 8

//4F9D39, DEB456, 3DA8B9, A57CA7, 26A84A, 2FCFA3, 3AAB09, 89CBF5
//1, 2, L, 2
//5, 3, A, 4
//3, 5, B, 2
//6, 7, R, 5
//4, 7, L, 6

import java.util.Arrays;
import java.util.Scanner;

public class ACSLWalk
{
  public static final int iterations = 5;

  public static int[][] arrayWalk = new int[8][8];

  public static int[] initialX = new int[iterations];

  public static int[] initialY = new int[iterations];

  public static String[] directions = new String[iterations];

  public static int[] moveNum = new int[iterations];

  /**
   * Direction is oriented like so: 
   *             x
   *             x
   *           3 2 1
   * yyyyyyyyy 4 C 0 yyyyyyyyy
   *           5 6 7
   *             x
   *             x
   */
  public static void playMoves(int number)
  {
    System.out.println("Walk #" + ( number + 1 ) + ":");

    int direction = 0;
    int x = initialX[number];
    int y = initialY[number];

    if (directions[number].equals("R"))
      direction = 0;
    if (directions[number].equals("A"))
      direction = 2;
    if (directions[number].equals("L"))
      direction = 4;
    if (directions[number].equals("B"))
      direction = 6;

    for (int i = 0; i < moveNum[number]; i++)
    {
      // Turning clockwise in a counter-clockwise oriented system
      direction -= arrayWalk[x - 1][y - 1];

      if (direction < 0)
        direction += 8;
      if (direction > 7)
        direction -= 8;

      System.out.print("   Move #" + ( i + 1 ) + ": Moving from: (" + x + ", " + y + ") to ");

      if (direction == 7 || direction == 0 || direction == 1)
        y++;

      if (direction == 3 || direction == 4 || direction == 5)
        y--;

      if (direction == 1 || direction == 2 || direction == 3)
        x++;
      
      if (direction == 5 || direction == 6 || direction == 7)
        x--;

      System.out.println("(" + x + ", " + y + ")");
      direction += 4;
    }

    System.out.println("   Final Location: (" + x + ", " + y + ")");
  }

  /**
   * Main method.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    @SuppressWarnings("resource") Scanner kboard = new Scanner(System.in);
    String input = "";

    filterChars(args);

    System.out.println("Are these the correct inputs?: (y/n) \n" + Arrays.toString(args));

    input = kboard.nextLine();

    if (!input.equals("y"))
    {
      System.out.println("Exiting system.");
      System.exit(1);
    }

    String[] hexa = new String[8];

    for (int i = 0; i < 8; i++)
    {
      hexa[i] = args[i];
    }

    System.out.println("Converting hexadecimal arguments to decimal ...");

    long[] convertedHexa = convertHexaToTens(hexa);
    System.out.println(Arrays.toString(hexa));

    System.out.println("Converting decimal arguments to octal grid ...");

    convertTensToOctal(convertedHexa);

    for (int i = 0; i < arrayWalk.length; i++)
    {
      for (int j = 0; j < arrayWalk[i].length; j++)
      {
        System.out.print(arrayWalk[7 - i][j] + " ");
      }
      System.out.println();
    }

    System.out.println("Is the above board oriented correctly? (y/n)");

    input = kboard.nextLine();

    if (!input.equals("y"))
    {
      System.out.println("Exiting system.");
      System.exit(1);
    }

    for (int i = 0; i < iterations; i++)
    {
      initialX[i] = Integer.parseInt(args[Math.min(args.length - 1, 8 + 0 + 4 * i)]);
      initialY[i] = Integer.parseInt(args[Math.min(args.length - 1, 8 + 1 + 4 * i)]);
      directions[i] = args[Math.min(args.length - 1, 8 + 2 + 4 * i)];
      moveNum[i] = Integer.parseInt(args[Math.min(args.length - 1, 8 + 3 + 4 * i)]);
    }

    System.out.println("Initial X Coordinates: " + Arrays.toString(initialX));
    System.out.println("Initial Y Coordinates: " + Arrays.toString(initialY));
    System.out.println("Directions of Initial Entry: " + Arrays.toString(directions));
    System.out.println("Number of Moves: " + Arrays.toString(moveNum));

    System.out.println("Is the above data correct? (y/n)");

    input = kboard.nextLine();

    if (!input.equals("y"))
    {
      System.out.println("Exiting system.");
      System.exit(1);
    }

    System.out.println("Initiating walks: ");

    for (int i = 0; i < iterations; i++)
    {
      playMoves(i);
    }
  }

  /**
   * Removes anything that isn't a letter or a digit from the arguments.
   * 
   * @param args
   */
  public static void filterChars(String[] args)
  {
    for (int i = 0; i < args.length; i++)
    {
      for (int j = 0; j < args[i].length(); j++)
      {
        char c = args[i].charAt(j);

        if (!Character.isLetterOrDigit(c))
        {
          if (j == args[i].length() - 1)
          {
            args[i] = args[i].substring(0, j);
          }
          else
          {
            args[i] = args[i].substring(0, j) + args[i].substring(j + 1);
          }

          j--;
        }
      }
    }
  }

  /**
   * Converts strings of hexadecimal numbers into decimal numbers.
   * 
   * @param hexa
   * @return
   */
  public static long[] convertHexaToTens(String[] hexa)
  {
    long[] target = new long[hexa.length];

    for (int i = 0; i < hexa.length; i++)
    {
      for (int j = 0; j < hexa[i].length(); j++)
      {
        int value = 0;

        if (Character.isDigit(hexa[i].charAt(j)))
          value = Integer.parseInt("" + hexa[i].charAt(j));
        if (hexa[i].charAt(j) == 'A')
          value = 10;
        if (hexa[i].charAt(j) == 'B')
          value = 11;
        if (hexa[i].charAt(j) == 'C')
          value = 12;
        if (hexa[i].charAt(j) == 'D')
          value = 13;
        if (hexa[i].charAt(j) == 'E')
          value = 14;
        if (hexa[i].charAt(j) == 'F')
          value = 15;
        target[i] += (int) Math.pow(16, hexa[i].length() - j - 1) * value;
      }
    }

    return target;
  }

  /**
   * Converts a series of decimal numbers into arrayWalk's coordinates.
   * 
   * @param tens
   */
  public static void convertTensToOctal(long[] tens)
  {
    for (int i = 0; i < Math.min(8, tens.length); i++)
    {
      for (int j = 0; j < Math.min(8, tens.length); j++)
      {
        arrayWalk[i][j] = Math.min(7, (int) tens[i] / (int) Math.pow(8, 7 - j));
        tens[i] %= (int) Math.pow(8, 7 - j);
      }
    }
  }
}