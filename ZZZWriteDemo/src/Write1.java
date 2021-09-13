import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Write1
{

  public static void writeFile(String canonicalFilename, String text) throws IOException
  {
    
    File file = new File(canonicalFilename);
    BufferedWriter out = new BufferedWriter(new FileWriter(file));
    out.write(text);
    out.close();
  }
  
  public static void main(String[] args) throws IOException
  {

      writeFile("C:\\Code\\Eclipse Workspace JC\\Write Demo\\Resources\\Hello!.file", "hi");
    
  }
}