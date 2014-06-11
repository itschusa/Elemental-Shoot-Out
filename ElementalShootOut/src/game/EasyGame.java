package game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The EasyGame class is a screen that represents the Easy Level.
 * The gameplay involves shooting stable atoms at stable atoms or neutrons at unstable atoms.
 * The first interaction simulates nuclear fusion, and the second simulates nuclear fission.
 * There are an equal number of both reactions.
 * There are no obstacles.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 21 2014. (extends panel, uses paintComponent)
 * @version 1.1, May 22 2014. (extends levelscreen, updates!)
 * @version 1.2, May 26 2014. (Modified all code involving 'player' to fit changes to LevelScreen.)
 * @version 1.3, May 27 2014. (Modified code for updating the inventory and targets. Actually shoots and interacts! + comment lines)
 * @version 1.4, May 28 2014. (Temporary code to print points in the interactions pane.)
 * @version 1.5, May 29 2014. (JavaDoc, removed KeyOverrides method - was protected, don't know what it does)
 * @version 1.6, May 31 2014. (Creates equal number of stable and unstable so game is winnable, creates full inventory, added win/lose methods)
 * @version 1.7, June 2 2014. (More JavaDoc)
 * @version 1.8, June 3 2014. (Modifications due to location class constructor change, removes instead of sets location to null, two keys at once)
 * @version 1.9, June 4 2014. (Modified win and lose screens, once a player wins, they will be taken to the next level)
 * @version 1.10, June 5 2014. (Sends tempPoints to super, JavaDoc is current)
 * @version 2.0, June 8 2014. (Displays facts.)
 * @version 2.1, June 10 2014. (JavaDoc)
 */
public class EasyGame extends LevelScreen
{        
  /**
   * instructionImage - ImageIcon - Stores the image that explains how to play the game to the user.
   */
  private ImageIcon instructionImage = new ImageIcon ("Images/EasyGame.png");;
  /**
   * end - boolean - Stores whether the user has won the level.
   */
  private boolean end = false;
  
  /**
   * Creates a new screen that represents the easy level.
   * It creates a random list of targets and user inventory.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory object that stores the current screen.
   * @param newTargets - ArrayList<GameParticle> - The new list of targets.
   * @param newInventory - ArrayList<GameParticle> - The new list of inventory particles.
   * @param name - String - Temporarily stores the names of the elements to create.
   * @param count - int - Stores the number of unstable or neutrons elements.
   * @param count2 - int - Stores the number of stable elements.
   * @param row - int - Stores the current row.
   * @param col - int - Stores the current column.
   */
  public EasyGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    
    ArrayList<GameParticle> newTargets = new ArrayList<GameParticle>();
    ArrayList<GameParticle> newInventory = new ArrayList <GameParticle>();
    String name = "Stable";
    int count = 0;
    int count2 = 0;
    
    //set targets randomly
    for (int row = 1; row < 4; row++)
    {
      for (int col = 1; col < 13; col++)
      {
        if ((Math.random() < 0.5 && count < 18) || count2 > 17)
        {
          name = "Unstable";
          count++;
        }
        else
        {
          name = "Stable";
          count2++;
        }
        newTargets.add (new GameParticle(name, new Location(col, row, false), 0, 1));
      }
    }
    
    //reset count and count2
    count = 0;
    count2 = 0;
    
    //set inventory randomly
    for (int col = 1; col < 37; col++)
    {
      if ((Math.random() < 0.5 && count < 18) || count2 > 17)
      {
        name = "Neutron";
        count++;
      }
      else
      {
        name = "Stable";
        count2++;
      }
      newInventory.add (new GameParticle(name, new Location (col, 10, false), 0, 1));      
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
   * First, the player and shooting is updated.
   * Then, target-inventory interaction is updated. If there is any target directly infront of an inventory particle, interaction has occurred.
   * Either both are removed or only one is removed, depending on whether the user shot at the correct target.
   * Points are also updated.
   * Finally, all elements with no locations are removed, and all elements are updated.
   * 
   * @param x - int - Increments through for loop.
   * @param inv - GameParticle - Stores the temporary inventory elements.
   * @param tar - GameParticle - Stores the temporary target elements.
   * @param index - int - Stores the index of the target in the same location as the current inventory.
   */
  public void onUpdate ()
  {
    updateShoot ();
    
    //checks for target-inventory interaction
    for (int x = 0; x < getAllInventory().size(); x++)
    {
      //gets the inventory element
      GameParticle inv = getAllInventory().get(x);
      
      //if the location exists
      if(inv.getLocation() != null)
      {
        //get the index of the target
        int index = getTargetIndex(new Location(inv.getLocation().getXCoord(), inv.getLocation().getYCoord(), true));
        
        //if there is a target
        if (index != -1)
        {
          //get targer
          GameParticle tar = getAllTargets().get(index);
          
          //stable and stable || unstable and neutron <-- remove both inventory and target
          if (inv.getName().equals(tar.getName()) || inv.getName().equals("Neutron") && tar.getName().equals("Unstable"))
          {
            getAllInventory().remove(x);
            getAllTargets().remove(index);
            getPlayer().addPoints (10);
            setTempPoints (10);
            
            if (getPlayer().getCurrentPoints() > 0 && getPlayer().getCurrentPoints() %60 == 0)
              getScreenFactory().getGame().getMap().incrementCounter();
          }
          //if wrong target, remove the inventory
          else
          {
            getAllInventory().remove(x);
            getPlayer().removePoints(5);
            setTempPoints (-5);
          }
        }
      }
    }
    
    removeNonexistentInventory ();  
    updateElements();
  }
  
  /**
   * Displays the easy level on the JPanel.
   * It draws the wallpaper, tutorial, player (launcher), inventory, targets and win/lose screen, respectively.
   * If the inventory and targets are all removed, and the game has not been won before, the win screen is displayed.
   * Otherwise, if only the inventory is used up, the gameOver screen is displayed.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   * @param x - int - Increments through for loop.
   */
  public void onDraw (Graphics2D twoDimensional)
  {
    super.onDraw (twoDimensional);
    
    //tutorial
    if (getAllTargets().size() == 36)
      twoDimensional.drawImage (instructionImage.getImage(), -100, 0, instructionImage.getImageObserver());
    
    //game over or win
    if (getAllInventory().size() == 0)
    {
      if (!end && getAllTargets().size() == 0)
      {
        end = true;
        getScreenFactory().win (2);
      }
      else
      {
        if (getAllTargets().size() != 0)
            gameOver (twoDimensional);
      }
    }
  }
}