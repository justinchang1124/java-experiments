
public class Lily extends Violet
{
  public void verse1()
  {
    super.verse1();
    System.out.print("Lily 1 ");
  }
  
  public void verse2()
  {
    System.out.print("Lily 2 ");
    verse1();
  }
  
  public String toString()
  {
    return "Lily";
  }
}
