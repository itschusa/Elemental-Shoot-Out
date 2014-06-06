import javax.swing.*;
import java.awt.*;

/**
 * The Element class represents a generic element with a name and location.
 * This class is meant to be extended from.
 * 
 * @author Anqi Wu
 * @author baseball435 (update and draw methods)
 * @version 1.0, May 21 2014. (removeFromGrid method not functional)
 * @version 1.1, May 22, 2014. (still not functional, allows all subclasses to update themselves)
 * @version 1.2, May 27, 2014. (added more abstract methods that easyparticle uses. removeFromGrid now works!)
 * @version 1.3, May 28, 2014. (added abstract method getCharge)
 * @version 1.4, May 29, 2014. (JavaDoc)
 * @version 1.5, June 3, 2014. (Removed methods associated with currentSteps)
 * @version 1.6, June 5, 2014. (Removed methods and JavaDoc is current)
 */
public abstract class Element
{
  /**
   * name - private String - Stores the name of the element.
   */
  private String name;
  /**
   * location - private Location - Stores the location of the element.
   */
  private Location location;
  /**
   * myIcon - private ImageIcon - Stores the icon of the game particle.
   */
  private ImageIcon myIcon;
  /**
   * canMove - boolean - Stores whether the player can move or not.
   */
  private boolean canMove;
  
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
  
  /**
   * Returns the name of the element.
   */
  public String getName ()
  {
    return name;
  }
  
  /**
   * Sets the name of the element to the specified new name.
   * 
   * @param newName - String - The new name of the element.
   */
  public void setName (String newName)
  {
    name = newName;
  }
  
  /**
   * Returns the Location of the element.
   */
  public Location getLocation ()
  {
    return location;
  }
  
  /**
   * Sets the location of the element to the specified new location.
   * 
   * @param newLocation - Location - The new location of the element.
   */
  public void setLocation (Location newLocation)
  {
    location = newLocation;
  }
  
  /**
   * Removes itself by setting the location to null.
   */
  public void removeFromGrid ()
  {
    location = null;
  }
  
  /**
   * Returns the icon of the element.
   */
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
  
  /**
   * Sets the icon for the element.
   * 
   * @param icon - ImageIcon - The new icon.
   */
  public void setIcon (ImageIcon icon)
  {
    myIcon = icon;
  }
  
  /**
   * Sets canMove to the boolean specified in the parameters.
   * 
   * @param move - boolean - Stores whether or not the element can move.
   */
  public void setCanMove (boolean move)
  {
    canMove = move;
  }
  
  /**
   * Returns whether or not the element can move.
   */
  public boolean canMove ()
  {
    return canMove;
  }
  
  /**
   * Draws the icon.
   * 
   * @param graphics - Graphics2D - The Graphics2D object.
   */
  public void draw (Graphics2D graphics)
  {
    if (getLocation()== null)
      return;
    
    graphics.drawImage (getIcon().getImage(), getLocation().getXCoord(), getLocation().getYCoord(),getIcon().getImageObserver());
  }
  
  /**
   * Updates the status of the element.
   */
  public abstract void update ();
}