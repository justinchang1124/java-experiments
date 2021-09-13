
/*
 * Attribution:
 * Andy Solace from Youtube.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A collection of public static methods that allow for save file management.
 * 
 * @author Justin C
 */
public class SaveCreator
{
  /**
   * Creates a serialization of the desired file with the given parameters.
   * 
   * @param objectToSerialize
   * @param root
   * Generally of the form "Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   */
  public static void save(Serializable objectToSerialize, String root, String fileName, int saveSlot)
  {
    FileOutputStream fos = null;
    String address = createDataFolder(root) + fileName + saveSlot + ".sav";

    try
    {
      // Writes the file to the specified address.
      fos = new FileOutputStream(address);
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      oos.writeObject(objectToSerialize);
      oos.flush();
      oos.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Loads the serialization of the save file at the specified location
   * if it can be found.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   * @return
   */
  public static SaveFile load(String root, String fileName, int saveSlot)
  {
    FileInputStream fis = null;
    String address = createDataFolder(root) + fileName + saveSlot + ".sav";

    // If the file specified is actually a file.
    if (checkFileExists(root, fileName, saveSlot))
    {
      try
      {
        // Opens an input stream from the address given.
        fis = new FileInputStream(address);
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Creates an Object from the serialization and returns it.
        SaveFile loadedObject = (SaveFile) ois.readObject();
        ois.close();
        return loadedObject;
      }
      catch (ClassNotFoundException | IOException e)
      {
        e.printStackTrace();
      }
    }

    return null;
  }

  /**
   * Creates a folder with the name "root" on the default location.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @return
   */
  public static String createDataFolder(String root)
  {
    // Starts with the home address of the system.
    String home = System.getProperty("user.home");
    String os = System.getProperty("os.name").toLowerCase();

    // Places the folder in the desired directory.
    if (os.contains("win"))
    {
      home += "/Desktop";
    }
    if (os.contains("mac"))
    {
      home += "~/Libary/Application Support";
    }
    if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
    {
      home += "~/.";
    }

    File dir = new File(home);
    dir = new File(dir, root);

    // Creates the file if it does not exist.
    if (!dir.exists())
    {
      dir.mkdir();
    }

    return dir.getAbsolutePath();
  }

  /**
   * Checks if a file with the given parameters exists.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   * @return
   */
  public static boolean checkFileExists(String root, String fileName, int saveSlot)
  {
    String address = createDataFolder(root) + fileName + saveSlot + ".sav";
    return new File(address).isFile();
  }

  /**
   * Deletes any save file at the specified location.
   * 
   * @param root
   * Generally of the form "/Saves", as it is the folder to be created.
   * @param fileName
   * Generally of the form "/GameSave" or "/PalfSave", as it is the file itself.
   * @param saveSlot
   * Generally goes 1, 2, 3, etc.
   * @return
   */
  public static boolean deleteSaveFile(String root, String fileName, int saveSlot)
  {
    String address = createDataFolder(root) + fileName + saveSlot + ".sav";
    
    if (!checkFileExists(root, fileName, saveSlot))
    {
      System.out.println("File: " + address + " does not exist");
      return new File(createDataFolder(root)).delete();
    }

    File toDelete = new File(address);
    if (toDelete.canWrite())
    {
      return toDelete.delete();
    }

    System.out.println("File: " + address + " is write-protected.");
    return false;
  }
}
