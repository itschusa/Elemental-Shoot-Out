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
 * @version 1.3, May 27, 2014. (getInventoryIndex works! +changed from get element to get index)
 * @version 1.4, June 5, 2014. (Added tempPoints variable and method, JavaDoc)
 */
public abstract class LevelScreen extends Screen
{
  /**
   * targets - ArrayList<GameParticle> - Stores all the targets.
   */
  private ArrayList <GameParticle> targets = new ArrayList <GameParticle>();
  /**
   * inventory - ArrayList<GameParticle> - Stores all the inventory.
   */
  private ArrayList <GameParticle> inventory = new ArrayList <GameParticle>();
  /**
   * wallpaper - ImageIcon - Stores the wallpaper image.
   */
  private ImageIcon wallpaper = new ImageIcon ("../Images/WallpaperGame.png");
  /**
   * player - CurrentPlayer - Stores the launcher.
   */
  private CurrentPlayer player = new CurrentPlayer("Launcher", new Location (6, 9, false));
  /**
   * tempPoints - int - The temporary number of points the user lost/won.
   */
  private int tempPoints = 0;
  /**
   * gameOverImage - ImageIcon - Stores the game over screen.
   */
  private ImageIcon gameOverImage = new ImageIcon ("../Images/GameOver2.png");
  
  /**
   * Constructs a new LevelScreen with the specified ScreenFactory.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory.
   */
  public LevelScreen (ScreenFactory screenFactory)
  {
    super(screenFactory);
  }
  
  /**
   * Does nothing when first created.
   * Override to do something.
   */
  public abstract void onCreate ();
  
  /**
   * Does nothing when updating.
   * Override to do something.
   */
  public abstract void onUpdate ();
  
  /**
   * Draws nothing when drawing.
   * Override to draw something.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  public abstract void onDraw (Graphics2D twoDimensional);
  
  /**
   * Updates the targets and inventory.
   * 
   * @param x - int - Increments for the for loop.
   */
  public void updateElements ()
  {
    //update targets
    for (int x = 0; x < getAllTargets().size(); x++)
      getAllTargets().get(x).update();
    
    //update inventory
    for (int x = 0; x < getAllInventory().size(); x++)
      getAllInventory().get(x).update();
  }
  
  /**
   * Removes all inventory that has a null location.
   * 
   * @param x - int - Increments for the for loop.
   */
  public void removeNonexistentInventory ()
  {
    for (int x = 0; x < getAllInventory().size(); x++)
    {
      if (getAllInventory().get(x).getLocation() == null)
      {
        getAllInventory().remove (getAllInventory().get(x));
        x--;
      }
    }
  }
  
  /**
   * Updates the elements currently stored in the easy level.
   * It first updates the player/launcher movement.
   * If the up key is pressed, the first element of the inventory is moved up to where the launcher is and canMove is set to true.
   * The inventory will then be shifted to the left.
   * 
   * @param index - int - Stores the index of the element at the location with row 10 and column 1.
   * @param dart - GameParticle - Stores the temporary element to be shot with.
   * @param x - int - Increments through for loop.
   */
  public void updateShoot ()
  {
    if (GameWindow.movement != 0 || GameWindow.movement2 != 0)
    {
      //update player
      getPlayer().update(GameWindow.movement);
      
      //if pressed up
      if (GameWindow.movement2 == 38)
      {
        //get index of element of the first inventory slot
        int index = getInventoryIndex (new Location(1, 10, false));
        
        //if there is something there
        if (index != -1)
        {
          //get element that is shot and change its location to 1 row in front of player
          GameParticle dart = getAllInventory().get(index);
          dart.setLocation (new Location(getPlayer().getLocation().getColumn(), getPlayer().getLocation().getRow()-1, false));
          dart.setCanMove (true);
          
          //shift the remaining inventory 1 column left
          for (int x = index+1; x < getAllInventory().size(); x++)
          {
            getAllInventory().get(x).setShift (true);
          }
        }
      }
      
      //reset key
      GameWindow.movement = 0;
      GameWindow.movement2 = 0;
    }
  }
  
  /**
   * Draws the game over screen. The game loses focus, so the user cannot move the launcher.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  public void gameOver (Graphics2D twoDimensional)
  {
    getScreenFactory().loseFocus();
    twoDimensional.drawImage (gameOverImage.getImage(), 0, 0, gameOverImage.getImageObserver());
  }
  
  /**
   * Sets the temporary amount of points to display.
   * 
   * @param int - points - The amount of points to set.
   */
  public void setTempPoints (int points)
  {
    tempPoints = points;
  }
  
  /**
   * Gets the temporary amount of points stored.
   */
  public int getTempPoints ()
  {
    return tempPoints;
  }
  
  /**
   * Returns the index of the target at the specified location in the parameter.
   * If no target has that location, -1 is returned.
   * 
   * @param location - Location - The location of the target to get.
   * @param x - int - Increments for the for loop.
   */
  public int getTargetIndex (Location location)
  {
    for (int x = 0; x < targets.size(); x++)
      if (targets.get(x).getLocation() != null && targets.get(x).getLocation().getYCoord() == location.getYCoord() && targets.get(x).getLocation().getXCoord() == location.getXCoord())
      return x;
    
    return -1;
  }
  
  /**
   * Returns the index of the inventory at the specified location in the parameter.
   * If no inventory has that location, -1 is returned.
   * 
   * @param location - Location - The location of the inventory to get.
   * @param x - int - Increments for the for loop.
   */
  public int getInventoryIndex (Location location)
  {
    for (int x = 0; x < inventory.size(); x++)
    {
      if (inventory.get(x).getLocation() != null && inventory.get(x).getLocation().getRow() == location.getRow() && inventory.get(x).getLocation().getColumn() == location.getColumn())
        return x;
    }
    
    return -1;
  }
  
  /**
   * Returns the ArrayList of all targets elements.
   */
  public ArrayList<GameParticle> getAllTargets ()
  {
    return targets;
  }
  
  /**
   * Returns the ArrayList of all inventory elements.
   */
  public ArrayList<GameParticle> getAllInventory ()
  {
    return inventory;
  }
  
  /**
   * Replaces targets with the targets specified in the parameter.
   * 
   * @param newTargets - ArrayList<GameParticle> - The new ArrayList of targets.
   */
  public void setAllTargets (ArrayList<GameParticle> newTargets)
  {
    targets = newTargets;
  }
  
  /**
   * Replaces inventory with the inventory specified in the parameter.
   * 
   * @param newInventory - ArrayList<GameParticle> - The new ArrayList of inventory.
   */
  public void setAllInventory (ArrayList<GameParticle> newInventory)
  {
    inventory = newInventory;
  }
  
  /**
   * Returns the wallpaper of the LevelScreen.
   */
  public ImageIcon getWallpaper()
  {
    return wallpaper;
  }
  
  /**
   * Returns the CurrentPlayer of the LevelScreen.
   */
  public CurrentPlayer getPlayer()
  {
    return player;
  }
}