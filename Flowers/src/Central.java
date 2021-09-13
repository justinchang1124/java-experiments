
public class Central
{
  public static void main(String[] args)
  {
    Violet[] pretty = {new Tulip(), new Lily(), new Violet(), new Rose()};

    for (int i = 0; i < pretty.length; i++)
    {
      System.out.println(pretty[i]);
      pretty[i].verse1();
      System.out.println();
      pretty[i].verse2();
      System.out.println();
      System.out.println();
    }
  }
}
