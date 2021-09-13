import java.util.ArrayList;

public class Board
{
  // Blank space: 0
  // Player 1: 1
  // Player 2: -1
  public int[][] pieces;

  public static final int[][] startingBoard = {
      { +1, +1, +1, +1, 0, 0, 0, 0, 0 },
      { +1, +1, +1, 0, 0, 0, 0, 0, 0 },
      { +1, +1, 0, 0, 0, 0, 0, 0, 0 },
      { +1, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, -1 },
      { 0, 0, 0, 0, 0, 0, 0, -1, -1 },
      { 0, 0, 0, 0, 0, 0, -1, -1, -1 },
      { 0, 0, 0, 0, 0, -1, -1, -1, -1 }
  };

  // copies a 2d rectangular array into a board
  public Board(int[][] config)
  {
    pieces = new int[config.length][config[0].length];

    for (int i = 0; i < config.length; i++)
      for (int j = 0; j < config.length; j++)
        pieces[i][j] = config[i][j];
  }
  
  public Board(Board o)
  {
    this(o.pieces);
  }

  // starting position
  public Board()
  {
    this(startingBoard);
  }

  public int getScore()
  {
    int score = 0;
    int length = pieces.length + pieces[0].length;

    for (int i = 0; i < pieces.length; i++)
    {
      for (int j = 0; j < pieces[0].length; j++)
      {
        int value = pieces[i][j];
        
        if (value == 0)
          continue;

        score += i + j + 1;
        
        if (value < 0)
          score -= length;
      }
    }

    return score;
  }

  public String toString()
  {
    String s = "";

    for (int i = 0; i < pieces.length; i++)
    {
      for (int j = 0; j < pieces[i].length; j++)
      {
        s += pieces[i][j];
        if (j != pieces[i].length - 1)
          s += " ";
      }
      if (i != pieces.length - 1)
        s += "\n";
    }

    return s;
  }
  
  public ArrayList<Board> getNextMove(boolean isTurn1)
  {
    ArrayList<Board> set = new ArrayList<Board>();
    
    int color = isTurn1 ? 1 : -1;
    
    for (int i = 0; i < pieces.length; i++)
    {
      for (int j = 0; j < pieces[0].length; j++)
      {
        if (pieces[i][j] == color)
        {
          boolean hopsFinished = false;
          // The direction of the last hop
          int previousDirectionX = 0;
          int previousDirectionY = 0;
          
          while(!hopsFinished)
          {
            
          }
        }
      }
    }
  }
}
