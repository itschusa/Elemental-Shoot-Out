import javax.swing.*;
import java.awt.*;

/**
 * The CurrentPlayer class stores the score of the player.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen 
 * @version 1.0, May 21 2014. (point system)
 * @version 1.1, May 22, 2014. (now extends Element, with location, name, etc.)
 * @version 1.2, May 24 2014. (Coded basic logic for keyboard determined movement.)
 * @version 1.3, May 26 2014. (Overloaded Element's empty "update" override.)
 * @version 1.4, May 27, 2014. (added setCanMove, and setCurrentStep(empty))
 * @version 1.5, May 28, 2014. (added getCharge (empty))
 * @version 1.6, May 29, 2014. (JavaDoc all parts)
 */
public class CurrentPlayer extends Element
{
  /**
   * myIcon - ImageIcon - Stores the image that represents the player.
   */
  private ImageIcon myIcon;
  /**
   * points - int - Stores the current number of points the user has.
   */
  private int points;
  /**
   * canMove - boolean - Stores whether the player can move or not.
   */
  private boolean canMove;
  
  /**
   * The constructor creates a new CurrentPlayer object, while setting the name, location, and icon.
   * 
   * @param newName - String - Stores the name of the element (should be 'player').
   * @param newLocation - Location - Stores the initial location of the launcher.
   */
  public CurrentPlayer (String newName, Location newLocation)
  {
    super (newName, newLocation);
    myIcon = Database.icon4;
  }
  
  /**
   * Sets whether the player can move as indicated by the parameter.
   * 
   * @param move - boolean - Boolean to set whether the launcher can move.
   */
  public void setCanMove (boolean move)
  {
    canMove=move;
  }
  
  /**
   * Returns whether the player can move.
   */
  public boolean canMove ()
  {
    return canMove;
  }
  
  /**
   * Empty override from the Element class
   */
  public void setShift (boolean set)
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
        canMove = false;
      else
        canMove = true;
    }
    else
    {
      if (direction == 39)
      {
        if (getLocation().getColumn() == 12)
          canMove = false;
        else
          canMove = true;
      }
    }
    return canMove;
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
  
  //Empty override
  /**
   * Empty override from the Element class.
   */
  public void update()
  {
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
   * Draws the launcher on the screen.
   * 
   * @param graphics - Graphics2D - The Graphics2D object.
   */
  public void draw (Graphics2D graphics)
  {
    graphics.drawImage (getIcon().getImage(), getLocation().getXCoord(), getLocation().getYCoord(),getIcon().getImageObserver());
  }
  
  //returns the icon
  /**
   * Returns the icon.
   */
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
  
  //add points
  /**
   * Adds the number of points to the current points as specified by the parameter.
   * 
   * @param add - int - The number of points to add.
   */
  public void addPoints (int add)
  {
    points += add;
  }
  
  //remove points
  /**
   * Removes the number of points from the current points as specified by the parameter.
   * If the number of points to remove is less than the total number of points, points is set to 0.
   * @param remove - int - The number of points to remove.
   */
  public void removePoints (int remove)
  {
    points -= remove;
    if (points < 0)
      points = 0;
  }
  
  //return points
  /**
   * Returns the current number of points of the player.
   */
  public int getCurrentPoints ()
  {
    return points;
  }
  
  //empty override
  /**
   * Empty override from the Element class. (Note: Remove later)
   */
  public void setCurrentStep(int steps)
  {
  }
  
  /**
   * Returns the charge of the player.
   * The launcher does not have a charge, so returns 0.
   */
  public int getCharge ()
  {
    return 0;
  }
}