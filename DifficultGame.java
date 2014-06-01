import java.util.ArrayList;
import java.awt.*;

/**
 * The DifficultGame class represents the screen for the difficult level.
 * It involves ionic compounds.
 * 
 * @author Anqi Wu
 * @version 1.0, May 31, 2014. (Creates the game with difficult particles. No win/game over screen)
 */
public class DifficultGame extends LevelScreen
{          
  public DifficultGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    
    ArrayList<Element> newTargets = new ArrayList<Element>();
    String name = "";
    
    //set targets randomly
    for (int row = 1; row<4;row++)
    {
      for (int col=1;col<13;col++)
      {
        int charge = (int) (Math.random()*3)+1;
        int element;
        
        if (charge == 1)
        {
          element = (int)(Math.random()*Database.chargePositiveOne.length);
          name = Database.chargePositiveOne[element];
        }
        else if (charge == 2)
        {
          element = (int)(Math.random()*Database.chargePositiveTwo.length);
          name = Database.chargePositiveTwo[element];
        }
        else
        {
          element = (int)(Math.random()*Database.chargePositiveThree.length);
          name = Database.chargePositiveThree[element];
        }

        System.out.println (name);
        newTargets.add (new DifficultParticle(name, new Location(col, row),charge));
      }
    }
    
    ArrayList<Element> newInventory = new ArrayList <Element>();
    for (int col = 1;col<37;col++)
    {
        int charge = -((int) (Math.random()*3))-1;
        int element;
        
        if (charge == -1)
        {
          element = (int)(Math.random()*Database.chargeNegativeOne.length);
          name = Database.chargeNegativeOne[element];
        }
        else if (charge == -2)
        {
          element = (int)(Math.random()*Database.chargeNegativeTwo.length);
          name = Database.chargeNegativeTwo[element];
        }
        else
        {
          element = (int)(Math.random()*Database.chargeNegativeThree.length);
          name = Database.chargeNegativeThree[element];
        }
        System.out.println (name);
        newInventory.add (new DifficultParticle(name, new Location(col, 10),charge));
    }
            
    //save changes
    super.setAllTargets (newTargets);
    super.setAllInventory (newInventory);
  }
  
  public void onCreate ()
  {
  }
  
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
          //get target
          Element tar = getAllTargets().get(index);
          System.out.println ("Inv: "+inv.getCharge());
          System.out.println ("Tar: "+tar.getCharge());
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
    for (int x = 0; x<12&&x<getAllInventory().size();x++)
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