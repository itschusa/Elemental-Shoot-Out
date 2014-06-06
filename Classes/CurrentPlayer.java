import javax.swing.*;
import java.awt.*;

/**
 * The CurrentPlayer class stores the score of the player.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen 
 * @version 1.0, May 21 2014. (point system)
 * @version 1.1, May 22 2014. (now extends Element, with location, name, etc.)
 * @version 1.2, May 24 2014. (Coded basic logic for keyboard determined movement.)
 * @version 1.3, May 26 2014. (Overloaded Element's empty "update" override.)
 * @version 1.4, May 27 2014. (added setCanMove, and setCurrentStep(empty))
 * @version 1.5, May 28 2014. (added getCharge (empty))
 * @version 1.6, May 29 2014. (JavaDoc all parts)
 * @version 1.7, June 3 2014. (Player can have negative points, added method to set icon)
 * @version 1.8, June 5 2014. (JavaDoc is current, removed some methods)
 */
public class CurrentPlayer extends Element
{
  /**
   * points - int - Stores the current number of points the user has.
   */
  private int points;
  
  /**
   * The constructor creates a new CurrentPlayer object, while setting the name, location, and icon.
   * 
   * @param newName - String - Stores the name of the element (should be 'Launcher').
   * @param newLocation - Location - Stores the initial location of the launcher.
   */
  public CurrentPlayer (String newName, Location newLocation)
  {
    super (newName, newLocation);
    setIcon (Database.icon4);
  }
  
  /**
   * Empty override from the Element class.
   */
  public void update()
  {
  }
  
  /**
   * Checks whether the launcher can move depending on the location of the launcher.
   * It returns true all times except when the launcher is on the two edges of the screen.
   * 
   * @param direction - int - The direction as the ASCII value of the keyboard key.
   */
  public boolean canMove (int direction)
  {
    if (direction == 37)//left
    {
      if (getLocation().getColumn() == 1)
        setCanMove (false);
      else
        setCanMove (true);
    }
    else
    {
      if (direction == 39)
      {
        if (getLocation().getColumn() == 12)
          setCanMove (false);
        else
          setCanMove (true);
      }
    }
    return canMove();
  }
  
  /**
   * Moves the launcher one column in the direction set by the parameters.
   * It changes the location of the launcher.
   * 
   * @param direction - int - The direction as the ASCII value of the keyboard key. (37 is left, and 39 is right)
   */
  public void move (int direction)
  {
    if (direction == 37)//left
      getLocation().setColumn(getLocation().getColumn()-1);
    else
    {
      if (direction == 39)//right
        getLocation().setColumn(getLocation().getColumn()+1);
    }
  }
  
  /*
   * Updates the launcher movement depending on the direction as set by the parameters.
   * It checks to see if the launcher can move, and then changes the direction.
   * 
   * @param movement - int - The direction as the ASCII value of the keyboard key.
   */
  public void update (int movement)
  {
    if (canMove(movement))
      move(movement);
  }
  
  /**
   * Adds the number of points to the current points as specified by the parameter.
   * 
   * @param add - int - The number of points to add.
   */
  public void addPoints (int add)
  {
    points += add;
  }
  
  /**
   * Removes the number of points from the current points as specified by the parameter.
   * 
   * @param remove - int - The number of points to remove.
   */
  public void removePoints (int remove)
  {
    points -= remove;
  }
  
  /**
   * Returns the current number of points of the player.
   */
  public int getCurrentPoints ()
  {
    return points;
  }
}