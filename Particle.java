import javax.swing.*;

/**
 * The Particle class represents a particle. It stores the name, location and grid.
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (removeFromGrid method not functional)
 */
public abstract class Particle
{
  //the particle name
  private String name;
  //the location
  private Location location;
  //the grid
  private GameGrid myGrid;
 
  //constructor, sets the name, location and grid
  public Particle (String newName, Location newLocation, GameGrid newGrid)
  {
    name = newName;
    location = newLocation;
    myGrid = newGrid;
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
    myGrid = null;
  }
  
  //override to return icon
  public abstract ImageIcon getIcon ();
}