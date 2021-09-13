import java.io.Serializable;

public class TestObject implements Serializable
{
  private int number = 1;
  
  public String toString()
  {
    StringBuilder out = new StringBuilder();
    out.append("" + number);
    return out.toString();
  }
}
