/**
 * Creates a [3x3] matrix and performs operations with them.
 * 
 * @author Justin C
 */
public class TripleMatrix
{
  double[] values = new double[9];

  /**
   * Constructs a matrix.
   * 
   * @param values
   */
  public TripleMatrix(double[] values)
  {
    for (int i = 0; i < 9; i++)
    {
      this.values[i] = values[i];
    }
  }

  /**
   * Multiplies this matrix with another matrix.
   * 
   * @param other
   * @return
   */
  public TripleMatrix multiply(TripleMatrix other)
  {
    double[] result = new double[9];
    for (int row = 0; row < 3; row++)
    {
      for (int col = 0; col < 3; col++)
      {
        for (int i = 0; i < 3; i++)
        {
          result[row * 3 + col] += this.values[row * 3 + i] * other.values[i * 3 + col];
        }
      }
    }
    return new TripleMatrix(result);
  }

  /**
   * Transforms a vertex with this matrix.
   * 
   * @param in
   * @return
   */
  public Vertex transform(Vertex in)
  {
    return new Vertex(
        in.x * values[0] + in.y * values[3] + in.z * values[6], 
        in.x * values[1] + in.y * values[4] + in.z * values[7], 
        in.x * values[2] + in.y * values[5] + in.z * values[8]);
  }
}