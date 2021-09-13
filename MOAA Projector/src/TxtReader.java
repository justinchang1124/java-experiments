/**
 * Reads .txt files in the same manner as DocXReader reads .docx files.
 * 
 * @author Justin C
 */
public class TxtReader implements Reader
{
  /**
   * The file name of the .txt file to be read. In Eclipse, this ought to be
   * along the lines of "EXAMPLE.txt" and placed directly in the project folder
   * (outside of src, same level as src).
   */
  private String filename;

  /**
   * Constructs a TxtReader assigned to the .txt file with the given "filename".
   */
  public TxtReader(String filename)
  {
    this.filename = filename;
  }

  /**
   * Reads the .txt file at "filename".
   * Uses the EasyReader class, as instructed.
   */
  public String readFile()
  {
    EasyReader lineCounter = new EasyReader(filename);
    EasyReader inputReader = new EasyReader(filename);

    // Ensures that the readers open properly.
    if (lineCounter.bad() || inputReader.bad())
    {
      System.err.println("*** Cannot open " + filename + " ***");
      System.exit(1);
    }

    // Counts how many lines are in the input file.
    int lineCount = 0;
    while (!lineCounter.eof())
    {
      lineCounter.readLine();
      lineCount++;
    }
    lineCounter.close();

    // The string of input to be returned.
    String str = "";

    for (int i = 0; i < lineCount; i++)
    {
      String s = inputReader.readLine();

      // For readability, every line of text is separated by two "\n"
      // characters.
      if (i != 0)
        str += "TERMINATE";

      if (s != null)
        str += s;
    }

    return str;
  }
}
