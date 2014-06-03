import java.awt.*;
import javax.swing.*;

/**
 * The GameParticle class represents the generic game element.
 * 
 * @author Anqi Wu
 * @version 1.0, June 2, 2014 (Has all the methods - Thinking of just combining all into one, with param indicating level)
 * @version 1.1, June 3, 2014 (Deprecated EasyParticle, MediumParticle and DifficultParticle)
 */
public class GameParticle extends Element
{
  private ImageIcon myIcon;
  private boolean canMove;
  private int currentStep=0;
  private boolean shift;
  private int charge;
  
  //constructor, sets name, location and grid
  public GameParticle (String newName, Location newLocation, int charge, int level)
  {
    super (newName, newLocation);
    if (level == 1)
    {
      if (newName.equals ("Stable"))
        setIcon (Database.icon);
      else if (newName.equals ("Unstable"))
        setIcon (Database.icon2);
      else
        setIcon (Database.icon3);
    }
    else if (level == 2)
    {
      if (newName.equals ("Lithium"))
        setIcon (Database.icon5);
      else if (newName.equals ("Sodium"))
        setIcon (Database.icon6);
      else if (newName.equals ("Potassium"))
        setIcon (Database.icon7);
      else if (newName.equals ("Rubidium"))
        setIcon (Database.icon8);
      else if (newName.equals ("Cesium"))
        setIcon (Database.icon9);
      else if (newName.equals ("Francium"))
        setIcon (Database.icon10);
      else if (newName.equals("Hydroxide"))
        setIcon (Database.icon11);
      else 
        setIcon (Database.icon12);
    }
    else
    {
      String path = "../Images/Difficult/"+newName+".png";
      setIcon (new ImageIcon (path));
    }
    this.charge = charge;
  }
  
  public void setIcon (ImageIcon icon)
  {
    myIcon = icon;
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
  
  public int getCharge()
  {
    return charge;
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