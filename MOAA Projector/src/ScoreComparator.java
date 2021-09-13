import java.util.Comparator;

public class ScoreComparator implements Comparator<Team>
{
  @Override
  public int compare(Team o1, Team o2)
  {
    int difference = o1.getScore() - o2.getScore();
    
    if (difference == 0)
      return (new TimeComparator()).compare(o1, o2);
    
    return -difference;
  }
}
