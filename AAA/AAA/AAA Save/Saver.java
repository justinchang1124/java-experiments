/**
 * This save manager can do the following:
 * 1) Save a SaveFile
 * 2) Load a SaveFile
 * 3) Delete a SaveFile
 * 4) Create a SaveFile if none exists
 * 5) Initialize data allocation
 * 6) Pass the necessary parameters to writeString
 * 
 * @author Justin C
 */
public class Saver
{
  private SaveFile gameSave;

  private int[] longL;

  private int[] doubleL;

  private int[] booleanL;

  private int[] stringL;

  // All long, double, boolean, and string values that are savables.
  public static long[][] sL;

  public static double[][] sD;

  public static boolean[][] sB;

  public static String[][] sS;

  /**
   * Allocates the data architecture for all savables.
   * 
   * @param longL
   * @param doubleL
   * @param booleanL
   * @param stringL
   */
  public Saver(int[] longL, int[] doubleL, int[] booleanL, int[] stringL)
  {
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

  /**
   * Passes writeString() to gameSave.
   */
  public void writeString()
  {
    gameSave.writeString();
  }

  /**
   * Deletes the save slot at the specified location.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   */
  public void deleteSaveFile(String root, String fileName, int saveSlot)
  {
    SaveCreator.deleteSaveFile(root, fileName, saveSlot);
  }

  /**
   * Saves all savables to the specified location.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   */
  public void save(String root, String fileName, int saveSlot)
  {
    for (int i = 0; i < longL.length; i++)
    {
      for (int j = 0; j < longL[i]; j++)
      {
        gameSave.setLong(i, j);
      }
    }
    for (int i = 0; i < doubleL.length; i++)
    {
      for (int j = 0; j < doubleL[i]; j++)
      {
        gameSave.setDouble(i, j);
      }
    }
    for (int i = 0; i < booleanL.length; i++)
    {
      for (int j = 0; j < booleanL[i]; j++)
      {
        gameSave.setBoolean(i, j);
      }
    }
    for (int i = 0; i < stringL.length; i++)
    {
      for (int j = 0; j < stringL[i]; j++)
      {
        gameSave.setString(i, j);
      }
    }
    
    SaveCreator.save(gameSave, root, fileName, saveSlot);
  }

  /**
   * Loads all savables from the specified location.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   */
  public void load(String root, String fileName, int saveSlot)
  {
    gameSave = SaveCreator.load(root, fileName, saveSlot);

    for (int i = 0; i < longL.length; i++)
    {
      for (int j = 0; j < longL[i]; j++)
      {
        sL[i][j] = gameSave.getLong(i, j);
      }
    }

    for (int i = 0; i < doubleL.length; i++)
    {
      for (int j = 0; j < doubleL[i]; j++)
      {
        sD[i][j] = gameSave.getDouble(i, j);
      }
    }

    for (int i = 0; i < booleanL.length; i++)
    {
      for (int j = 0; j < booleanL[i]; j++)
      {
        sB[i][j] = gameSave.getBoolean(i, j);
      }
    }

    for (int i = 0; i < stringL.length; i++)
    {
      for (int j = 0; j < stringL[i]; j++)
      {
        sS[i][j] = gameSave.getString(i, j);
      }
    }
  }

  /**
   * Loads all savables from the specified location and with flexibility.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   * @param binLoad
   * Decides which data types are loaded.
   */
  public void load(String root, String fileName, int saveSlot, int[] binLoad)
  {
    gameSave = SaveCreator.load(root, fileName, saveSlot);
    
    if (binLoad[0] == 1)
    {
      for (int i = 0; i < longL.length; i++)
      {
        for (int j = 0; j < longL[i]; j++)
        {
          sL[i][j] = gameSave.getLong(i, j);
        }
      }
    }
    if (binLoad[1] == 1)
    {
      for (int i = 0; i < doubleL.length; i++)
      {
        for (int j = 0; j < doubleL[i]; j++)
        {
          sD[i][j] = gameSave.getDouble(i, j);
        }
      }
    }
    if (binLoad[2] == 1)
    {
      for (int i = 0; i < booleanL.length; i++)
      {
        for (int j = 0; j < booleanL[i]; j++)
        {
          sB[i][j] = gameSave.getBoolean(i, j);
        }
      }
    }
    if (binLoad[3] == 1)
    {
      for (int i = 0; i < stringL.length; i++)
      {
        for (int j = 0; j < stringL[i]; j++)
        {
          sS[i][j] = gameSave.getString(i, j);
        }
      }
    }
  }

  /**
   * Creates an initialized file if none exists at the specified location. 
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   */
  public void doesThisExist(String root, String fileName, int saveSlot)
  {
    if (SaveCreator.load(root, fileName, saveSlot) == null)
    {
      gameSave = new SaveFile(longL, doubleL, booleanL, stringL);
      SaveCreator.save(gameSave, root, fileName, saveSlot);
    }
  }
}
