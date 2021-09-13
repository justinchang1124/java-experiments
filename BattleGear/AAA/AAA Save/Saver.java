/**
 * 
 * @author Justin C
 * 
 *         This is a class that can load and save SaveFiles.
 *         Additionally, it initializes saves, creates folders, and ticks save values.
 *         Also, it passes the writeString method.
 *
 */

public class Saver
{
  private SaveFile gameSave;

  private String folderName;

  private String saveName;

  private int[] longL;

  private int[] doubleL;

  private int[] booleanL;

  private int[] stringL;

  // All long, double, boolean, and string values that are savables.
  public static long[][] sL;

  public static double[][] sD;

  public static boolean[][] sB;

  public static String[][] sS;

  public Saver(String folderName, String saveName, int[] longL, int[] doubleL, int[] booleanL, int[] stringL)
  {
    this.folderName = folderName;
    this.saveName = saveName;
    
    this.longL = new int[longL.length];
    this.doubleL = new int[doubleL.length];
    this.booleanL = new int[booleanL.length];
    this.stringL = new int[stringL.length];
    
    for (int i = 0; i < longL.length; i++)
    {
      this.longL[i] = longL[i];
    }

    for (int i = 0; i < doubleL.length; i++)
    {
      this.doubleL[i] = doubleL[i];
    }

    for (int i = 0; i < booleanL.length; i++)
    {
      this.booleanL[i] = booleanL[i];
    }

    for (int i = 0; i < stringL.length; i++)
    {
      this.stringL[i] = stringL[i];
    }
    
    sL = new long[longL.length][];
    sD = new double[doubleL.length][];
    sB = new boolean[booleanL.length][];
    sS = new String[stringL.length][];
    
    for (int i = 0; i < longL.length; i++)
    {
      sL[i] = new long[longL[i]];
    }

    for (int i = 0; i < doubleL.length; i++)
    {
      sD[i] = new double[doubleL[i]];
    }

    for (int i = 0; i < booleanL.length; i++)
    {
      sB[i] = new boolean[booleanL[i]];
    }

    for (int i = 0; i < stringL.length; i++)
    {
      sS[i] = new String[stringL[i]];
    }
  }
  
  // Sets all saved values to their ss every tick.
  public void tick()
  {
    gameSave.setLongs();
    gameSave.setDoubles();
    gameSave.setBooleans();
    gameSave.setStrings();
  }
  
  public void writeString()
  {
    gameSave.writeString();
  }

  public void deleteSaveFile(int saveSlot)
  {
    SaveCreator.deleteSaveFile(folderName, saveName, saveSlot);
  }
  
  public void save(int saveSlot)
  {
    SaveCreator.save(gameSave, folderName, saveName, saveSlot);
    // gameSave.writeString();
    // System.out.println("saved");
  }

  public void load(int saveSlot)
  {
    gameSave = SaveCreator.load(folderName, saveName, saveSlot);
    for (int i = 0; i < longL.length; i++)
    {
      for (int j = 0; j < longL[i]; j++)
      {
        sL[i][j] = gameSave.getLongs(i, j);
      }
    }
    for (int i = 0; i < doubleL.length; i++)
    {
      for (int j = 0; j < doubleL[i]; j++)
      {
        sD[i][j] = gameSave.getDoubles(i, j);
      }
    }
    for (int i = 0; i < booleanL.length; i++)
    {
      for (int j = 0; j < booleanL[i]; j++)
      {
        sB[i][j] = gameSave.getBooleans(i, j);
      }
    }
    for (int i = 0; i < stringL.length; i++)
    {
      for (int j = 0; j < stringL[i]; j++)
      {
        sS[i][j] = gameSave.getStrings(i, j);
      }
    }
    // System.out.println("Loaded!");
  }

  public void doesThisExist(int saveSlot)
  {
    // Creates a file if none exists.
    if (SaveCreator.load(folderName, saveName, saveSlot) == null)
    {
      gameSave = new SaveFile(longL, doubleL, booleanL, stringL);
      SaveCreator.save(gameSave, folderName, saveName, saveSlot);
      // System.out.println("Created!");
    }
  }
}
