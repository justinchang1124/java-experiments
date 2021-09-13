import java.util.Comparator;

public class TimeComparator implements Comparator<Team>
{

  @Override
  public int compare(Team o1, Team o2)
  {
    int difference = (int) (o1.getUpdate() - o2.getUpdate());

    if (difference == 0)
      return (new NameComparator()).compare(o1, o2);
    return difference;
  }

}
