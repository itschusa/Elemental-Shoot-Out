//JAVADOC
import javax.swing.*;
import java.awt.*;

/**
 * The MediumParticle class represents the elements for the medium level.
 * It stores the charge, to separate alkali from alkaline metals.
 * 
 * @author Anqi Wu
 * @version 1.0, May 28, 2014. (everything except the icon storing works)
 * @version 1.1, June 2, 2014. (extends GameElement, moved all methods there)
 */
public class MediumParticle extends GameParticle
{
  //constructor, sets name, location and grid
  public MediumParticle (String newName, Location newLocation, int charge)
  {
    super (newName, newLocation, charge);
    
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
}