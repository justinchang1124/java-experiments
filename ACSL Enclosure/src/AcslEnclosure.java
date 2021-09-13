/**
 * Justin Chang
 * Phillips Academy, Andover
 * Upper-1
 * 
 * 5e64efdc-eda2-4821-8260-2c0ab2d100f3
 */
public class AcslEnclosure
{
  /**
   * Returns the positions that a parenthesis, bracket, or brace can be placed in.
   * 
   * @param str
   */
  public static void getPositions(String str)
  {
    // Ensures that characters can be placed in the last spot.
    str += "_";

    // The open-close order of the expression.
    char[] bounders = {'{', '[', '(', ')', ']', '}'};

    // The indices of each bounding character.
    int[] locations = new int[6];

    // The index of the missing character in "bounders".
    int absent = 0;

    for (int i = 0; i < 6; i++)
    {
      locations[i] = str.indexOf(bounders[i]);
      if (locations[i] == -1 && locations[5-i] != -1)
        absent = i;
    }

    System.out.println("Missing Character: " + bounders[absent]);

    for (int i = 0; i < str.length(); i++)
    {
      // Determines if the given location violates the order of parentheses, brackets, braces
      boolean braceBrack = isBetween(i, locations[5 - absent], locations[1], locations[4]);
      boolean braceParen = isBetween(i, locations[5 - absent], locations[2], locations[3]);
      boolean brackBrace = isBetween(i, locations[5 - absent], locations[0], locations[5]);
      boolean brackParen = isBetween(i, locations[5 - absent], locations[2], locations[3]);
      boolean parenBrack = isBetween(i, locations[5 - absent], locations[1], locations[4]);
      boolean parenBrace = isBetween(i, locations[5 - absent], locations[0], locations[5]);

      if (( ( absent == 0 || absent == 5 ) && braceBrack && braceParen ) || 
          ( ( absent == 1 || absent == 4 ) && brackBrace && brackParen ) || 
          ( ( absent == 2 || absent == 3 ) && parenBrace && parenBrack ))
      {
        // Considers the characters before, at, and after "i"
        char sub1 = '0';
        char sup1 = '0';
        char same = '0';

        if (i > 0)
          sub1 = str.charAt(i - 1);

        if (i < str.length() - 1)
          sup1 = str.charAt(i + 1);

        same = str.charAt(i);

        // if this substring doesn't enclose a single integer
        if (str.substring(Math.min(i, locations[5 - absent]), Math.max(i, locations[5 - absent])).indexOf('+') != -1)
        {
          // if the opening character satisfies the conditions
          if (absent <= 2 && i <= locations[5 - absent] && sub1 != '1' && same != '+' && sup1 != ')' && sup1 != ']' && sup1 != '}')
            System.out.print(( i + 1 ) + " ");

          // if the closing character satisfies the conditions
          if (absent >= 3 && i > locations[5 - absent] && sub1 != '{' && sub1 != '[' && sub1 != '(' && sub1 != '+' && same != '1')
            System.out.print(( i + 1 ) + " ");
        }
      }
    }
    System.out.println("\n");
  }

  /**
   * Ensures that t1 and t2 are both included or excluded from (a, b].
   * 
   * @param t1
   * @param t2
   * @param a
   * @param b
   * @return
   */
  public static boolean isBetween(int t1, int t2, int a, int b)
  {
    if (( t1 > a && t1 <= b && t2 > a && t2 <= b ) || ( ( t1 <= a || t1 > b ) && ( t2 <= a || t2 > b ) ))
      return true;
    return false;
  }

  /**
   * Converts an expression into a more parsable form.
   * 
   * @param str
   * @return
   * {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} goes to {1}
   * {+, -, *, /} goes to {+}
   */
  public static String parseExpression(String str)
  {
    String target = "";

    for (int i = 0; i < str.length(); i++)
    {
      char c = str.charAt(i);

      if (Character.isDigit(c))
        target += "1";
      if (c == '+' || c == '-' || c == '*' || c == '/')
        target += "+";
      if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}')
        target += c;
    }

    return target;
  }

  /**
   * Runs the program for all arguments given.
   * 
   * @param args
   */
  public static void main(String[] args)
  {
    for (int i = 0; i < args.length; i++)
    {
      System.out.println(args[i] + " => " + parseExpression(args[i]));
      getPositions(parseExpression(args[i]));
    }
  }
}
