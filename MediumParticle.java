import javax.swing.*;
import java.awt.*;

/**
 * The MediumParticle class represents the elements for the medium level.
 * It stores the charge, to separate alkali from alkaline metals.
 * 
 * @author Anqi Wu
 * @version 1.0, May 28, 2014. (everything except the icon storing works)
 */
public class MediumParticle extends Element
{
  //icon
  private ImageIcon myIcon;
  private boolean canMove;
  private int currentStep=0;
  private boolean shift;
  private int charge;
  
  //constructor, sets name, location and grid
  public MediumParticle (String newName, Location newLocation, int charge)
  {
    super (newName, newLocation);
    
    if (newName.equals ("Lithium"))
      myIcon = Database.icon;
    else if (newName.equals ("Sodium"))
      myIcon = Database.icon2;
    else if (newName.equals ("Potassium"))
      myIcon = Database.icon3;
    else if (newName.equals ("Rubidium"))
      myIcon = Database.icon3;
    else if (newName.equals ("Cesium"))
      myIcon = Database.icon3;
    //francium
    else
      myIcon = Database.icon3;
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