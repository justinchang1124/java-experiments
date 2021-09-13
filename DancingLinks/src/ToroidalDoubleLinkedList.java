import java.util.ArrayList;

public class ToroidalDoubleLinkedList
{
  public ArrayList<ArrayList<DoubleLinkedNode>> data = new ArrayList<ArrayList<DoubleLinkedNode>>();

  public ToroidalDoubleLinkedList(int[][] input)
  {
    // Create the horizontal set of headers.
    for (int j = 0; j < input[0].length; j++)
      data.add(new ArrayList<DoubleLinkedNode>());
    
    for (int i = 0; i < input.length; i++)
    {
      DoubleLinkedNode l = null;
      DoubleLinkedNode r = null;

      for (int j = 0; j < input[0].length; j++)
        if (input[i][j] != 0)
        {
          DoubleLinkedNode newR = new DoubleLinkedNode();

          if (r != null)
          {
            r.R = newR;
            newR.L = r;
          }
          
          r = newR;
          data.get(j).add(newR);
          
          if (l == null)
            l = newR;
        }

      if (l != null)
      {
        r.R = l;
        l.L = r;
      }
    }

    for (int j = 0; j < data.size(); j++)
      for (int i = 0; i < data.get(j).size(); i++)
      {
        int n = data.get(j).size();

        data.get(j).get(i).U = data.get(j).get((i + n - 1) % n);
        data.get(j).get(i).D = data.get(j).get((i + 1) % n);
      }
  }

  public int getMinOnes()
  {
    int minSize = Integer.MAX_VALUE;
    int index = -1;

    for (int j = 0; j < data.size(); j++)
      if (data.get(j).size() < minSize)
      {
        minSize = data.get(j).size();
        index = j;
      }

    return index;
  }
  
  public 

  public String toString()
  {
    String s = "";

    for (int i = 0; i < data.size(); i++)
      for (int j = 0; j < data.get(i).size(); j++)
      {
        DoubleLinkedNode n = data.get(i).get(j);

        s += n + "[L:" + n.L + ",R:" + n.R + ",U:" + n.U + ",D:" + n.D + "]";

        if (i != data.size() - 1 || j != data.get(i).size() - 1)
          s += "\n";
      }

    return s;
  }
}
