import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The MediumGame class represents the screen for the medium level.
 * It involves acid-base neutralization.
 * The player can shoot either alkali metals or ions at the targets.
 * When a player successfully creates a base, it is added to the end of the inventory.
 * The player wins once all obstacles and targets are cleared.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 28 2014. (alkali metals and hydroxide elements update - repeat of EasyGame)
 * @version 1.1, May 30 2014. (Added code to create and update acid cloud obstacles.)
 * @version 1.2, May 31 2014 (Creates all inventory, and only displays first 12. No alkaline metals yet.)
 * @version 1.3, June 2 2014. (JavaDoc)
 * @version 1.4, June 3 2014. (Modifications due to location class constructor change, removes instead of sets location to null, two keys at once)
 * @version 1.5, June 4 2014. (Added win and lose screens, can go to difficult level. Added base particles, no functionality.)
 * @version 1.6, June 4 2014. (Base implementation and having mix and match for alkali and hydroxide, more targets and inventory)
 * @version 1.7, June 5 2014. (Equal number of hydroxide and alkali metals, JavaDoc partially)
 * @version 1.8, June 8 2014. (More KISS)
 */
public class MediumGame extends LevelScreen
{        
  /**
   * obstacles - ArrayList<Obstacle> - Stores the acid cloud objects.
   */
  private ArrayList <Obstacle> obstacles = new ArrayList<Obstacle> ();
  /**
   * end - boolean - Stores whether the user has won the level.
   */
  private boolean end = false;
  
  /**
   * Constructs a medium level screen. This creates a random list of targets and inventory.
   * Acid clouds are also created in the last two rows of targets.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * @param newTargets - ArrayList<GameParticle> - Stores the temporary targets.
   * @param newInventory - Stores the temporary inventory.
   * @param name - String - Stores the temporary names of the elements.
   * @param count - int - Stores the number of hydroxide ions.
   * @param count2 - int - Stores the number of alkali ions.
   * @param which - double - Stores whether to add an alkali ion or a hydroxide ion.
   * @param row - int - Stores the current row of the location to set for the elements.
   * @param col - int - Stores the current column of the location to set for the elements.
   * @param element - int - Stores the temporary index of the current element.
   */
  public MediumGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    
    ArrayList<GameParticle> newTargets = new ArrayList<GameParticle>();
    ArrayList<GameParticle> newInventory = new ArrayList <GameParticle>();
    String name = "";
    int count = 0;
    int count2 = 0;
    double which = 0;
    
    //set targets randomly
    for (int row = 1; row < 5; row++)
    {
      for (int col = 1; col < 13; col++)
      {
        int element = (int) (Math.random()*6);
        which = Math.random ();
        if (which > 0.5 && count < 24 || count2 > 23)
        {
          newTargets.add (new GameParticle("Hydroxide", new Location (col, row, false), -1, 2));
          count++;
        }
        else
        {
          name = Database.alkaliMetals[element];
          newTargets.add (new GameParticle(name, new Location(col, row, false), 1, 2));
          count2++;
        }
      }
    }
    
    //reset count and count2
    count = 0;
    count2 = 0;
    
    for (int col = 1; col < 49; col++)
    {
      int element = (int)(Math.random()*6);
      which = Math.random ();
      if (which > 0.5 && count < 24 || count2 > 23)
      {
        newInventory.add (new GameParticle("Hydroxide", new Location (col, 10, false), -1, 2));
        count++;
      }
      else
      {
        name = Database.alkaliMetals[element];
        newInventory.add (new GameParticle (name, new Location (col, 10, false), 1, 2));
        count2++;
      }
    }
    
    //add obstacles
    obstacles.add(new Obstacle ("Cloud", new Location (1, 4, false), -3, true, 2));
    obstacles.add(new Obstacle ("Cloud", new Location (9, 4, false), -3, true, 2));
    obstacles.add(new Obstacle ("Cloud", new Location (8, 5, false), -3, false, 2));
    
    //save changes
    super.setAllTargets (newTargets);
    super.setAllInventory (newInventory);
  }
  
  /**
   * Empty override from the Screen class.
   */
  public void onCreate ()
  {
  }
  
  /**
   * Updates the elements currently stored in the medium level.
   * It first updates the obstacles.
   * Then, the player and shooting is updated.
   * Obstacle-inventory and target-inventory interaction is updated. 
   * If there is an obstacle in the same location as the inventory particle, either the inventory is removed, or both the inventory and obstacle.
   * The obstacle is only removed when the inventory is a base.
   * If there is any target directly infront of an inventory particle, either both are removed or only one is removed, depending on whether the user shot at the correct target.
   * Points are also updated.
   * Finally, all elements with no locations are removed, and all elements are updated.
   * 
   * @param index - int - Stores the index of the element at the location with row 10 and column 1.
   * @param dart - GameParticle - Stores the temporary element to be shot with.
   * @param inv - GameParticle - Stores the temporary inventory elements.
   * @param x - int - Increments through for loop.
   * @param tar - GameParticle - Stores the temporary target elements.
   * @param tar - ArrayList<GameParticle> - Stores all targets with location not equal to null. 
   * @param inv - ArrayList<GameParticle> - Stores all inventory with location not equal to null.
   */
  public void onUpdate ()
  {  
    //moves the obstacles
    if (!GameWindow.paused)
    {
      for (int x = 0; x < obstacles.size(); x++)
      {
        if (obstacles.get(x).getUpdateCount() < 100)
          obstacles.get(x).setUpdateCount(obstacles.get(x).getUpdateCount() + 1);
        else
        {
          obstacles.get(x).setUpdateCount(0);
          obstacles.get(x).update();
        }
      }
    }
    
    updateShoot();
    
    //checks for target-inventory interaction
    for (int x = 0; x < getAllInventory().size();x++)
    {
      //gets the inventory element
      GameParticle inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation() != null)
      {
        //get the index of the target
        int index = getTargetIndex(new Location(inv.getLocation().getXCoord(), inv.getLocation().getYCoord(), true));
        int index2 = getObstacleIndex (new Location(inv.getLocation().getColumn(), inv.getLocation().getRow(), false));
        
        //if hits an acid cloud        
        if (index2 != -1)
        { 
          if (inv.getCharge() != +3)
          {
            getAllInventory().remove(x);
            getPlayer().removePoints(10);
            setTempPoints(-10);
          }
          else
          {
            obstacles.remove(index2);
            getAllInventory().remove(x);
            getPlayer().addPoints (20);
            setTempPoints(20);
          }
        }
        else
        {
          if (index!=-1)
          {
            //get target
            GameParticle tar = getAllTargets().get(index);
            
            if (inv.getCharge() + tar.getCharge() == 0)
            {
              GameParticle baseParticle = new GameParticle ("Base", new Location (getAllInventory().get(getAllInventory().size()-1).getLocation().getColumn()+1, 10, false), +3, 2);
              getAllInventory().remove (x);
              getAllInventory().add (baseParticle);
              getAllTargets().remove(index);
              getPlayer().addPoints (10);
              setTempPoints(10);
            }
            //if wrong target, remove the inventory
            else
            {
              getAllInventory().remove (x);
              getPlayer().removePoints(5);
              setTempPoints(-5);
            }
          }
        }
      }
    }
    
    removeNonexistentInventory();
    updateElements();
  }
  
  //returns the obstacle at location in parameters, returns null if not found
  public int getObstacleIndex (Location location)
  {
    for (int x = 0; x <obstacles.size(); x++)
      if (obstacles.get(x).getLocation()!= null && obstacles.get(x).getLocation().getRow() == location.getRow() && obstacles.get(x).getLocation().getColumn() == location.getColumn())
      return x;
    
    return -1;
  }
  
  /**
   * Displays the medium level on the JPanel.
   * It draws the wallpaper, player (launcher), inventory, targets and obstacles, respectively.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  public void onDraw (Graphics2D twoDimensional)
  {
    //draw background
    twoDimensional.drawImage (getWallpaper().getImage(), 0, 0, getWallpaper().getImageObserver()); 
    
    //draw player
    getPlayer().draw(twoDimensional);
    
    //draw inventory
    for (int x = 0; x < 12 && x < getAllInventory().size(); x++)
      getAllInventory().get(x).draw(twoDimensional);
    
    //draw targets
    for (int x=0;x<getAllTargets().size();x++)
      getAllTargets().get(x).draw(twoDimensional);
    
    for (int x = 0; x < obstacles.size(); x++)
      obstacles.get(x).draw(twoDimensional);
    
    //game over or win
    if (getAllInventory().size() == 0 || obstacles.size() == 0)
    {
      if (!end && getAllTargets().size() == 0)
      {
        end = true;
        getScreenFactory().win (3);
      }
      else
      {
        if (getAllTargets().size() !=0)
          gameOver (twoDimensional);
      }
    }
  }
}