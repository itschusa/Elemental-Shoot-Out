import javax.swing.*;
import java.awt.*;

/**
 * The Particle class represents a particle. It stores the name, location and grid.
 * 
 * @author Anqi Wu
 * @author baseball435 (update and draw methods)
 * @version 1.0, May 21 2014. (removeFromGrid method not functional)
 * @version 1.1, May 22, 2014. (still not functional, allows all subclasses to update themselves)
 */
public abstract class Element
{
  //the particle name
  private String name;
  //the location
  private Location location;
  //the grid
  //private GameGrid myGrid;
  
  //constructor, sets the name, location and grid
  public Element (String newName, Location newLocation, GameGrid newGrid)
  {
    name = newName;
    location = newLocation;
    //myGrid = newGrid;
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
  
  //remove particle, not functional
  public void removeFromGrid ()
  {
    location = null;
    //myGrid = null;
  }
  
  public abstract void reverseMove ();
  
  public abstract boolean canMove ();
  
  //override to return icon
  public abstract ImageIcon getIcon ();
  
  public abstract void update ();
  
  public abstract void draw (Graphics2D graphics);
}