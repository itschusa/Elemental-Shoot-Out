package game;

/**
 * The Location class stores the x coordinates and y coordinates (location) of an element.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 * @version 1.1, June 3 2014. (Added setXCoord and setYCoord methods)
 * @version 1.2, June 8 2014. (JavaDoc)
 */
public class Location
{
  /**
   * xCoord - int - Stores the x-coordinate.
   */
  private int xCoord;
  /**
   * yCoord - int - Stores the y-coordinate.
   */
  private int yCoord;
  
  //constructor, sets x and y coords using column and row
  /**
   * Constructs a new location depending on the column, and row specified in the parameters.
   * If exact is true, the exact column and row should be given in pixels, rather than the grid form.
   * 
   * @param column - int - The column of the location.
   * @param row - int - The row of the location.
   * @param exact - boolean - Whether the column and row are in pixels.
   */
  public Location (int column, int row, boolean exact)
  {
    if (exact)
    {
      xCoord = column;
      yCoord = row;
    }
    else
    {
      xCoord = column*50;
      yCoord = row*50;
    }
  }
  
  /**
   * Returns the column (not in pixels).
   */
  public int getColumn ()
  {
    return xCoord/50;
  }
  
  /**
   * Returns the row (not in pixels).
   */
  public int getRow ()
  {
    return yCoord/50;
  }
  
  /**
   * Sets the column to the column specified in the parameter(not in pixels).
   */
  public void setColumn (int column)
  {
    xCoord = column*50;
  }
  
  /**
   * Sets the row to the row specified in the parameter (not in pixels).
   */
  public void setRow (int row)
  {
    yCoord = row*50;
  }
  
  /**
   * Returns the x-coordinate in pixels.
   */
  public int getXCoord ()
  {
    return xCoord;
  }
  
  /**
   * Returns the y-coordinate in pixels.
   */
  public int getYCoord ()
  {
    return yCoord;
  }
  
  /**
   * Sets the x-coordinate to the coordinate specified in the parameter in pixels.
   */
  public void setXCoord (int coord)
  {
    xCoord = coord;
  }
  
  /**
   * Sets the y-coordinate to the coordinate specified in the parameter in pixels.
   */
  public void setYCoord (int coord)
  {
    yCoord = coord;
  }
}