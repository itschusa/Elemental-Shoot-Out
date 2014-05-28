import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @author baseball435
 * @version 1.0, May 21, 2014. (Methods of the LevelMap class)
 * @version 1.1, May 22, 2014. (Moved all methods over to this class, added getWallpaper method, extends Screen)
 * @version 1.2, May 26 2014. (Instantiates CurrentPlayer, added related methods.)
 * @version 1.3, May 27, 2013. (getInventoryIndex works! +changed from get element to get index)
 */
public class LevelScreen extends Screen
{
  //the target particles
  private ArrayList <Element> targets = new ArrayList <Element>();
  //the inventory particles
  private ArrayList <Element> inventory = new ArrayList <Element>();
  private ImageIcon wallpaper = new ImageIcon ("WallpaperGame.png");
  private CurrentPlayer player = new CurrentPlayer("Launcher", new Location (6, 9));
  
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
  public int getTargetIndex (Location location)
  {
    for (int x = 0;x<targets.size();x++)
      if (targets.get(x).getLocation()!=null && targets.get(x).getLocation().getRow() == location.getRow() && targets.get(x).getLocation().getColumn() == location.getColumn())
        return x;
    return -1;
  }
  
  public void setTarget (Element newElement, int index)
  {
    targets.set(index, newElement);
  }
  
  //returns the inventory particles at location in parameters, returns null if not found
  public int getInventoryIndex (Location location)
  {
    for (int x=0;x<inventory.size();x++)
    {
      if (inventory.get(x).getLocation()!=null && inventory.get(x).getLocation().getRow() == location.getRow() && inventory.get(x).getLocation().getColumn() ==location.getColumn())
        return x;
    }
    return -1;
  }
  
  
  public void setInventory (Element newElement, int index)
  {
    inventory.set(index, newElement);
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
  
  public ImageIcon getWallpaper()
  {
    return wallpaper;
  }
  
  public CurrentPlayer getPlayer()
  {
    return player;
  }
}