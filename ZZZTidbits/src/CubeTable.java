public class CubeTable 
{
	public static void main(String args [])
	{
		int a, b, c;
		a = 12;
		b = 12;
		c = 12;
		
		int Cube[][][] = new int[a][b][c];
		int i, j, k;
		
		for(i=0; i<a; i++)
			for(j=0; j<b; j++)
				for(k=0; k<c; k++)
					Cube[i][j][k] = (i+1) * (j+1) * (k+1);
				
		for(i=0; i<a; i++) 
		{
			for(j=0; j<b; j++) 
			{
				for(k=0; k<c; k++)
					System.out.print(Cube[i][j][k] + " ");
				System.out.println();
			}
			System.out.println();	
		}	
	}
}