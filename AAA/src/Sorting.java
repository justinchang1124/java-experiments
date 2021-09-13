/**
 * A set of static methods for sorting purposes.
 * 
 * MOSTLY REDUNDANT
 * 
 * @author Justin C
 */
public class Sorting
{
  /**
   * Returns the minimum entry of an array.
   * 
   * @param group
   * @return
   */
  public static double getMin(double[] group)
  {
    double min = 0;

    for (int i = 0; i < group.length; i++)
    {
      min = Math.min(group[i], min);
    }

    return min;
  }

  /**
   * Returns the maximum entry of an array.
   * 
   * @param group
   * @return
   */
  public static double getMax(double[] group)
  {
    double max = 0;

    for (int i = 0; i < group.length; i++)
    {
      max = Math.max(group[i], max);
    }

    return max;
  }

  /**
   * Bubble sorts a given array of integers.
   * 
   * @param pc
   * @return
   */
  public static int[] bubbleInt(int[] pc)
  {
    for (int j = 0; j < pc.length - 1; j++)
    {
      for (int i = 0; i < pc.length - 1; i++)
      {
        if (pc[i] > pc[i + 1])
        {
          int temp = pc[i];
          pc[i] = pc[i + 1];
          pc[i + 1] = temp;
        }
      }
    }

    return pc;
  }

  /**
   * Bubble sorts a given array of doubles.
   * 
   * @param pc
   * @return
   */
  public static double[] bubbleDouble(double[] pc)
  {
    for (int j = 0; j < pc.length - 1; j++)
    {
      for (int i = 0; i < pc.length - 1; i++)
      {
        if (pc[i] > pc[i + 1])
        {
          double temp = pc[i];
          pc[i] = pc[i + 1];
          pc[i + 1] = temp;
        }
      }
    }

    return pc;
  }

  /**
   * Returns var if it is between min and max, or the bounds otherwise.
   * 
   * @param var
   * @param min
   * @param max
   * @return
   */
  public static double cap(double var, double min, double max)
  {
    if (var > max)
    {
      return max;
    }
    if (var < min)
    {
      return min;
    }
    else
    {
      return var;
    }
  }
}