import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestMain
{

  public static void main(String[] args)
  {
    TestObject testObject = new TestObject();
    System.out.println(testObject.toString());
    save(testObject, "TestOutput.sav");
    load("TestOutput.sav");
  }

  /**
   * Attribution:
   * Andy Solace from Youtube
   */
  public static void save(Serializable objectToSerialize, String fileName)
  {
    FileOutputStream fos = null;
    try
    {
      fos = new FileOutputStream(fileName);
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
  
  public static void load(String fileName)
  {
    if(checkFileExists(fileName))
    {
      FileInputStream fis = null;
      
      try
      {
        fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        TestObject loadedObject = (TestObject) ois.readObject();
        ois.close();
        System.out.println("load");
        System.out.println(loadedObject.toString());
      }
      catch (ClassNotFoundException | IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static boolean checkFileExists(String fileName)
  {
    return new File(fileName).isFile();
  }

}
