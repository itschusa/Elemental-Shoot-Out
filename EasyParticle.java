//JAVADOC
import javax.swing.*;
import java.awt.*;

/**
 * The EasyParticle class represents the unstable and stable particles of the easy level.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (added icon, name, etc.)
 * @version 1.2, May 22, 2014. (added canMove, now draws and moves itself!)
 * @version 1.3, May 27, 2014. (added setCanMove, setCurrentStep, setShift. particles now shift themselves left(inventory))
 * @version 1.4, May 28, 2014. (added getCharge (empty))
 * @version 1.5, June 2, 2014. (now extends GameElement, which has most of the methods)
 */
public class EasyParticle extends GameParticle
{
  //constructor, sets name, location and grid
  public EasyParticle (String newName, Location newLocation)
  {
    super (newName, newLocation, 0);
    
    if (newName.equals ("Stable"))
      setIcon (Database.icon);
    else if (newName.equals ("Unstable"))
      setIcon (Database.icon2);
    else
      setIcon (Database.icon3);
  }
}