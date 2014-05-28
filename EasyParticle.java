import javax.swing.*;
import java.awt.*;

/**
 * The EasyParticle class represents the unstable and stable particles of the easy level.
 * It stores the icon.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (added icon, name, etc.)
 * @version 1.2, May 22, 2014. (added canMove, now draws and moves itself!)
 * @version 1.3, May 27, 2014. (added setCanMove, setCurrentStep, setShift. particles now shift themselves left(inventory))
 */
public class EasyParticle extends Element
{
  //icon
  private ImageIcon myIcon;
  private boolean canMove;
  private int currentStep=0;
  private boolean shift;
  
  //constructor, sets name, location and grid
  public EasyParticle (String newName, Location newLocation)
  {
    super (newName, newLocation);
    
    if (newName.equals ("Stable"))
      myIcon = Database.icon;
    else if (newName.equals ("Unstable"))
      myIcon = Database.icon2;
    else
      myIcon = Database.icon3;
  }
  
  public void setCanMove (boolean move)
  {
    canMove=move;
  }
  
  public boolean canMove ()
  {
    return canMove;
  }
  
  public void setCurrentStep(int steps)
  {
    currentStep = steps;
  }
  
  public void setShift (boolean set)
  {
    shift = set;
  }
  
  public void updateBounce ()
  {
    if (getLocation().getRow() <= 0)
      removeFromGrid();
    //setLocation(new Location (getColumn(), 1));
  }
  
  public void update ()
  {
    if (getLocation() == null)
      return;
    
    if (currentStep == 10)
    {
      currentStep = 0;
      if (canMove())
      {
        setLocation (new Location (getLocation().getColumn(), getLocation().getRow()-1));
        updateBounce();
      }
      else
      {
        if (shift)
        {
          setLocation(new Location(getLocation().getColumn()-1, getLocation().getRow()));
          shift = false;
        }
      }
    }
    currentStep++;
  }
  
  public void draw (Graphics2D graphics)
  {
    if (getLocation()== null)
      return;
    graphics.drawImage (getIcon().getImage(), getLocation().getXCoord(), getLocation().getYCoord(),getIcon().getImageObserver());
  }
  
  //returns the icon
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
}