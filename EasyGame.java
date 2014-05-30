import java.util.ArrayList;
import java.awt.*;

/**
 * The EasyGame class is a screen that draws the easy level.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 21 2014. (extends panel, uses paintComponent)
 * @version 1.1, May 22, 2014. (extends levelscreen, updates!)
 * @version 1.2, May 26 2014. (Modified all code involving 'player' to fit changes to LevelScreen.)
 * @version 1.3, May 27, 2014. (Modified code for updating the inventory and targets. Actually shoots and interacts! + comment lines)
 * @version 1.4, May 28, 2014. (Temporary code to print points in the interactions pane.)
 * @version 1.5, May 29, 2014. (JavaDoc, removed KeyOverrides method - was protected, don't know what it does)
 */
public class EasyGame extends LevelScreen
{        
  /**
   * Creates a new screen that represents the easy level.
   * It creates a random list of targets and user inventory.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * @param newTargets - ArrayList<Element> - The new list of targets.
   * @param name - String - Temporarily stores the names of the elements to create.
   * @param newInventory - ArrayList<Element> - The new list of inventory particles.
   */
  public EasyGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    
    ArrayList<Element> newTargets = new ArrayList<Element>();
    String name = "Stable";
    
    //set targets randomly
    for (int row = 1; row<4;row++)
    {
      for (int col=1;col<13;col++)
      {
        if (Math.random()<0.5)
          name = "Unstable";
        newTargets.add (new EasyParticle(name, new Location(col, row)));
        name = "Stable";
      }
    }
    
    //set inventory randomly
    ArrayList<Element> newInventory = new ArrayList <Element>();
    for (int col = 1; col<13;col++)
    {
      name = "Stable";
      if (Math.random()<0.5)
        name = "Neutron";
      newInventory.add (new EasyParticle(name, new Location (col, 10)));      
    }
    
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
   * Updates the elements currently stored in the easy level.
   * It first updates the player/launcher movement.
   * If the up key is pressed, the first element of the inventory is moved up to where the launcher is and canMove is set to true.
   * The inventory will then be shifted to the left.
   * Then, target-inventory interaction is updated. If there is any target directly infront of an inventory particle, interaction has occurred.
   * Either both are removed or only one is removed, depending on whether the user shot at the correct target.
   * Points are also updated.
   * Finally, all elements with no locations are removed, and all elements are updated.
   */
  public void onUpdate ()
  {
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
          Element dart = getAllInventory().get(index);
          dart.setLocation (new Location(getPlayer().getLocation().getColumn(), getPlayer().getLocation().getRow()-1));
          dart.setCanMove(true);
          dart.setCurrentStep (10);
          setInventory (dart, index);
          
          Element inv;
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
      Element inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation()!=null)
      {
        //get the index of the target directly in front of it
        int index = getTargetIndex(new Location(inv.getLocation().getColumn(), inv.getLocation().getRow()-1));
        
        //if there is a target
        if (index!=-1)
        {
          //get targer
          Element tar = getAllTargets().get(index);
          //stable and stable || unstable and neutron <-- remove both inventory and target
          if (inv.getName().equals(tar.getName())||inv.getName().equals("Neutron") && tar.getName().equals("Unstable"))
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
    
    //removes targets that have no location from the arraylist
    ArrayList<Element> tar = new ArrayList<Element>();
    for (int x = 0;x<getAllTargets().size();x++)
    {
      if (getAllTargets().get(x).getLocation() != null)
        tar.add (getAllTargets().get(x));
    }
    
    //removes inventory that have no location from the arraylist
    ArrayList<Element> inv = new ArrayList<Element>();
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
    {
      getAllTargets().get(x).update();
    }
    
    //update inventory
    for (int x = 0; x<getAllInventory().size();x++)
    {
      getAllInventory().get(x).update();
    }
  }
  
  public void onDraw (Graphics2D twoDimensional)
  {
    //draw background
    twoDimensional.drawImage (getWallpaper().getImage(), 0, 0, getWallpaper().getImageObserver()); 
    
    //draw player
    getPlayer().draw(twoDimensional);
    
    //draw inventory
    for (int x = 0; x<getAllInventory().size();x++)
    {
      getAllInventory().get(x).draw(twoDimensional);
    }
    
    //draw targets
    for (int x=0;x<getAllTargets().size();x++)
    {
      getAllTargets().get(x).draw(twoDimensional);
    }
  }
}