import java.util.ArrayList;
import javax.swing.*;

/**
 * The LevelMap class represents the panel. It stores the targets and inventory.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class LevelMap extends JPanel
{
  //the target particles
  private ArrayList <Particle> targets = new ArrayList <Particle>();
  //the inventory particles
  private ArrayList <Particle> inventory = new ArrayList <Particle>();
  //the game grid
  private GameGrid grid = new GameGrid();
  
  //default constructor
  public LevelMap ()
  {
  }
  
  //returns the target at location in parameters, returns null if not found
  public Particle getTarget (Location location)
  {
    for (int x = 0;x<targets.size();x++)
    {
      if (targets.get(x).getLocation().equals (location))
        return targets.get(x);
    }
    return null;
  }
  
  //returns the inventory particles at location in parameters, returns null if not found
  public Particle getInventory (Location location)
  {
    for (int x=0;x<inventory.size();x++)
    {
      if (inventory.get(x).getLocation().equals(location))
        return inventory.get(x);
    }
    return null;
  }
  
  //returns arraylist of all target particles
  public ArrayList<Particle> getAllTargets ()
  {
    return targets;
  }
  
  //returns arraylist of all inventory particles
  public ArrayList<Particle> getAllInventory ()
  {
    return inventory;
  }
  
  //replaces target particles with newTargets
  public void setAllTargets (ArrayList<Particle> newTargets)
  {
    targets = newTargets;
  }
  
  //replaces inventory particles with newInventory
  public void setAllInventory (ArrayList<Particle> newInventory)
  {
    inventory = newInventory;
  }
  
  //returns the grid of the panel
  public GameGrid getGameGrid()
  {
    return grid;
  }
}