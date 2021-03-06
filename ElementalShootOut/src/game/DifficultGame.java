package game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The DifficultGame class represents the screen for the difficult level.
 * The gameplay involves shooting anions (negative ions) at cations (positive ions) and vice versa to create ionic compounds.
 * All charges can be matched up exactly.
 * 
 * @author Anqi Wu
 * @version 1.0, May 31 2014. (Creates the game with difficult particles. No win/game over screen)
 * @version 1.1, June 2 2014. (Full JavaDoc)
 * @version 1.2, June 3 2014. (Modifications due to location class constructor change, removes instead of sets location to null, two keys at once)
 * @version 1.3, June 4 2014. (Added win and lose screens)
 * @version 1.4, June 5 2014. (Added more targets and inventory, current JavaDoc)
 * @version 1.5, June 8 2014. (Added absorbers)
 * @version 2.0, June 8 2014. (Displays facts.)
 * @version 2.1, June 10 2014. (JavaDoc)
 * @version 2.2, June 11 2014. (Fixed win screen)
 */
public class DifficultGame extends LevelScreen
{          
  /**
   * obstacles - ArrayList<Obstacle> - Stores the absorber objects.
   */
  private ArrayList <Obstacle> obstacles = new ArrayList<Obstacle> ();
  /**
   * winImage - ImageIcon - Stores the win screen.
   */
  private ImageIcon winImage = new ImageIcon ("Images/Win3.png");
  
  /**
   * Constructs a difficult level screen. This creates a random list of targets and inventory with charges from -3 to +3.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * 
   * @param newTargets - ArrayList<GameParticle> - Stores the temporary targets.
   * @param newInventory - ArrayList<GameParticle> - Stores the temporary inventory.
   * @param name - String - Stores the temporary names of the elements.
   * @param charge - int - Stores the temporary charge of the element.
   * @param count - int - Stores the number of charge 1/-1 elements.
   * @param count2 - int - Stores the number of charge 2/-2 elements.
   * @param count3 - int - Stores the number of charge 3/-3 elements.
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
    int charge = 0;
    int element = 0;
    int count = 0;
    int count2 = 0;
    int count3 = 0;
    
    //set targets randomly
    for (int row = 1; row < 6; row++)
    {
      for (int col = 1; col < 13; col++)
      {
        charge = (int) (Math.random()*3)+1;
        
        if (count < 20 && (charge == 1 || count2 > 19 || count3 > 19))
        {
          charge = 1;
          element = (int)(Math.random()*Database.chargePositiveOne.length);
          name = Database.chargePositiveOne[element];
          count++;
        }
        else if (count2 < 20 && (charge == 2 || count > 19 || count3 > 19))
        {
          charge = 2;
          element = (int)(Math.random()*Database.chargePositiveTwo.length);
          name = Database.chargePositiveTwo[element];
          count2++;
        }
        else
        {
          if (count3 < 20 && (charge == 3 || count > 19 || count2 > 19))
          {
            charge = 3;
            element = (int)(Math.random()*Database.chargePositiveThree.length);
            name = Database.chargePositiveThree[element];
            count3++;
          }
        }
        
        newTargets.add (new GameParticle(name, new Location(col, row, false), charge, 3));
      }
    }
    
    count = 0;
    count2 = 0;
    count3 = 0;
    
    //sets the inventory randomly
    for (int col = 1; col < 61; col++)
    {
      charge = -((int) (Math.random()*3))-1;
      
      if (count < 20 && (charge == -1 || count2 > 19 || count3 > 19))
      {
        charge = -1;
        element = (int)(Math.random()*Database.chargeNegativeOne.length);
        name = Database.chargeNegativeOne[element];
        count++;
      }
      else if (count2 < 20 && (charge == -2 || count > 19 || count3 > 19))
      {
        charge = -2;
        element = (int)(Math.random()*Database.chargeNegativeTwo.length);
        name = Database.chargeNegativeTwo[element];
        count2++;
      }
      else
      {
        if (count3 < 20 && (charge == -3 || count > 19 || count2 > 19))
        {
          charge = -3;
          element = (int)(Math.random()*Database.chargeNegativeThree.length);
          name = Database.chargeNegativeThree[element];
          count3++;
        }
      }
      
      newInventory.add (new GameParticle(name, new Location(col, 10, false), charge, 3));
    }
    
    //add obstacles
    obstacles.add(new Obstacle ("Absorber", new Location (4, 1, false), -4, false, 3));
    obstacles.add(new Obstacle ("Absorber", new Location (9, 2, false), -4, true, 3));
    obstacles.add(new Obstacle ("Absorber", new Location (5, 4, false), -4, false, 3));
    obstacles.add(new Obstacle ("Absorber", new Location (7, 4, false), -4, true, 3));
    obstacles.add(new Obstacle ("Absorber", new Location (6, 5, false), -4, false, 3));
    obstacles.add(new Obstacle ("Absorber", new Location (2, 5, false), -4, true, 3));
    
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
   * First, the player and shooting is updated.
   * Then, target-inventory interaction is updated. If there is any target in the same location as an inventory particle, interaction has occurred.
   * Either both are removed or only one is removed, depending on whether the user shot at the correct target.
   * Points are also updated. 10 points are added if the charges add to 0. 5 points are deducted if they do not add to 0.
   * Finally, all elements with no locations are removed, and all elements are updated.
   * 
   * @param index - int - Stores the index of the target at the same location as the current inventory.
   * @param index2 - int - Stores the index of the obstacle at the same location as the current inventory.
   * @param dart - GameParticle - Stores the temporary element to be shot with.
   * @param x - int - Increments through for loop.
   * @param inv - GameParticle - Stores the temporary inventory elements.
   * @param tar - GameParticle - Stores the temporary target elements.
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
    for (int x = 0; x < getAllInventory().size(); x++)
    {
      //gets the inventory element
      GameParticle inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation()!= null)
      {
        //get the index of the target
        int index = getTargetIndex(new Location(inv.getLocation().getXCoord(), inv.getLocation().getYCoord(), true));
        int index2 = getObstacleIndex (new Location(inv.getLocation().getColumn(), inv.getLocation().getRow(), false));
        
        if (index2 != -1)
        {
          getAllInventory().remove(x);
          if (obstacles.get(index2).getGlow())
          {
            getPlayer().removePoints(20);
            setTempPoints(-20);
          }
          else
          {
            getPlayer().removePoints(10);
            setTempPoints(-10);
            obstacles.get(index2).glow();
          }
        }
        else
        {
          //if there is a target
          if (index != -1)
          {
            //get target
            GameParticle tar = getAllTargets().get(index);
            
            if (inv.getCharge() + tar.getCharge() == 0)
            {
              getAllInventory().remove(x);
              getAllTargets().remove(index);
              getPlayer().addPoints (15);
              
              if (getPlayer().getCurrentPoints() > 0 && getPlayer().getCurrentPoints() %60 == 0)
                getScreenFactory().getGame().getMap().incrementCounter();
              
              setTempPoints(15);
            }
            //if wrong target, remove the inventory
            else
            {
              getAllInventory().remove(x);
              getPlayer().removePoints(10);
              setTempPoints(-10);
            }
          }
        }
      }
    }
    
    removeNonexistentInventory();
    updateElements();
  }
  
  /**
   * Returns the obstacle at the location in the parameter.
   * It returns null if not found.
   * 
   * @param location - Location - The location of the obstacle to find.
   * @param x - int - Increments through for loop.
   * @return An int that represents the index of the obstacle.
   */
  public int getObstacleIndex (Location location)
  {
    for (int x = 0; x <obstacles.size(); x++)
    {
      if (obstacles.get(x).getLocation()!= null && obstacles.get(x).getLocation().getRow() == location.getRow() && obstacles.get(x).getLocation().getColumn() == location.getColumn())
        return x;
    }
    
    return -1;
  }
  
  /**
   * Draws the win screen. The game loses focus, so the user cannot move the launcher.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  public void win(Graphics2D twoDimensional)
  {
    getScreenFactory().loseFocus();
    twoDimensional.drawImage (winImage.getImage(), 0, 0, winImage.getImageObserver());
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
    super.onDraw(twoDimensional);
    
    for (int x = 0; x < obstacles.size(); x++)
      obstacles.get(x).draw(twoDimensional);
    
    //game over or win
    if (getAllInventory().size() == 0)
    {
      if (getAllTargets().size() == 0)
        win (twoDimensional);
      else
      {
        if (getAllTargets().size() != 0)
          gameOver (twoDimensional);
      }
    }
  }  
}