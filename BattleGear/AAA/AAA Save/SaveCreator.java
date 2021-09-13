
/**
 * @author Justin Chang
 * 
 *         Attribution:
 *         Andy Solace from Youtube.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SaveCreator
{
  // Creates a serialization of the desired file.
  public static void save(Serializable objectToSerialize, String root, String fileName, int saveSlot)
  {
    FileOutputStream fos = null;

    try
    {
      // System.out.println(createDataFolder(root) + fileName);
      // Writes the file to the specified address.
      fos = new FileOutputStream(createDataFolder(root) + fileName + saveSlot + ".sav");
      ObjectOutputStream oos = new ObjectOutputStream(fos);

      oos.writeObject(objectToSerialize);
      oos.flush();
      oos.close();
      // System.out.println("Saved!");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static SaveFile load(String root, String fileName, int saveSlot)
  {
    FileInputStream fis = null;

    // If the file specified is actually a file.
    if (checkFileExists(root, fileName, saveSlot))
    {
      try
      {
        // System.out.println("LOAD:" + createDataFolder(root) + fileName);
        // Opens an input stream from the file given.
        fis = new FileInputStream(createDataFolder(root) + fileName + saveSlot + ".sav");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Creates an Object from the serialization and returns it.
        SaveFile loadedObject = (SaveFile) ois.readObject();
        ois.close();
        // System.out.println("Loaded!");
        return loadedObject;
      }
      catch (ClassNotFoundException | IOException e)
      {
        e.printStackTrace();
      }
    }

    return null;
  }

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
      // System.out.println("Creating directory");
      dir.mkdir();
    }

    // Returns the path to the folder.
    return dir.getAbsolutePath();
  }

  public static boolean checkFileExists(String root, String fileName, int saveSlot)
  {
    return new File(createDataFolder(root) + fileName + saveSlot + ".sav").isFile();
  }

  public static boolean deleteSaveFile(String root, String fileName, int saveSlot)
  {
    if (!checkFileExists(root, fileName, saveSlot))
    {
      System.out.println("File: " + createDataFolder(root) + fileName + " does not exist");

      return new File(createDataFolder(root)).delete();
    }

    File toDelete = new File(createDataFolder(root) + fileName);
    if (toDelete.canWrite())
    {
      return toDelete.delete();
    }

    System.out.println("File: " + createDataFolder(root) + fileName + " is write-protected.");
    return false;
  }
}
