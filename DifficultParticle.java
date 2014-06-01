import javax.swing.*;
import java.awt.*;

/**
 * The DifficultParticle class represents the elements for the difficult level.
 * It stores the charge.
 * 
 * @author Anqi Wu
 * @version 1.0, May 31, 2014. (Can get image from name, and not from database. Should do this for easy and hard particles too.)
 */
public class DifficultParticle extends Element
{
  //icon
  private ImageIcon myIcon;
  private boolean canMove;
  private int currentStep=0;
  private boolean shift;
  private int charge;
  
  //constructor, sets name, location and grid
  public DifficultParticle (String newName, Location newLocation, int charge)
  {
    super (newName, newLocation);
    this.charge = charge;
    String path = "Images/Difficult/"+newName+".png";
    myIcon = new ImageIcon (path);
  }
  
  public int getCharge ()
  {
    return charge;
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