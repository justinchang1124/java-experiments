import java.util.ArrayList;
import java.util.Collections;

public class Team
{
  private int score;

  private String name;

  private int timeLastUpdated;

  public static int numberOfUpdates;

  public static ArrayList<Team> teams = new ArrayList<Team>();

  public Team(String s)
  {
    score = 0;
    name = s;
    timeLastUpdated = numberOfUpdates;
  }

  public static void sort()
  {
    Collections.sort(teams, new ScoreComparator());
  }

  public static void addScore(String teamName, int delta)
  {
    boolean updated = false;

    for (Team t : teams)
    {
      if (t.name.equals(teamName))
      {
        t.score += delta;
        numberOfUpdates++;
        t.timeLastUpdated = numberOfUpdates;
        updated = true;
      }
    }

    if (!updated)
    {
      System.out.println("Error! Name not found!");
    }
  }

  public static void setScore(String teamName, int score)
  {
    boolean updated = false;

    for (Team t : teams)
    {
      if (t.name.equals(teamName))
      {
        t.score = score;
        numberOfUpdates++;
        t.timeLastUpdated = numberOfUpdates;
        updated = true;
      }
    }

    if (!updated)
    {
      System.out.println("Error! Name not found!");
    }
  }

  public static void setTeams(String fileName, int teamNumber)
  {
    TxtReader txt = new TxtReader(fileName);

    String s = txt.readFile();

    for (int i = 0; i < teamNumber; i++)
    {
      int k = s.indexOf("TERMINATE");

      String fragment = s.substring(0, k);
      s = s.substring(k + "TERMINATE".length());
      teams.add(new Team(fragment));
    }

    for (int i = 0; i < teamNumber; i++)
    {
      int k = s.indexOf("TERMINATE");

      String fragment = s.substring(0, k);
      s = s.substring(k + "TERMINATE".length());
      Team.setScore(teams.get(i).getName(), Integer.parseInt(fragment));
    }
  }

  public int getScore()
  {
    return score;
  }

  public String getName()
  {
    return name;
  }

  public int getUpdate()
  {
    return timeLastUpdated;
  }
}
