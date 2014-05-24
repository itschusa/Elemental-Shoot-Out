import javax.swing.*;
import java.awt.*;

/**
 * The CurrentPlayer class stores the score of the player.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (point system)
 * @version 1.1, May 22, 2014. (now extends Element, with location, name, etc.)
 */
public class CurrentPlayer extends Element
{
  private ImageIcon myIcon;
  //user points
  private int points;
  private boolean canMove;
  private KeyboardListener keyListen;
  
  public CurrentPlayer (String newName, Location newLocation, GameGrid newGrid)
  {
    super (newName, newLocation, newGrid);
    myIcon = Database.icon4;
  }
  
  public void reverseMove ()
  {
    if (canMove == true)
      canMove = false;
    else
      canMove = true;
  }
  
  public boolean canMove ()
  {
    return canMove;
  }
  
  public boolean canMove (int direction)
  {
    if (direction == 37)//left
    {
      if (getLocation().getRow() == 1)
        canMove = false;
      else
        canMove = true;
    }
    else
    {
      if (direction == 39)
      {
        if (getLocation().getRow() == 12)
          canMove = false;
        else
          canMove = true;
      }
    }
    return canMove;
  }
  
  public void move (int direction)
  {
    if (direction == 37)//left
      getLocation().setRow(getLocation().getRow()-1);
    else
    {
      if (direction == 39)//right
        getLocation().setRow(getLocation().getRow()+1);
    }
  }
  
  public void update ()
  {
    
  }
  
  public void draw (Graphics2D graphics)
  {
    graphics.drawImage (getIcon().getImage(), getLocation().getXCoord(), getLocation().getYCoord(),getIcon().getImageObserver());
  }
  
  //returns the icon
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
  
  //add points
  public void addPoints (int add)
  {
    points += add;
  }
  
  //remove points
  public void removePoints (int remove)
  {
    points -= remove;
    if (points < 0)
      points = 0;
  }
  
  //return points
  public int getCurrentPoints ()
  {
    return points;
  }
}