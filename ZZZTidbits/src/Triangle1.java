import java.util.Scanner;

public class Triangle1
{

  public static void main(String args[])
  {

    Scanner kboard = new Scanner(System.in);

    boolean p, q, r;
    int a, b, c;

    System.out.println("Enter your three side lengths in ascending order.");
    try
    {
      a = kboard.nextInt();
      b = kboard.nextInt();
      c = kboard.nextInt();
    }
    finally
    {
      kboard.close();
    }

    if (a + b > c)
    {

      if (a * a + b * b > c * c)
      {

        p = true;
        q = false;
        r = false;

        System.out.println("Let's test if the given values form an acute, right, or obtuse triangle.");

        if (p)
          System.out.println("The triangle is acute.");
        if (q)
          System.out.println("The triangle is right.");
        if (r)
          System.out.println("The triangle is obtuse.");

        System.out.println("The idea that the triangle is acute is " + p);
        System.out.println("The idea that the triangle is right is " + q);
        System.out.println("The idea that the triangle is obtuse is " + r);

      }

      if (a * a + b * b == c * c)
      {

        p = false;
        q = true;
        r = false;

        System.out.println("Let's test if the given values form an acute, right, or obtuse triangle.");

        if (p)
          System.out.println("The triangle is acute.");
        if (q)
          System.out.println("The triangle is right.");
        if (r)
          System.out.println("The triangle is obtuse.");

        System.out.println("The idea that the triangle is acute is " + p);
        System.out.println("The idea that the triangle is right is " + q);
        System.out.println("The idea that the triangle is obtuse is " + r);

      }

      if (a * a + b * b < c * c)
      {

        p = false;
        q = false;
        r = true;

        System.out.println("Let's test if the given values form an acute, right, or obtuse triangle.");

        if (p)
          System.out.println("The triangle is acute.");
        if (q)
          System.out.println("The triangle is right.");
        if (r)
          System.out.println("The triangle is obtuse.");

        System.out.println("The idea that the triangle is acute is " + p);
        System.out.println("The idea that the triangle is right is " + q);
        System.out.println("The idea that the triangle is obtuse is " + r);

      }

    }

    else
    {
      System.out.println("Let's test if the given values form an acute, right, or obtuse triangle.");
      System.out.println("A triangle cannot be formed with these sides.");
    }

  }
}