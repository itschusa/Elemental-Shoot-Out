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
 */
public class CurrentPlayer extends Element
{
  private ImageIcon myIcon;
  //user points
  private int points;
  private boolean canMove;
  
  public CurrentPlayer (String newName, Location newLocation)
  {
    super (newName, newLocation);
    myIcon = Database.icon4;
  }
  
  public void setCanMove (boolean move)
  {
    canMove=move;
  }
  
  public boolean canMove ()
  {
    return canMove;
  }
  
    public void setShift (boolean set)
  {
  }
    
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
  public void update()
  {
  }
  
  public void update (int movement)
  {
    if (canMove(movement))
      move(movement);
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
  
  //empty override
  public void setCurrentStep(int steps)
  {
  }
  
  public int getCharge ()
  {
    return 0;
  }
}