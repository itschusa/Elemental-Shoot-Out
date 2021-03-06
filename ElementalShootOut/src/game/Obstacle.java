package game;

import javax.swing.*;

/**
 * The "Obstacle" class. This class creates objects to act as obstacles for the medium and difficult levels. 
 * The obstacles move along a predefined path on the game screen. It is, by extension, a GameParticle. 
 * 
 * @author Chusa Nguyen
 * @author Anqi Wu
 * @version 1.0, May 29 2014. 
 * @version 1.1, June 3 2014. (added currentStep (5 step wait), so clouds move slower)
 * @version 1.2, June 5 2014. (JavaDoc is current)
 * @version 2.0, June 8 2014. (Now obstacle instead if acidcloud, added glow for difficult)
 * @version 2.1, June 10 2014. (JavaDoc)
 */
public class Obstacle extends GameParticle
{
  /**
   * steps - int - Counter which keeps track of the number of columns the acid cloud has moved.
   */
  private int steps;
  /**
   * forwards - boolean - Determines whether the acid cloud will move forwards or backwards.
   */
  private boolean forwards;
  /**
   * updateCount - int - Counter which keeps track of the number of times the screen updates.
   */
  private int updateCount = 0;
  /**
   * currentStep - int - Stores the current number of steps after it was updated.
   */
  private int currentStep = 0;
  /**
   * glow - boolean - Stores whether the obstacle should glow.
   */
  private boolean glow;
  
  /**
   * The class constructor. It creates an obstacle object with the specified name, at a specified location 
   * and a given charge, as well as set its initial direction of movement.
   * 
   * @param newName - String - String representation of this object's name.
   * @param newLocation - Location - The location given upon creation. 
   * @param charge - int - The charge of the AcidCloud.
   * @param forwards - boolean - Determines whether the first step will be forwards or backwards.
   * @param level - int - Integer representation of the level difficulty this Obstacle object is being constructed for.
   */
  public Obstacle (String newName, Location newLocation, int charge, boolean forwards, int level)
  {
    super(newName, newLocation,  charge, level);
    this.forwards = forwards;
    
    if (forwards)
      steps = 0;
    else
      steps = 3;
  }
  
  /**
   * Sets the obstacle to glow.
   * This method is only available for Absorber elements.
   * The icon is changed.
   */
  public void glow()
  {
    setIcon (new ImageIcon ("Images/Difficult/Absorber2.png"));
    glow = true;
  }
  
  /**
   * Returns whether the obstacle is glowing.
   * 
   * @return A boolean that represents whether the obstacle is glowing.
   */
  public boolean getGlow ()
  {
    return glow;
  }
  
  /**
   * The "setMoveForwards" method. It sets the current value of the instance variable "forwards".
   * 
   * @param forwards boolean - The new value of the instance level variable, "forwards". 
   */
  public void setMoveForwards(boolean forwards)
  {
    this.forwards = forwards;
  }
  
  /**
   * The "getMoveForwards" method. It returns whether this object is moving forwards or not. 
   * 
   * @return The current value of "forwards", which represents whether this object is moving forwards or not.
   */
  public boolean getMoveForwards ()
  {
    return forwards;
  }
  
  /**
   * The "move" method. It changes the location of this object to follow a predefined path, as determined by the 
   * if structure. 
   * The AcidCloud moves every 5 updates.
   */
  public void move ()
  {
    if (currentStep == 5)
    {
      currentStep = 0;
      
      if (forwards)
      {
        if (steps < 3)
        {
          getLocation().setColumn(getLocation().getColumn()+1);
          steps++;
        }
        else
        {
          forwards = false;
          getLocation().setColumn(getLocation().getColumn()-1);
          steps --;
        }
      }
      else
      {
        if (steps > 0)
        {
          getLocation().setColumn(getLocation().getColumn()-1);
          steps --;
        }
        else
        {
          forwards = true;
          getLocation().setColumn(getLocation().getColumn()+1);
          steps++;
        }
      } 
    }
  }
  
  /**
   * The "update" method, which updates the current object when the game screen is refreshed/updated. 
   */
  public void update()
  {
    if (getLocation() == null)
      return;
    
    move();
    currentStep++;
  }
  
  /**
   * Sets the current step of the cloud.
   * 
   * @param steps - int - The number of calls to update before the element moves.
   */
  public void setCurrentStep(int steps)
  {
    currentStep = steps;
  }
  
  /**
   * Returns the current step of the cloud.
   * 
   * @return An int that represents the current step of the cloud.
   */
  public int getCurrentStep()
  {
    return currentStep;
  }
  
  /**
   * The "setUpdateCount" method, which changes the current value of the instance variable, "updateCount".
   * 
   * @param updateCount int - The new value for the instance variable, "updateCount". 
   */
  public void setUpdateCount(int updateCount)
  {
    this.updateCount = updateCount;
  }
  
  /**
   * The "getUpdateCount" method, which returns the current value of the instance variable, "updateCount".
   * 
   * @return The current number of updates being tracked. 
   */
  public int getUpdateCount()
  {
    return updateCount;
  }  
}