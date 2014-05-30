import java.awt.*;
import javax.swing.*;

/**
 * The "AcidCloud" class.
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 29 2014. 
 */
public class AcidCloud extends MediumParticle
{
  private ImageIcon myIcon;
  private int steps; //possible values: 0, 1, 2
  private boolean forwards;
  private int charge;
  private int updateCount = 0;
  
  public AcidCloud (String newName, Location newLocation, int charge, boolean forwards)
  {
    super(newName, newLocation,  charge);
    this.forwards = forwards;
    if (forwards)
      steps = 0;
    else
      steps = 3;
  }
  
  public void setMoveForwards(boolean forwards)
  {
    this.forwards = forwards;
  }
  
  public boolean getMoveForwards ()
  {
    return forwards;
  }
  
  public void move ()
  {
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
  
  public void update()
  {
    if (getLocation() == null)
      return;
    move();
  }
  
  public void setUpdateCount(int updateCount)
  {
    this.updateCount = updateCount;
  }
  
  public int getUpdateCount()
  {
    return updateCount;
  }
  
}