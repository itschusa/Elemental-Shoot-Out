import javax.swing.*;
import java.awt.event.*;

/**
 * The EasyParticle class represents the unstable and stable particles of the easy level.
 * It stores the icon.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class EasyParticle extends Particle
{
  //icon
  private ImageIcon myIcon;
  
  //constructor, sets name, location and grid
  public EasyParticle (String newName, Location newLocation, GameGrid newGrid)
  {
    super (newName, newLocation, newGrid);
    
    if (newName.equals ("Stable"))
      myIcon = Database.icon;
    else
      myIcon = Database.icon2;
  }
  
  //returns the icon
  public ImageIcon getIcon ()
  {
    return myIcon;
  }
}