
/**
 * Attribution:
 * 
 * All of this class is attributed to Andy Solace from Youtube.
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
  public static void save(Serializable objectToSerialize, String root, String fileName)
  {
    FileOutputStream fos = null;
    
    try
    {
      // System.out.println(createDataFolder(root) + fileName);
      fos = new FileOutputStream(createDataFolder(root) + fileName);
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

  public static SaveFile load(String root, String fileName)
  {
    FileInputStream fis = null;
    
    if (new File(createDataFolder(root) + fileName).isFile())
    {
      try
      {
        // System.out.println("LOAD:" + createDataFolder(root) + fileName);
        fis = new FileInputStream(createDataFolder(root) + fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

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
    String home = System.getProperty("user.home");
    String os = System.getProperty("os.name").toLowerCase();
    
    if (os.contains("win"))
      home += "/Desktop";
    if (os.contains("mac"))
      home += "~/Libary/Application Support";
    if (os.contains("nix") || os.contains("nux") || os.contains("aix"))
      home += "~/.";

    File dir = new File(home);
    dir = new File(dir, root);

    if (!dir.exists())
    {
      // System.out.println("Creating directory");
      dir.mkdir();
    }

    return dir.getAbsolutePath();
  }
}
