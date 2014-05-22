/**
 * The Location class stores the x coordinates and y coordinates (location).
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class Location
{
  //x coord
  private int xCoord;
  //y coord
  private int yCoord;
  
  //constructor, sets x and y coords using column and row
  public Location (int column, int row)
  {
    xCoord = column*50;
    yCoord = row*50;
  }
  
  //returns column
  public int getColumn ()
  {
    return xCoord/50;
  }
  
  //returns row
  public int getRow ()
  {
    return yCoord/50;
  }
  
  //sets column
  public void setColumn (int column)
  {
    xCoord = column*50;
  }
  
  //sets row
  public void setRow (int row)
  {
    yCoord = row*50;
  }
  
  //returns xcoord
  public int getXCoord ()
  {
    return xCoord;
  }
  
  //returns ycoord
  public int getYCoord ()
  {
    return yCoord;
  }
}