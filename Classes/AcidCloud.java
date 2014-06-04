/**
 * The "AcidCloud" class. This class creates objects to act as obstacles for the medium difficulty. 
 * The acid clouds move along a predefined path on the game screen. It is, by extension, a MediumParticle. 
 * 
 * @author Chusa Nguyen
 * @author Anqi Wu
 * @version 1.0, May 29 2014. 
 * @version 1.1, June 3, 2014. (added currentStep (5 step wait), so clouds move slower)
 */
public class AcidCloud extends GameParticle
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
  private int currentStep = 0;
  
  /**
   * The class constructor. It creates an acid cloud object with the specified name, at a specified location 
   * and a given charge, as well as set its initial direction of movement.
   * 
   * @param newName String - String representation of this object's name.
   * @param newLocation Location - The location given upon creation. 
   * @param forwards boolean - Determines whether the first step will be forwards or backwards.
   */
  public AcidCloud (String newName, Location newLocation, int charge, boolean forwards)
  {
    super(newName, newLocation,  charge, 2);
    this.forwards = forwards;
    if (forwards)
      steps = 0;
    else
      steps = 3;
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
   * The "move" method. It changes the location of this object to follow a predefined path. 
   */
  public void move ()
  {
    if (currentStep == 5)
    {
      currentStep=0;
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
   * Sets the current step of the element.
   * 
   * @param steps - int - The number of calls to update before the element moves.
   */
  public void setCurrentStep(int steps)
  {
    currentStep = steps;
  }
  
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