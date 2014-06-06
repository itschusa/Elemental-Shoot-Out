import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The DifficultGame class represents the screen for the difficult level.
 * The gameplay involves shooting anions (negative ions) at cations (positive ions) and vice versa to create ionic compounds.
 * All the targets and inventory are set randomly, so the level is very unlikely to be won.
 * 
 * @author Anqi Wu
 * @version 1.0, May 31 2014. (Creates the game with difficult particles. No win/game over screen)
 * @version 1.1, June 2 2014. (Full JavaDoc)
 * @version 1.2, June 3 2014. (Modifications due to location class constructor change, removes instead of sets location to null, two keys at once)
 * @version 1.3, June 4 2014. (Added win and lose screens)
 * @version 1.4, June 5 2014. (Added more targets and inventory, current JavaDoc)
 */
public class DifficultGame extends LevelScreen
{          
  /**
   * gameOverImage - ImageIcon - Stores the game over screen.
   */
  private ImageIcon gameOverImage = new ImageIcon ("../Images/GameOver2.png");
  /**
   * end - boolean - Stores whether the user has won the level.
   */
  private boolean end = false;
  
  /**
   * Constructs a difficult level screen. This creates a random list of targets and inventory with charges from -3 to +3.
   * Acid clouds are also created in the last two rows of targets.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * @param newTargets - ArrayList<GameParticle> - Stores the temporary targets.
   * @param newInventory - ArrayList<GameParticle> - Stores the temporary inventory.
   * @param name - String - Stores the temporary names of the elements.
   * @param charge - int - Stores the temporary charge of the element.
   * @param element - int - Stores the temporary index of the current element.
   * @param row - int - Stores the current row of the location to set for the elements.
   * @param col - int - Stores the current column of the location to set for the elements.
   */
  public DifficultGame (ScreenFactory screenFactory)
  {
    //LevelScreen
    super(screenFactory);
    
    ArrayList<GameParticle> newTargets = new ArrayList<GameParticle>();
    ArrayList<GameParticle> newInventory = new ArrayList <GameParticle>();
    String name = "";
    int charge, element = 0;
    
    //set targets randomly
    for (int row = 1; row < 6; row++)
    {
      for (int col = 1; col < 13; col++)
      {
        charge = (int) (Math.random()*3)+1;
        
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
        newTargets.add (new GameParticle(name, new Location(col, row, false), charge, 3));
      }
    }
    
    //sets the inventory randomly
    for (int col = 1; col < 61; col++)
    {
      charge = -((int) (Math.random()*3))-1;
      
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
      newInventory.add (new GameParticle(name, new Location(col, 10, false), charge, 3));
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
   * Updates the elements currently stored in the difficult level.
   * It first updates the player/launcher movement.
   * If the up key is pressed, the first element of the inventory is moved up to where the launcher is and canMove is set to true.
   * The inventory will then be shifted to the left.
   * Then, target-inventory interaction is updated. If there is any target in the same location as an inventory particle, interaction has occurred.
   * Either both are removed or only one is removed, depending on whether the user shot at the correct target.
   * Points are also updated. 10 points are added if the charges add to 0. 5 points are deducted if they do not add to 0.
   * Finally, all elements with no locations are removed, and all elements are updated.
   * 
   * @param index - int - Stores the index of the element at the location with row 10 and column 1.
   * @param dart - GameParticle - Stores the temporary element to be shot with.
   * @param x - int - Increments through for loop.
   * @param inv - GameParticle - Stores the temporary inventory elements.
   * @param tar - GameParticle - Stores the temporary target elements.
   * @param inv - ArrayList<GameParticle> - Stores all inventory with location not equal to null.
   */
  public void onUpdate ()
  {
    //if key pressed
    if (GameWindow.movement != 0 || GameWindow.movement2 != 0)
    {
      //update player
      getPlayer().update(GameWindow.movement);
      
      //if pressed up
      if (GameWindow.movement2 == 38)
      {
        //get index of element of the first inventory slot
        int index = getInventoryIndex(new Location(1, 10, false));
        
        //if there is something there
        if (index != -1)
        {
          //get element that is shot and change its location to 1 row in front of player
          GameParticle dart = getAllInventory().get(index);
          dart.setLocation (new Location(getPlayer().getLocation().getColumn(), getPlayer().getLocation().getRow()-1, false));
          dart.setCanMove(true);
          
          //shift the remaining inventory 1 column left
          for (int x = index+1;x<getAllInventory().size();x++)
          {
            getAllInventory().get(x).setShift(true);
          }
        }
      }
      
      //reset key
      GameWindow.movement = 0;
      GameWindow.movement2 = 0;
    }
    
    //checks for target-inventory interaction
    for (int x = 0; x < getAllInventory().size(); x++)
    {
      //gets the inventory element
      GameParticle inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation()!= null)
      {
        //get the index of the target
        int index = getTargetIndex(new Location(inv.getLocation().getXCoord(), inv.getLocation().getYCoord(), true));
        
        //if there is a target
        if (index != -1)
        {
          //get target
          GameParticle tar = getAllTargets().get(index);
          System.out.println ("Inv: "+inv.getCharge());
          System.out.println ("Tar: "+tar.getCharge());
          
          if (inv.getCharge() + tar.getCharge() == 0)
          {
            getAllInventory().remove(x);
            getAllTargets().remove(index);
            getPlayer().addPoints (10);
            setTempPoints(10);
          }
          //if wrong target, remove the inventory
          else
          {
            getAllInventory().remove(x);
            getPlayer().removePoints(5);
            setTempPoints(-5);
          }
        }
      }
    }
    
    //removes inventory that have no location from the arraylist
    ArrayList<GameParticle> inv = new ArrayList<GameParticle>();
    for (int x = 0; x < getAllInventory().size(); x++)
    {
      if (getAllInventory().get(x).getLocation() != null)
        inv.add (getAllInventory().get(x));
    }
    
    //sets the new inv
    setAllInventory (inv);
    
    //update targets
    for (int x = 0; x < getAllTargets().size(); x++)
      getAllTargets().get(x).update();
    
    //update inventory
    for (int x = 0; x < getAllInventory().size(); x++)
      getAllInventory().get(x).update();
  }
  
  /**
   * Draws the game over screen. The game loses focus, so the user cannot move the launcher.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  private void gameOver (Graphics2D twoDimensional)
  {
    getScreenFactory().loseFocus();
    twoDimensional.drawImage (gameOverImage.getImage(), 0, 0, gameOverImage.getImageObserver());
  }
  
  /**
   * Displays the difficult level on the Panel.
   * It draws the wallpaper, player (launcher), inventory and targets, respectively.
   * If the inventory and targets are all removed, and the game has not been won before, the win screen is displayed.
   * Otherwise, if only the inventory is used up, the gameOver screen is displayed.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   * @param x - int - Increments through for loop.
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
    for (int x = 0; x < getAllTargets().size(); x++)
      getAllTargets().get(x).draw(twoDimensional);
    
    //game over or win
    if (getAllInventory().size() == 0)
    {
      if (!end && getAllTargets().size() == 0)
      {
        end = true;
        getScreenFactory().win (4);
      }
      else
      {
        if (getAllTargets().size() != 0)
          gameOver (twoDimensional);
      }
    }
  }  
}