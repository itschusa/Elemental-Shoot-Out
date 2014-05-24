/**
 * The GameGrid class stores whether there are objects in the location. May be obsolete.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (Is not used by other classes currently)
 */
public class GameGrid
{
  private boolean[][] usedGrid = new boolean[8][12];
  
  //dimensions of the game grid are 600 by 400 currently
  public GameGrid ()
  {
  }
  
  public boolean isUsed (int row, int col)
  {
    return usedGrid [row][col];
  }
  
  public void setGridUsed (int row, int col, boolean replace)
  {
    usedGrid[row][col] = replace;
  }
}