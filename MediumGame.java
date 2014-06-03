import java.util.ArrayList;
import java.awt.*;

/**
 * The MediumGame class represents the screen for the medium level.
 * It involves acid-base neutralization.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 28, 2014. (alkali metals and hydroxide elements update - repeat of EasyGame)
 * @version 1.1, May 30 2014. (Added code to create and update acid cloud obstacles.)
 * @version 1.2, May 31, 2014 (Creates all inventory, and only displays first 12. No alkaline metals yet.)
 * @version 1.3, June 2, 2014. (JavaDoc)
 * @version 1.4, June 3, 2014. (Added some functionality to acid cloud, eats ions.)
 */
public class MediumGame extends LevelScreen
{        
  /**
   * obstacles - ArrayList<AcidCloud> - Stores the acid cloud objects.
   */
  private ArrayList <AcidCloud> obstacles = new ArrayList<AcidCloud> ();
  
   /**
   * Constructs a medium level screen. This creates a random list of targets and inventory.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * @param newTargets - ArrayList<GameParticle> - Stores the temporary targets.
   * @param name - String - Stores the temporary names of the elements.
   * @param row - int - Stores the current row of the location to set for the elements.
   * @param col - int - Stores the current column of the location to set for the elements.
   * @param element - int - Stores the temporary index of the current element.
   * @param newInventory - Stores the temporary inventory.
   */
  public MediumGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    
    ArrayList<GameParticle> newTargets = new ArrayList<GameParticle>();
    String name = "";
    
    //set targets randomly
    for (int row = 1; row<4;row++)
    {
      for (int col=1;col<13;col++)
      {
        int element = (int) (Math.random()*6);
        name = Database.alkaliMetals[element];
        System.out.println (element +" "+ name);
        newTargets.add (new MediumParticle(name, new Location(col, row),1));
      }
    }
    
    ArrayList<GameParticle> newInventory = new ArrayList <GameParticle>();
    for (int col = 1; col<37;col++)
      newInventory.add (new MediumParticle("Hydroxide", new Location (col, 10),-1));      
    
    obstacles.add(new AcidCloud ("Cloud", new Location (1, 4), -3, true));
    obstacles.add(new AcidCloud ("Cloud", new Location (9, 4), -3, true));
    obstacles.add(new AcidCloud ("Cloud", new Location (8, 5), -3, false));
      
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
   * It first updates the player/launcher movement.
   * If the up key is pressed, the first element of the inventory is moved up to where the launcher is and canMove is set to true.
   * The inventory will then be shifted to the left.
   * Then, target-inventory interaction is updated. If there is any target directly infront of an inventory particle, interaction has occurred.
   * Either both are removed or only one is removed, depending on whether the user shot at the correct target.
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
   * @param wasEaten - boolean - Represents whether or not the "dart" was eaten by an acid cloud. 
   */
  public void onUpdate ()
  {
    boolean wasEaten = false;
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
    
    //if key pressed
    if (GameWindow.movement != 0)
    {
      //update player
      getPlayer().update(GameWindow.movement);
      
      //if pressed up
      if (GameWindow.movement == 38)
      {
        //get index of element of the first inventory slot
        int index = getInventoryIndex(new Location(1,10));
        
        //if there is something there
        if (index != -1)
        {
          //get element that is shot and change its location to 1 row in front of player
          //update immediately (no wait)
          GameParticle dart = getAllInventory().get(index);
          dart.setLocation (new Location(getPlayer().getLocation().getColumn(), getPlayer().getLocation().getRow()-1));
          dart.setCanMove(true);
          dart.setCurrentStep (10);
          setInventory (dart, index);
          
          GameParticle inv;
          //shift the remaining inventory 1 column left
          for (int x = index+1;x<getAllInventory().size();x++)
          {
            inv = getAllInventory().get(x);
            inv.setShift (true);
            setInventory (inv, x);
          }
        }
      }
      //reset key
      GameWindow.movement = 0;
    }
    
    //checks for target-inventory interaction
    for (int x=0;x<getAllInventory().size();x++)
    {
      //gets the inventory element
      GameParticle inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation()!=null)
      {
        //get the index of the target directly in front of it
        int index = getTargetIndex(new Location(inv.getLocation().getColumn(), inv.getLocation().getRow()-1));
        
        //if it hits an acid cloud
        for (int f = 0; f < obstacles.size(); f++)
        {
          if (inv.getLocation().getRow() == obstacles.get(f).getLocation().getRow())
          {
            System.out.println ("Same Row");
            if (inv.getLocation().getColumn() == obstacles.get(f).getLocation().getColumn())
            {
              System.out.println ("Same Column");
              if (inv.getCharge() + obstacles.get(f).getCharge() != 0)
              {
                System.out.println ("-5");
                inv.removeFromGrid();
                getPlayer().removePoints(5);
                setInventory(inv, x);
                System.out.println ("Eaten");
                System.out.println ("Total: "+getPlayer().getCurrentPoints());
                wasEaten = true;
              }//end if: can't neutralize
              else
              {
                
              }//end if: can neutralize
            }//end if: same col
          }//end if: same row
        }
        
        //if there is a target
        if (!wasEaten)
        {
          if (index!=-1)
          {
            //get target
            GameParticle tar = getAllTargets().get(index);
            if (inv.getCharge() + tar.getCharge() == 0)
            {
              System.out.println ("+10");
              inv.removeFromGrid();
              tar.removeFromGrid();
              getPlayer().addPoints (10);
              setInventory (inv, x);
              setTarget(tar, index);
              System.out.println ("Total: "+getPlayer().getCurrentPoints());
            }
            //if wrong target, remove the inventory
            else
            {
              System.out.println ("-5");
              inv.removeFromGrid();
              getPlayer().removePoints(5);
              setInventory(inv, x);
              System.out.println ("Total: "+getPlayer().getCurrentPoints());
            }
          }
        }
      }
    }
    
    //removes targets that have no location from the arraylist
    ArrayList<GameParticle> tar = new ArrayList<GameParticle>();
    for (int x = 0;x<getAllTargets().size();x++)
    {
      if (getAllTargets().get(x).getLocation() != null)
        tar.add (getAllTargets().get(x));
    }
    
    //removes inventory that have no location from the arraylist
    ArrayList<GameParticle> inv = new ArrayList<GameParticle>();
    for (int x = 0;x<getAllInventory().size();x++)
    {
      if (getAllInventory().get(x).getLocation() != null)
        inv.add (getAllInventory().get(x));
    }
    
    //sets the new inv+tar
    setAllTargets (tar);
    setAllInventory (inv);
    
    //update targets
    for (int x=0;x<getAllTargets().size();x++)
      getAllTargets().get(x).update();
    
    //update inventory
    for (int x = 0; x<getAllInventory().size();x++)
      getAllInventory().get(x).update();
  }
  
  /**
   * Displays the medium level on the jpanel.
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
    for (int x = 0; x<12&&x<getAllInventory().size();x++)
      getAllInventory().get(x).draw(twoDimensional);
    
    //draw targets
    for (int x=0;x<getAllTargets().size();x++)
      getAllTargets().get(x).draw(twoDimensional);
    
    for (int x = 0; x < obstacles.size(); x++)
      obstacles.get(x).draw(twoDimensional);
  }
}