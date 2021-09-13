
/**
 * @author Justin Chang
 * 
 *         This is a save file with the ability to store any number of
 *         longs, doubles, booleans, and Strings, which can also be grouped into arrays.
 */

import java.io.Serializable;

/**
 * SaveFile is a serializable class that preserves four distinct arrays of 
 * long, double, boolean, and String type.
 * 
 * @author Justin C
 */
public class SaveFile implements Serializable
{
  private int[] longL;

  private int[] doubleL;

  private int[] booleanL;

  private int[] stringL;

  private long[][] savedLong;

  private double[][] savedDouble;

  private boolean[][] savedBoolean;

  private String[][] savedString;

  /**
   * Creates a SaveFile with the given data allocation.
   * 
   * @param longL
   * @param doubleL
   * @param booleanL
   * @param stringL
   */
  public SaveFile(int[] longL, int[] doubleL, int[] booleanL, int[] stringL)
  {
    this.longL = new int[longL.length];
    this.doubleL = new int[doubleL.length];
    this.booleanL = new int[booleanL.length];
    this.stringL = new int[stringL.length];
    savedLong = new long[longL.length][];
    savedDouble = new double[doubleL.length][];
    savedBoolean = new boolean[booleanL.length][];
    savedString = new String[stringL.length][];

    for (int i = 0; i < longL.length; i++)
    {
      this.longL[i] = longL[i];
      savedLong[i] = new long[longL[i]];
    }

    for (int i = 0; i < doubleL.length; i++)
    {
      this.doubleL[i] = doubleL[i];
      savedDouble[i] = new double[doubleL[i]];
    }

    for (int i = 0; i < booleanL.length; i++)
    {
      this.booleanL[i] = booleanL[i];
      savedBoolean[i] = new boolean[booleanL[i]];
    }

    for (int i = 0; i < stringL.length; i++)
    {
      this.stringL[i] = stringL[i];
      savedString[i] = new String[stringL[i]];
    }
  }

  /**
   * Displays the contents of the save in four rows, 
   * with each array broken up by 3 spaces and each entry by 1 space.
   */
  public void writeString()
  {
    for (int i = 0; i < savedLong.length; i++)
    {
      for (int j = 0; j < savedLong[i].length; j++)
      {
        System.out.print(savedLong[i][j] + " ");
      }
      System.out.print("  ");
    }
    System.out.println();
    for (int i = 0; i < savedDouble.length; i++)
    {
      for (int j = 0; j < savedDouble[i].length; j++)
      {
        System.out.print(savedDouble[i][j] + " ");
      }
      System.out.print("  ");
    }
    System.out.println();
    for (int i = 0; i < savedBoolean.length; i++)
    {
      for (int j = 0; j < savedBoolean[i].length; j++)
      {
        if (savedBoolean[i][j])
          System.out.print("T ");
        else
          System.out.print("F ");
      }
      System.out.print("  ");
    }
    System.out.println("\n");
    for (int i = 0; i < savedString.length; i++)
    {
      for (int j = 0; j < savedString[i].length; j++)
      {
        System.out.print(savedString[i][j] + " ");
      }
      System.out.print("  ");
    }
    System.out.println("\n");
  }

  public long getLong(int i, int j)
  {
    return savedLong[i][j];
  }

  public double getDouble(int i, int j)
  {
    return savedDouble[i][j];
  }

  public boolean getBoolean(int i, int j)
  {
    return savedBoolean[i][j];
  }

  public String getString(int i, int j)
  {
    return savedString[i][j];
  }

  public void setLong(int i, int j)
  {
    savedLong[i][j] = Saver.sL[i][j];
  }

  public void setDouble(int i, int j)
  {
    savedDouble[i][j] = Saver.sD[i][j];
  }

  public void setBoolean(int i, int j)
  {
    savedBoolean[i][j] = Saver.sB[i][j];
  }

  public void setString(int i, int j)
  {
    savedString[i][j] = Saver.sS[i][j];
  }
}
