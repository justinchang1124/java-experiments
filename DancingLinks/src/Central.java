
public class Central
{
  public static void main(String[] args)
  {
    int[][] n = {{1, 0, 1}, {1,1,1}, {1,0,1}};
    
    ToroidalDoubleLinkedList tdll = new ToroidalDoubleLinkedList(n);
    
    System.out.println(tdll);
    
    System.out.println(tdll.getMinOnes());
  }
}
