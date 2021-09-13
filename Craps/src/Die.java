
public class Die
{
  private int numDots;
  
  public void roll()
  {
    numDots = (int)(6*Math.random()) + 1;
  }
  
  public int getNumDots()
  {
    return numDots;
  }
}
