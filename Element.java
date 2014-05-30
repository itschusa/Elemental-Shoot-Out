import javax.swing.*;
import java.awt.*;

/**
 * The Particle class represents a particle. It stores the name, location and grid.
 * 
 * @author Anqi Wu
 * @author baseball435 (update and draw methods)
 * @version 1.0, May 21 2014. (removeFromGrid method not functional)
 * @version 1.1, May 22, 2014. (still not functional, allows all subclasses to update themselves)
 * @version 1.2, May 27, 2014. (added more abstract methods that easyparticle uses. removeFromGrid now works!)
 * @version 1.3, May 28, 2014. (added abstract method getCharge)
 * @version 1.4, May 29, 2014. (JavaDoc)
 */
public abstract class Element
{
  /**
   * name - String - Stores the name of the element.
   */
  private String name;
  /**
   * lcoation - Location - Stores the location of the element.
   */
  private Location location;
  
  //constructor, sets the name, location and grid
  /**
   * Constructs a new Element with the specified name and location.
   * 
   * @param newName - String - The name of the element.
   * @param newLocation - Location - The location of the element.
   */
  public Element (String newName, Location newLocation)
  {
    name = newName;
    location = newLocation;
  }
  
  //returns name
  /**
   * Returns the name of the element.
   */
  public String getName ()
  {
    return name;
  }
  
  //sets name
  /**
   * Sets the name of the element to the specified new name.
   * 
   * @param newName - String - The new name of the element.
   */
  public void setName (String newName)
  {
    name = newName;
  }
  
  //returns location
  /**
   * Returns the Location of the element.
   */
  public Location getLocation ()
  {
    return location;
  }
  
  //sets location
  /**
   * Sets the location of the element to the specified new location.
   * 
   * @param newLocation - Location - The new location of the element.
   */
  public void setLocation (Location newLocation)
  {
    location = newLocation;
  }
  
  //remove particle
  /**
   * Removes itself by setting the location to null.
   */
  public void removeFromGrid ()
  {
    location = null;
  }
  
  /**
   * Returns the charge of the element.
   */
  public abstract int getCharge();
  
  /**
   * Sets canMove to the boolean specified in the parameters.
   * 
   * @param move - boolean - Stores whether or not the element can move.
   */
  public abstract void setCanMove (boolean move);
  
  /**
   * Sets whether the element can shift depending on the parameter.
   * This method is used solely by inventory particles.
   * 
   * @param set - boolean - Stores whether or not the element should shift to the left.
   */
  public abstract void setShift (boolean set);
  
  /**
   * Returns whether or not the element can move.
   */
  public abstract boolean canMove ();
  
  /**
   * Sets the current step of the element.
   * 
   * @param steps - int - The number of calls to update before the element moves.
   */
  public abstract void setCurrentStep(int steps);
  
  //override to return icon
  /**
   * Returns the icon of the element.
   */
  public abstract ImageIcon getIcon ();
  
  /**
   * Updates the status of the element.
   */
  public abstract void update ();
  
  /**
   * Draws the element/icon.
   * 
   * @param graphics - Graphics2D - The Graphics2D object.
   */
  public abstract void draw (Graphics2D graphics);
}