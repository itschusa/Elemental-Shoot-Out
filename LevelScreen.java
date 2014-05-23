import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * @author Anqi Wu
 * @author baseball435
 * @version 1.0, May 21, 2014. (Methods of the LevelMap class)
 * @version 1.0, May 22, 2014. (Moved all methods over to this class, added getWallpaper method, extends Screen)
 */
public class LevelScreen extends Screen
{
  //the target particles
  private ArrayList <Element> targets = new ArrayList <Element>();
  //the inventory particles
  private ArrayList <Element> inventory = new ArrayList <Element>();
  //the game grid
  private GameGrid grid = new GameGrid();
  private ImageIcon wallpaper = new ImageIcon ("WallpaperGame.png");
  
  public LevelScreen (ScreenFactory screenFactory)
  {
    super(screenFactory);
  }
  
  public void onCreate ()
  {
  }
  
  public void onUpdate ()
  {
  }
  
  public void onDraw (Graphics2D twoDimensional)
  {
  }
  
  //returns the target at location in parameters, returns null if not found
  public Element getTarget (Location location)
  {
    for (int x = 0;x<targets.size();x++)
    {
      if (targets.get(x).getLocation().equals (location))
        return targets.get(x);
    }
    return null;
  }
  
  //returns the inventory particles at location in parameters, returns null if not found
  public Element getInventory (Location location)
  {
    for (int x=0;x<inventory.size();x++)
    {
      if (inventory.get(x).getLocation().equals(location))
        return inventory.get(x);
    }
    return null;
  }
  
  //returns arraylist of all target particles
  public ArrayList<Element> getAllTargets ()
  {
    return targets;
  }
  
  //returns arraylist of all inventory particles
  public ArrayList<Element> getAllInventory ()
  {
    return inventory;
  }
  
  //replaces target particles with newTargets
  public void setAllTargets (ArrayList<Element> newTargets)
  {
    targets = newTargets;
  }
  
  //replaces inventory particles with newInventory
  public void setAllInventory (ArrayList<Element> newInventory)
  {
    inventory = newInventory;
  }
  
  //returns the grid of the panel
  public GameGrid getGameGrid()
  {
    return grid;
  }
  
  public ImageIcon getWallpaper()
  {
    return wallpaper;
  }
}