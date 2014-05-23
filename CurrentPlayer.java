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
  
  public CurrentPlayer (String newName, Location newLocation, GameGrid newGrid)
  {
    super (newName, newLocation, newGrid);
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
  
  public void update ()
  {
  }
  
  public void draw (Graphics2D graphics)
  {
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