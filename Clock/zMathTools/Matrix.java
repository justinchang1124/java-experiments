/**
 * Define an m by n matrix as a rectangular array of numbers with m rows and n
 * columns. This class represents such a matrix. New matrices are always
 * provided.
 *
 * @author Justin C
 */
public class Matrix
{
  public int m, n;

  public double[][] v;

  /**
   * Constructs a zero Matrix.
   */
  public Matrix(int row, int col)
  {
    if (row < 1 || col < 1)
      notValidSize();

    m = row;
    n = col;

    v = new double[m][n];
  }

  /**
   * Constructs an identity Matrix.
   */
  public Matrix(int d)
  {
    this(d, d);

    for (int i = 0; i < m; i++)
      v[i][i] = 1;
  }

  /**
   * Constructs a Matrix from an array.
   */
  public Matrix(double[][] o)
  {
    this(o.length, o[0].length);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        v[i][j] = o[i][j];
  }

  /**
   * Constructs a Matrix from a column.
   */
  public Matrix(double[] o)
  {
    this(o.length, 1);

    for (int i = 0; i < m; i++)
      v[i][0] = o[i];
  }

  /**
   * Constructs a 3x3 Identity Matrix.
   */
  public Matrix()
  {
    this(3);
  }

  public static void notValidSize()
  {
    throw new IllegalArgumentException("Matrix cannot be constructed from nonpositive dimensions!");
  }

  public static void notSquare()
  {
    throw new IllegalArgumentException("This operation cannot be performed on a nonsquare Matrix!");
  }

  public static void notCompatible()
  {
    throw new IllegalArgumentException("This operation cannot be performed on Matrix \"this, o\"!");
  }

  public Matrix add(Matrix o)
  {
    if (o.m != m || o.n != n)
      notCompatible();

    Matrix r = new Matrix(v);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        r.v[i][j] += o.v[i][j];

    return r;
  }

  public Matrix dot(Matrix o)
  {
    if (o.m != m || o.n != n)
      notCompatible();

    Matrix r = new Matrix(v);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        r.v[i][j] *= o.v[i][j];

    return r;
  }

  public Matrix dot(double o)
  {
    Matrix r = new Matrix(v);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        r.v[i][j] *= o;

    return r;
  }

  public Matrix times(Matrix o)
  {
    if (n != o.m)
      notCompatible();

    Matrix r = new Matrix(m, o.n);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < o.n; j++)
        for (int k = 0; k < n; k++)
          r.v[i][j] += v[i][k] * o.v[k][j];

    return r;
  }

  public Matrix transpose()
  {
    Matrix r = new Matrix(n, m);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        r.v[j][i] = v[i][j];

    return r;
  }

  public double determinant()
  {
    if (m != n)
      notSquare();

    if (m == 1)
      return v[0][0];

    if (m == 2)
      return (v[0][0] * v[1][1]) - (v[0][1] * v[1][0]);

    double sum = 0;

    for (int i = 0; i < n; i++)
      sum += Math.pow(-1, i) * v[0][i] * subMatrix(0, i).determinant();

    return sum;
  }

  public Matrix subMatrix(int row_removed, int col_removed)
  {
    Matrix r = new Matrix(m - 1, n - 1);
    int row = -1;

    for (int i = 0; i < m; i++)
    {
      if (i == row_removed)
        continue;
      row++;

      int col = -1;
      for (int j = 0; j < n; j++)
      {
        if (j == col_removed)
          continue;
        col++;
        r.v[row][col] = v[i][j];
      }
    }

    return r;
  }

  public Matrix cofactor()
  {
    Matrix r = new Matrix(m, n);

    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        r.v[i][j] = Math.pow(-1, i + j) * subMatrix(i, j).determinant();

    return r;
  }

  public Matrix inverse()
  {
    if (m != n)
      notSquare();

    if (m == 1)
    {
      double value[][] = new double[][] { { 1 / v[0][0] } };
      Matrix r = new Matrix(value);
      return r;
    }

    if (m == 2)
    {
      double a = v[0][0];
      double b = v[0][1];
      double c = v[1][0];
      double d = v[1][1];
      double det = a * d - b * c;
      double value[][] = new double[][] { { d, -b }, { -c, a }
      };
      Matrix r = new Matrix(value);
      return r.dot(1 / det);
    }

    if (m == 3)
    {
      double a = v[0][0];
      double b = v[0][1];
      double c = v[0][2];
      double d = v[1][0];
      double e = v[1][1];
      double f = v[1][2];
      double g = v[2][0];
      double h = v[2][1];
      double i = v[2][2];
      double det = a * e * i + b * f * g + c * d * h - a * f * h - b * d * i - c * e * g;
      double value[][] = {

          { e * i - f * h, c * h - b * i, b * f - c * e },

          { f * g - d * i, a * i - c * g, c * d - a * f },

          { d * h - e * g, b * g - a * h, a * e - b * d }
      };

      Matrix r = new Matrix(value);
      return r.dot(1 / det);
    }

    return cofactor().transpose().dot(1 / determinant());
  }

  /**
   * Rotate around the 3 axes perpendicular to the specified planes.
   */
  public static Matrix rotate(double xy, double yz, double zx)
  {
    Matrix r = rotate(1, 0, 0, yz).times(rotate(0, 1, 0, zx).times(rotate(0, 0, 1, xy)));
    return r;
  }

  /**
   * Rotates axis counterclockwise by theta around Vertex(l,m,n).
   */
  public static Matrix rotate(double l, double m, double n, double theta)
  {
    double c = Math.cos(theta);
    double s = Math.sin(theta);
    double a = 1 - c;

    Matrix r = new Matrix(new double[][] {

        { l * l * a + 1 * c, m * l * a - n * s, n * l * a + m * s },

        { l * m * a + n * s, m * m * a + 1 * c, n * m * a - l * s },

        { l * n * a - m * s, m * n * a + l * s, n * n * a + 1 * c }
    });

    return r;
  }

  public Matrix rref()
  {
    Matrix r = new Matrix(v);

    // The index of the first row that has not yet been fixed.
    int minPivot = 0;

    for (int col = 0; col < n && minPivot < m; ++col)
    {
      // The index of the largest unfixed possible pivot in the column.
      int largestPivot = minPivot;
      for (int i = minPivot + 1; i < m; ++i)
        if (Math.abs(r.v[i][col]) > Math.abs(r.v[largestPivot][col]))
          largestPivot = i;

      // If the largest pivot is 0, next column.
      if (Math.abs(r.v[largestPivot][col]) < 0.00001)
        continue;

      // Swaps the largest pivot into the first unfixed position.
      for (int j = 0; j < n; ++j)
      {
        double temp = r.v[largestPivot][j];
        r.v[largestPivot][j] = r.v[minPivot][j];
        r.v[minPivot][j] = temp;
      }

      // Turns the pivot row's leading entry into a 1.
      double largestPivotInverse = 1.0 / r.v[minPivot][col];
      for (int j = 0; j < n; ++j)
        r.v[minPivot][j] *= largestPivotInverse;

      // Removes the leading entries of all other rows.
      for (int i = 0; i < m; ++i)
        if (i != minPivot)
        {
          double t = r.v[i][col];
          for (int j = 0; j < n; ++j)
            r.v[i][j] -= t * r.v[minPivot][j];
        }

      minPivot++;
    }

    return r;
  }

  public String toString()
  {
    String r = "";

    for (int i = 0; i < m; i++)
    {
      for (int j = 0; j < n - 1; j++)
        r += v[i][j] + " ";

      r += v[i][n - 1] + "\n";
    }

    return r;
  }
}