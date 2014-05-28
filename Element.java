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
 */
public abstract class Element
{
  //the particle name
  private String name;
  //the location
  private Location location;
  
  //constructor, sets the name, location and grid
  public Element (String newName, Location newLocation)
  {
    name = newName;
    location = newLocation;
  }
  
  //returns name
  public String getName ()
  {
    return name;
  }
  
  //sets name
  public void setName (String newName)
  {
    name = newName;
  }
  
  //returns location
  public Location getLocation ()
  {
    return location;
  }
  
  //sets location
  public void setLocation (Location newLocation)
  {
    location = newLocation;
  }
  
  //remove particle
  public void removeFromGrid ()
  {
    location = null;
  }
  
  public abstract int getCharge();
  
  public abstract void setCanMove (boolean steps);
  
  public abstract void setShift (boolean set);
  
  public abstract boolean canMove ();
  
  public abstract void setCurrentStep(int steps);
  
  //override to return icon
  public abstract ImageIcon getIcon ();
  
  public abstract void update ();
  
  public abstract void draw (Graphics2D graphics);
}