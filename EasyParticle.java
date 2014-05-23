import javax.swing.*;
import java.awt.*;

/**
 * The EasyParticle class represents the unstable and stable particles of the easy level.
 * It stores the icon.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (added icon, name, etc.)
 * @version 1.2, May 22, 2014. (added canMove, now draws and moves itself!)
 */
public class EasyParticle extends Element
{
  //icon
  private ImageIcon myIcon;
  private boolean canMove;
  
  //constructor, sets name, location and grid
  public EasyParticle (String newName, Location newLocation, GameGrid newGrid)
  {
    super (newName, newLocation, newGrid);
    
    if (newName.equals ("Stable"))
      myIcon = Database.icon;
    else if (newName.equals ("Unstable"))
      myIcon = Database.icon2;
    else
      myIcon = Database.icon3;
  }
  
  public void reverseMove ()
  {
    if (canMove == true)
      canMove = false;
    else
      canMove = true;
  }
  
  public boolean canMove ()
  {
    return canMove;
  }
  
  public void update ()
  {
    if (canMove())
      setLocation (new Location (getLocation().getColumn(), getLocation().getRow()+1));
  }
  
  public void draw (Graphics2D graphics)
  {
    graphics.drawImage (getIcon().getImage(), getLocation().getXCoord(), getLocation().getYCoord(),getIcon().getImageObserver());
  }
  
  //returns the icon
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
}