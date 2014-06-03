//JAVADOC
import javax.swing.*;
import java.awt.*;

/**
 * The DifficultParticle class represents the elements for the difficult level.
 * It stores the charge.
 * 
 * @author Anqi Wu
 * @version 1.0, May 31, 2014. (Can get image from name, and not from database. Should do this for easy and hard particles too.)
 * @version 1.1, June 2, 2014. (Extends GameParticle, methods are all moved there)
 */
public class DifficultParticle extends GameParticle
{

  //constructor, sets name, location and grid
  public DifficultParticle (String newName, Location newLocation, int charge)
  {
    super (newName, newLocation, charge);
    String path = "Images/Difficult/"+newName+".png";
    setIcon (new ImageIcon (path));
  }
}