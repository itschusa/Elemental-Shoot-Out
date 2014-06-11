package game;

import javax.swing.*;

/**
 * The GameParticle class represents the generic game element.
 * 
 * @author Anqi Wu
 * @version 2.0, June 2 2014 (Has all the methods - Thinking of just combining all into one, with param indicating level)
 * @version 2.1, June 3 2014 (Deprecated EasyParticle, MediumParticle and DifficultParticle)
 * @version 2.2, June 3 2014 (Removed unecessary code, update moves the particle one pixel at a time)
 * @version 2.3, June 5 2014 (setReverseShift method)
 * @version 2.4, June 5 2014. (JavaDoc, removed some methods)
 */
public class GameParticle extends Element
{
  /**
   * canMove - private boolean - Stores whether the element can move.
   */
  private boolean canMove;
  /**
   * shift - private boolean - Stores whether the element should shift left.
   */
  private boolean shift;
  /**
   * reverseShift - private boolean - Stores whether the element should shift right.
   */
  private boolean reverseShift;
  /**
   * charge - private int - Stores the charge of the GameParticle.
   */
  private int charge;
  
  /**
   * Constructs a new GameParticle with the specified name, location and level.
   * 
   * @param newName - String - The name of the element.
   * @param newLocation - Location - The location of the element.
   * @param charge - int - The charge of the GameParticle.
   * @param level - int - The level of the GameParticle.
   */
  public GameParticle (String newName, Location newLocation, int charge, int level)
  {
    //Element constructor
    super (newName, newLocation);
    
    //easy
    if (level == 1)
    {
      if (newName.equals ("Stable"))
        setIcon (Database.icon);
      else if (newName.equals ("Unstable"))
        setIcon (Database.icon2);
      else
        setIcon (Database.icon3);
    }
    //medium
    else if (level == 2)
    {
      if (newName.equals ("Lithium"))
        setIcon (Database.icon5);
      else if (newName.equals ("Sodium"))
        setIcon (Database.icon6);
      else if (newName.equals ("Potassium"))
        setIcon (Database.icon7);
      else if (newName.equals ("Rubidium"))
        setIcon (Database.icon8);
      else if (newName.equals ("Cesium"))
        setIcon (Database.icon9);
      else if (newName.equals ("Francium"))
        setIcon (Database.icon10);
      else if (newName.equals("Hydroxide"))
        setIcon (Database.icon11);
      else if (newName.equals("Cloud"))
        setIcon (Database.icon12);
      else
        setIcon (Database.icon13);
    }
    //difficult
    else
    {
      String path = "Images/Difficult/" + newName + ".png";
      setIcon (new ImageIcon (path));
    }
    
    //set charge
    this.charge = charge;
  }
  
  /**
   * Updates the location of the GameParticle.
   * If the GameParticle can move, the location moves up by 1 pixel.
   * Otherwise, if shift if true, then move the location to the cell to the left. shift is then set to false.
   * If reverseShift is true, move the location to the cell to the right. reverseShift is then set to false.
   * Overridden from the Element class.
   */
  public void update ()
  {
    if (getLocation() == null)
      return;
    
    if (canMove())
    {
      setLocation (new Location (getLocation().getXCoord(), getLocation().getYCoord()-1, true));
      updateBounce();
    }
    else if (shift)
    {
      setLocation(new Location(getLocation().getColumn()-1, getLocation().getRow(), false));
      shift = false;
    }
    else
    {
      if (reverseShift)
      {
        setLocation (new Location (getLocation().getColumn()+1,getLocation().getRow(), false));
        reverseShift = false;
      }
    }
  }
  
  /**
   * Sets whether the element can shift to the left depending on the parameter.
   * This method is used solely by inventory particles.
   * 
   * @param set - boolean - Stores whether or not the element should shift to the left.
   */
  public void setShift (boolean set)
  {
    shift = set;
  }
  
  /**
   * Sets whether the element can shift to the right depending on the parameter.
   * This method is used solely by inventory particles.
   * 
   * @param set - boolean - Stores whether or not the element should shift to the right.
   */
  public void setReverseShift (boolean set)
  {
    reverseShift = set;
  }
  
  /**
   * Updates whether the GameParticle travels outside the game boundaries.
   * If the location is outside, the GameParticle's location is set to null.
   */
  public void updateBounce ()
  {
    if (getLocation().getRow() <= 0)
      removeFromGrid();
  }
  
  /**
   * Returns the charge of the GameParticle.
   */
  public int getCharge()
  {
    return charge;
  }
}